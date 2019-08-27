<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <script src="https://cdn.bootcss.com/jquery/3.4.1/jquery.js"></script>
    <script src="http://cdn.bootcss.com/sockjs-client/1.4.0/sockjs.js"></script>
    <script type="application/javascript">
        var ws;
        var username = '${session.username}';
        console.log(username);
        var url = 'ws://localhost:8080/websocket/socketServer';

        window.onload = function () {
            if ('WebSocket' in window) {
                ws = new WebSocket(url);
            } else if ('MozWebSocket' in window) {
                ws = new MozWebSocket(url);
            } else {
                ws = new SockJS("http://127.0.0.1:8080/sockjs/socketServer");
                return;
            }
            ws.onopen = function (ev) {
                console.log(ev);
            }
            ws.onmessage = function (ev) {
                console.log(ev);
                alert('message is:' + ev.data);
            }
            ws.onerror = function (ev) {
                console.log('error occured')
            }
            ws.onclose = function (ev) {
                console.log('connection closed');
            }
        }
        window.close = function () {
            ws.close()
        }

        //将消息发送给后台服务器
        function send() {
            //alert(websocket.readyState + ":" + websocket.OPEN);
            if (ws.readyState == ws.OPEN) {
                var msg = $("#msg").val();
                ws.send("#anyone#" + msg);//调用后台handleTextMessage方法
            } else {
                alert("连接失败!");
            }
        }

        function doSendUsers() {
            if (ws.readyState == ws.OPEN) {
                var msg = $("#msg").val();
                ws.send("#everyone#" + msg);//调用后台handleTextMessage方法
            } else {
                alert("连接失败!");
            }
        }

        function webSocketClose() {
            ws.close();
        }
    </script>
</head>
<body>
<h3>欢迎 ${sessionScope.username }使用本聊天系统！！</h3>

<div id="content"
     style="border: 1px solid black; width: 400px; height: 300px; float: left; color: #7f3f00;"></div>
<div id="userList"
     style="border: 1px solid black; width: 120px; height: 300px; float: left; color: #00ff00;"></div>

<div style="clear: both;color:#00ff00">
    <label for="msg">消息：</label><input id="msg"/>
    <button onclick="send();">发送消息</button>
    <button onclick="doSendUsers();">群发</button>
    <button onclick="webSocketClose();">关闭连接</button>
</div>
</body>
</html>
