<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Covid</title>

</head>
<body>

<form action="monControleur" method="post">
login<input type="text" name="login"><br/>

pass<input type="password" name="pwd">
<input type="hidden" name="action" value="authentification" />
<input name="valider" type="submit" value="envoyer"></form>

</body>
</html>