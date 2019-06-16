<html>
<head>
	<title>rule</title>
</head>
<body>
	<#include "/header.ftl">
	<div>
		<form action="${springMacroRequestContext.contextPath}/rule/addPublic" method="POST">
			<table>
				<tr><td>contentType</td>
					<td>
						<select name="contentType">
							<option value="import">import</option>
							<option value="declare">declare</option>
							<option value="global">global</option>
							<option value="function">function</option>
							<option value="query">query</option>
						</select>
					</td>
				</tr>
				<tr><td>content</td><td><textarea name="content"></textarea></td></tr>
				<tr><td>content备注</td><td><span>需带关键字，如import，global，import和global以分号结尾</span></td></tr>
				<tr><td>orderNum</td><td><input name="orderNum"/></td></tr>
				<tr><td colspan=2><input type="submit" value="submit"/></td></tr>
			</table>
		</form>
	</div>
	<#include "/footer.ftl">
</body>
</html>