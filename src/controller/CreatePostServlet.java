package controller;

import model.dao.PostDAO;
import model.dto.Post;
import utils.HtmlUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.SQLException;

@WebServlet("/create-post")
// 画像アップロード用の設定
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 10 * 1024 * 1024)
public class CreatePostServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        // フォームからの入力値を取得
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String content = request.getParameter("content");

        // XSS対策として、HTMLの特殊文字をエスケープ
        title = HtmlUtils.escapeHtml(title);
        author = HtmlUtils.escapeHtml(author);
        content = HtmlUtils.escapeHtml(content);

        // 空欄チェック（必須項目）
        if (title == null || title.trim().isEmpty() ||
                author == null || author.trim().isEmpty() ||
                content == null || content.trim().isEmpty()) {

            // 入力エラー時は、エラーメッセージを設定して投稿ページに戻す
            request.setAttribute("error", "タイトル、ユーザー名、本文はすべて必須です。");
            request.getRequestDispatcher("create-post.jsp").forward(request, response);
            return;
        }

        // 画像アップロード処理
        Part imagePart = request.getPart("image");
        String imageName = "";
        String imagePath = "";

        if (imagePart != null && imagePart.getSize() > 0) {
            // コンテンツタイプを確認
            String contentType = imagePart.getContentType();
            if (contentType.startsWith("image/")) {
                // アップロードされたファイル名を取得; Path処理
                imageName = Paths.get(imagePart.getSubmittedFileName()).getFileName().toString();

                // 実際に保存するサーバー上のパスを取得
                String uploadPath = getServletContext().getRealPath("/assets/img/");
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) uploadDir.mkdirs();// ディレクトリがなければ作成

                // サーバー上に画像ファイルを書き込み
                imagePart.write(uploadPath + File.separator + imageName);
                imagePath = "assets/img/" + imageName; // DBに保存する相対パス
            } else {
                // 画像ファイルでない場合のエラー処理
                request.setAttribute("error", "Only image files are allowed.");
                request.getRequestDispatcher("create-post.jsp").forward(request, response);
                return;
            }
        } else {
            // 画像未選択時はデフォルト画像を設定 !!
            imagePath = "assets/img/default.png";
        }

        // 投稿データをDTO(Postオブジェクト)にまとめる
        Post post = new Post();
        post.setTitle(title);
        post.setAuthor(author);
        post.setContent(content);
        post.setImagePath(imagePath);

        // DAOを使ってDBに保存
        try {
            PostDAO dao = new PostDAO();
            dao.insertPost(post);
        } catch (SQLException e) {
            e.printStackTrace(); // エラー詳細を出力
            throw new ServletException("データベースエラーが発生しました。 ", e);
        }


        // 成功メッセージをセッションに格納
        HttpSession session = request.getSession();
        session.setAttribute("success", "投稿が完了しました！");

        // 投稿一覧ページへリダイレクト
        response.sendRedirect("posts");
    }
}
