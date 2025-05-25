package controller;

import dao.PostDAO;
import model.Post;
import utils.S3Uploader;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.InputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.SQLException;

@WebServlet("/edit-post")
@MultipartConfig
public class EditPostServlet extends HttpServlet {

    // 編集画面を表示（GETリクエスト）
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idParam = request.getParameter("id");

        if (idParam == null || idParam.isBlank()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "投稿IDが指定されていません。");
            return;
        }

        try {
            int postId = Integer.parseInt(idParam);
            Post post = new PostDAO().findById(postId);

            if (post == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "投稿が見つかりません。");
                return;
            }

            request.setAttribute("post", post);
            request.getRequestDispatcher("/views/edit-post.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "投稿IDの形式が正しくありません。");
        } catch (SQLException e) {
            throw new ServletException("データベースエラー", e);
        }
    }

    // 投稿を更新（POSTリクエスト）
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        try {
            int postId = Integer.parseInt(request.getParameter("id"));
            String title = request.getParameter("title");
            String author = request.getParameter("author");
            String content = request.getParameter("content");

            // 画像の処理
            Part filePart = request.getPart("image");
            String imagePath;

            if (filePart != null && filePart.getSize() > 0) {
                String fileName = Path.of(filePart.getSubmittedFileName()).getFileName().toString();
                try (InputStream inputStream = filePart.getInputStream()) {
                    imagePath = S3Uploader.uploadImage(inputStream, filePart.getSize(), filePart.getContentType(), fileName);
                }
            } else {
                // No new image uploaded, keep the old image path
                Post existingPost = new PostDAO().findById(postId);
                imagePath = (existingPost != null) ? existingPost.getImagePath() : null;
            }

            Post post = new Post();
            post.setId(postId);
            post.setTitle(title);
            post.setAuthor(author);
            post.setContent(content);
            post.setImagePath(imagePath);

            new PostDAO().update(post);
            response.sendRedirect(request.getContextPath() + "/posts");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "投稿の更新中にエラーが発生しました。");
        }
    }
}
