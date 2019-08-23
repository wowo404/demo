<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
    <script src="https://cdn.bootcss.com/jquery/3.4.1/jquery.js"></script>
</head>
<body>
<div>
    <form name="login-form" action="/websocket/login" method="post">
        <div>
            <span>用户名：</span><input name="username"/><br/>
        </div>
        <input type="submit" value="登录"/>
    </form>
</div>
</body>
</html>
