package org.liu.javaxannotation;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.when;

/**
 * TODO:此方法没有测试完整的servlet生命周期
 */
@RunWith(MockitoJUnitRunner.class)
public class TestJavaxAnnotationServletTest {

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;

    @Test
    public void doGet() throws IOException, ServletException {
        when(request.getParameter("name")).thenReturn("liu");
        when(request.getParameter("age")).thenReturn("30");
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("sessionModel")).thenReturn("1");

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        when(response.getWriter()).thenReturn(pw);

        TestJavaxAnnotationServlet servlet = new TestJavaxAnnotationServlet();
        servlet.doGet(request, response);
        String result = sw.getBuffer().toString();
        System.out.println(result);
        JSONObject object = JSON.parseObject(result);
        assertEquals(object.get("name"), "liu");
    }

}