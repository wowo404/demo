package org.demo.tomcat.asyncservlet;

import jakarta.servlet.AsyncContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

/**
 * 异步servlet
 *
 * @Author lzs
 * @Date 2023/2/13 17:27
 **/
@WebServlet(urlPatterns = "/async", asyncSupported = true)
public class AsyncServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long start = System.currentTimeMillis();
        AsyncContext asyncContext = req.getAsyncContext();
        CompletableFuture.runAsync(() -> doSomething(asyncContext, asyncContext.getRequest(), asyncContext.getResponse()));
        System.out.println("总耗时：" + (System.currentTimeMillis() - start));
    }

    private void doSomething(AsyncContext asyncContext, ServletRequest request, ServletResponse response) {
        try {
            Thread.sleep(3000);
            response.getWriter().write("ok");
            asyncContext.complete();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }
}
