package org.liu.velocity.ctrl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.Template;
import org.apache.velocity.context.Context;
import org.apache.velocity.tools.view.VelocityViewServlet;

/**
 * 使用servlet方式
 * @author liuzhsh
 */
public class VelocityDemoServlet extends VelocityViewServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected Template handleRequest(HttpServletRequest request,
			HttpServletResponse response, Context ctx) {
		// 往Context容器存放变量
        ctx.put("fullName","lixiaolin");
        // 也可以往request域中存值
        request.setAttribute("anotherName","xlli");
        // forward到指定模板
		return getTemplate("test.vm");
	}

}
