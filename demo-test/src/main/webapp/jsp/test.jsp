<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
	int i = 1;
	try {
		while (true) {
			out.print("<h1>" + (i++) + "</h1>");
			out.flush();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				out.print("<h1>" + e + "</h1>");
			}
		}
	} catch (Exception e) {
		out.print("<h1>" + e + "</h1>");
	}
%>
</body>
</html>