<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="controleur" method="post">
login<input type="text" name="login"><br/>

pass<input type="password" name="pwd">
		<p>
			<input type="hidden" name="action" value="authentification" />
		<p></p>
			<input type="submit" value="s'authentifier" />
		</p>

	</form>
</body>
</html>