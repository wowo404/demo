package org.demo.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author lzs
 * @Date 2022/6/23 9:02
 **/
@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        try {
            req.login(username, password);
        } catch (ServletException e) {
            e.printStackTrace();
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
            return;
        }
        boolean login = req.getUserPrincipal() != null && req.isUserInRole("admin");
        if (login) {
            resp.sendRedirect("/hello");
        } else {
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }
}
