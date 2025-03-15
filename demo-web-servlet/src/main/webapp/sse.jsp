<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>sse test</title>
</head>
<script type="text/javascript">
    // const es = new EventSource("/sse", { withCredentials: true });（可在跨域时使用该参数）
    const es = new EventSource("/sse");
    es.onopen = function (e) {
        console.log("open")
    };
    es.onmessage = function (e) {
        console.log(e.data);
    };
    es.onerror = function (e) {
        console.log("error");
        es.close()
    }
</script>
<body>

</body>
</html>
