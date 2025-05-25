package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {

        String password = request.getParameter("password");

        if ("moglog".equals(password)) {
            request.getSession().setAttribute("loggedIn", true);
            response.sendRedirect(request.getContextPath() + "/posts");
        } else {
            request.setAttribute("error", "パスワードが間違っています。");
            request.getRequestDispatcher("/views/login.jsp").forward(request, response);
        }
    }
}
