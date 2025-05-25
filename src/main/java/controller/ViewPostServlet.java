package controller;

import dao.PostDAO;
import model.Post;
import dao.CommentDAO;
import model.Comment;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

@WebServlet("/view-post")
public class ViewPostServlet extends HttpServlet {
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

            PostDAO dao = new PostDAO();
            Post post = dao.findById(postId);

            if (post == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "指定された投稿が見つかりません。");
                return;
            }

            // コメント一覧を取得
            CommentDAO commentDAO = new CommentDAO();
            List<Comment> commentList = commentDAO.findByPostId(postId);

            // 投稿日の整形
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            sdf.setTimeZone(TimeZone.getTimeZone("Asia/Tokyo")); // Time zone
            String formattedDate = sdf.format(post.getCreatedAt());

            // JSP に渡す
            request.setAttribute("post", post);
            request.setAttribute("formattedDate", formattedDate);
            request.setAttribute("comments", commentList);

            request.getRequestDispatcher("views/view-post.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "投稿IDの形式が正しくありません。");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("データベースエラー", e);
        }
    }
}
