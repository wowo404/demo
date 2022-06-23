package org.demo.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;

/**
 * @Author lzs
 * @Date 2022/6/23 9:26
 **/
@WebServlet(urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Principal userPrincipal = req.getUserPrincipal();
        if (userPrincipal == null) {
            resp.setStatus(401);
            req.setAttribute("message", "please login");
        } else if (!req.isUserInRole("admin")) {
            resp.setStatus(403);
            req.setAttribute("message", "forbidden");
        } else {
            req.setAttribute("message", "success");
        }
        req.getRequestDispatcher("hello.jsp").forward(req, resp);
    }
}
