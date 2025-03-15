<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>sse test</title>
</head>
<script type="text/javascript">
    // const es = new EventSource("/sse", { withCredentials: true });（可在跨域时使用该参数）
    const es = new EventSource("/sseCustomEvent");
    es.addEventListener("open", function (e) {
        console.log("open");
    })
    es.addEventListener("javaboy", function (e) {
        console.log(e.data, e.lastEventId, e);
    })
    es.addEventListener("error", function (e) {
        console.log("error")
        es.close();
    })
</script>
<body>

</body>
</html>
