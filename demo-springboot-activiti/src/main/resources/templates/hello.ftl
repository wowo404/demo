<html>
<head>
	<meta charset="UTF-8">
	<title>Hello World!</title>
</head>
<body>
	<#include "/header.ftl">
	<div>Welcome ${name} to freemarker!</div>
	<div>spring context path:${springMacroRequestContext.contextPath}</div>
	<#include "/footer.ftl">
</body>
</html>