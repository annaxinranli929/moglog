package controller;

import dao.PostDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/like-post")
public class LikePostServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        try {
            int postId = Integer.parseInt(request.getParameter("id"));
            new PostDAO().incrementLikes(postId);
            response.sendRedirect(request.getContextPath() + "/posts");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(500,  "いいねに失敗しました");
        }
    }
}
