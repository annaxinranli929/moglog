package controller;

import model.dao.PostDAO;
import model.dto.Post;

import javax.servlet.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/posts")
public class PostListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // DAOオブジェクトを作成し、投稿リストを取得
        PostDAO dao = new PostDAO();
        List<Post> postList = dao.findAll();

        // チェック
        System.out.println("Loaded posts: " + postList.size());
        for (Post p : postList) {
            System.out.println("Post title: " + p.getTitle());
        }

        // JSPにデータ（postList）を渡す
        // "posts" は JSP側getAttribute("")と一致させる
        request.setAttribute("posts", postList);

        // JSPへフォワード
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/post-list.jsp");
        dispatcher.forward(request, response);
    }
}
