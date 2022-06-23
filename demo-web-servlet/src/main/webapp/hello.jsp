<%@ page import="java.security.Principal" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Hello</title>
</head>
<body>
<%
    int httpStatus = response.getStatus();
    Object message = request.getAttribute("message");
%>
<h1>Hello,Servlet</h1>
<span>状态码：<%=httpStatus%>，返回消息：<%=message%></span>
<hr>
<span><a href="/login.jsp">login</a></span>
</body>
</html>