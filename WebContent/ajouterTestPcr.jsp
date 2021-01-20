<%@ page 	language="java" contentType="text/html; charset=ISO-8859-1"
    		pageEncoding="ISO-8859-1"%>
 <%@ page 	import="java.util.ArrayList"
 			import="com.DAO.CasDAO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>covid</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/stylesheet.css">
</head>
<body>
<h1>Ajouter un TestPcr</h1>
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
		
		les tests sont datés du jour de la saisie du TestPcr<Br/>
		identifiants de cas <select name="id_teste">
		<option value="nouveau">cas à créer</option>			
			
			<%ArrayList<String> liste2=new ArrayList<String>();
			try {
				liste2 = CasDAO.getListeId_cas();
				String str = "";
				str="=======liste des Id de cas enregistrés=======\n";
				for(int i=0;i<liste2.size();i++) {
					str=str+ liste2.get(i).toString()+"\n";
				}
				System.out.println(str); 
				for (int i=0; i < liste2.size(); i++) {
				%><option value="<%=liste2.get(i).toString() %>"><%=liste2.get(i) %></option>
				<% 
					
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} %></select>
		<p>
			resultat du test<select name="resultat">
				<option value="-1">-1 negatif</option>
				<option value="1">1 positif</option>
			</select><br />
		</p>
		
		

		<input type="hidden" name="action" value="TestPcrToAdd" />
		</p>
		<input type="submit" value="ajouter" />
		</p>

	</form>
</body>
</html>