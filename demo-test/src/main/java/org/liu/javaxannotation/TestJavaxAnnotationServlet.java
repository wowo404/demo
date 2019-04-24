package org.liu.javaxannotation;

import com.alibaba.fastjson.JSONObject;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * @author liuzhangsheng
 * @create 2019/4/21
 */
public class TestJavaxAnnotationServlet extends HttpServlet {

    public TestJavaxAnnotationServlet(){
        System.out.println("construct");
    }

    @Override
    public void destroy() {
        System.out.println("destroy");
    }

    @Override
    public void init() throws ServletException {
        System.out.println("init");
    }
//@PostConstruct和@PreDestroy两个注解只有在servlet中才起作用

    //被@PostConstruct修饰的方法会在服务器加载Servlet的时候运行，并且只会被服务器调用一次，类似于Serclet的inti()方法。
    // 被@PostConstruct修饰的方法会在构造函数之后，init()方法之前运行。
    @PostConstruct
    private void postConstruct(){
        System.out.println("@PostConstruct");
    }

    //被@PreDestroy修饰的方法会在服务器卸载Servlet的时候运行，并且只会被服务器调用一次，类似于Servlet的destroy()方法。
    // 被@PreDestroy修饰的方法会在destroy()方法之后运行，在Servlet被彻底卸载之前。
    @PreDestroy
    private void preDestroy(){
        System.out.println("@PreDestroy");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String age = req.getParameter("age");
        resp.setContentType("application/json");
        Object sessionModel = req.getSession().getAttribute("sessionModel");
        System.out.println(sessionModel);
        PrintWriter writer = resp.getWriter();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", name);
        jsonObject.put("age", age);
        jsonObject.put("timestamp", new Date());
        writer.print(jsonObject.toJSONString());
    }
}
