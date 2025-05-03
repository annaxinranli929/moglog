package controller;

import model.dao.PostDAO;
import model.dto.Post;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/posts")
public class PostListServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // DAOから投稿リストを取得
        PostDAO dao = new PostDAO();
        List<Post> postList = dao.findAll();

        // JSPに渡す
        request.setAttribute("posts", postList);

        // JSPへフォワード
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/post_list.jsp");
        dispatcher.forward(request, response);
    }
}
