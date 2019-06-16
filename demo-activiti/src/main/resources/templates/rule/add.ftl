<html>
<head>
	<title>rule</title>
</head>
<body>
	<#include "/header.ftl">
	<div>
		<form action="${base}/rule/save" method="POST">
			<table>
				<tr><td>rulePackage</td><td><input name="rulePackage"/></td></tr>
				<tr><td>ruleName</td><td><input name="ruleName"/></td></tr>
				<tr><td>score</td><td><input name="score"/></td></tr>
				<tr><td>content</td><td><textarea name="content"></textarea></td></tr>
				<tr><td colspan=2><input type="submit" value="submit"/></td></tr>
				<tr><td>springMacroRequestContext.contextPath</td><td>${springMacroRequestContext.contextPath}</td></tr>
				<tr><td>request.getContextPath()</td><td>${request.getContextPath()}</td></tr>
			</table>
		</form>
	</div>
	<#include "/footer.ftl">
</body>
</html>