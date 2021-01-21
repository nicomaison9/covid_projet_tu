<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>covid</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/stylesheet.css">
</head>
<body><h1>Covid test center</h1>
<%
		if (request.getAttribute("erreur") != null) {
	%>

	<div class="errors">
		<%=request.getAttribute("erreur")%></div>
	<%
		}
	%>
	<form action="controleur" method="post">
		<!-- login<input type="text" required pattern="[A-Za-z0-9]{4,30}" name="login"><br /> 
		pass<input	type="password" required pattern="[A-Za-z0-9] name="pwd"> -->
		login<input type="text" name="login"><br /> 
		pass<input	type="password"  name="pwd">
		<p>
			<input type="hidden" name="action" value="authentification" />
		<p></p>
		<input type="submit" value="s'authentifier" />
		</p>

	</form>
</body>
</html>