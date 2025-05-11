package controller;

import model.dao.PostDAO;
import model.dto.Post;

import javax.servlet.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/posts")
public class PostListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        // ログインチェック
        /*HttpSession session = request.getSession();
        Boolean loggedIn = (Boolean) session.getAttribute("loggedIn");
        if (loggedIn == null || !loggedIn) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }*/

        String keyword = request.getParameter("keyword");

        // DAOオブジェクトを作成し、投稿リストを取得
        try {
            PostDAO dao = new PostDAO();
            List<Post> postList = dao.findAll();

            // チェック
            System.out.println("Loaded posts: " + postList.size());
            for (Post p : postList) {
                System.out.println("Post title: " + p.getTitle());
            }

            // 検索機能
            if (keyword != null && !keyword.trim().isEmpty()) {
                postList = dao.findByKeyword(keyword);
            } else {
                postList = dao.findAll();
            }

            // JSPにデータ（postList）を渡す
            // "posts" は JSP側getAttribute("")と一致させる
            request.setAttribute("posts", postList);

            // JSPへフォワード
            request.getRequestDispatcher("/views/post-list.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("データベースエラー", e);
        }
    }
}
