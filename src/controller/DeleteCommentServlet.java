package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import model.dao.CommentDAO;

@WebServlet("/delete-comment")
public class DeleteCommentServlet extends HttpServlet {
    @Override
    protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commentIdParam = request.getParameter("commentId");
        String postIdParam = request.getParameter("postId");

        try {
            int commentId = Integer.parseInt(commentIdParam);
            int postId = Integer.parseInt(postIdParam);

            CommentDAO dao = new CommentDAO();
            dao.delete(commentId);

            response.sendRedirect("view-post?id=" + postId);
        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "削除に失敗しました");
        }
    }
}
