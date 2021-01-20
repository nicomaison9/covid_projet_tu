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
<body>
<h1>Ajouter un cas</h1>
	<form action="controleur" method="post">
		<%
			if (request.getAttribute("erreur") != null) {
		%>

		<div class="errors">
			<%=request.getAttribute("erreur")%></div>
		<%
			}
		%>
		<!-- login<input type="text" required pattern="[A-Za-z0-9]{4,30}" name="login"><br /> 
		pass<input	type="password" required pattern="[A-Za-z0-9] name="pwd"> -->
		<p>
			nom prenom<input type="text" name="nom_complet"><br />
		</p>
		<p>
			telephone<input type="text" name="telephone"><br />
		</p>
		<p>
			adresse<input type="text" name="adresse"><br />
		</p>
		<p>
			code_postal<input type="text" name="code_postal"><br />
		</p>
		<p>
			statut covid<select name="etat">
				<option value="-1">-1 negatif</option>
				<option value="1">1 positif</option>
			</select><br />
		</p>

		<input type="hidden" name="action" value="CasToAdd" />
		</p>
		<input type="submit" value="ajouter" />
		</p>

	</form>
</body>
</html>