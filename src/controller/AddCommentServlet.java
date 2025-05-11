package controller;

import model.dao.CommentDAO;
import model.dto.Comment;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/add-comment")
public class AddCommentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String postIdParam = request.getParameter("postId");
        String author = request.getParameter("author");
        String content = request.getParameter("content");

        if (postIdParam == null || author == null || content == null ||
                postIdParam.isBlank() || author.isBlank() || content.isBlank()) {

            request.setAttribute("error", "全ての項目を入力してください。");
            request.getRequestDispatcher("views/view-post.jsp").forward(request, response);
            return;
        }

        try {
            int postId = Integer.parseInt(postIdParam);

            Comment comment = new Comment();
            comment.setPostId(postId);
            comment.setAuthor(author);
            comment.setContent(content);

            CommentDAO commentDAO = new CommentDAO();
            commentDAO.insert(comment);

            response.sendRedirect("view-post?id=" + postId);

        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "投稿IDが不正です。");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("コメントの保存に失敗しました。", e);
        }
    }
}
