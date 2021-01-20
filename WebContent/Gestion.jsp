<%-- <%@page import="org.apache.tomcat.util.security.Escape"%> --%>
<%-- <%@page import="DAO.CasDAO"%> --%>
<%@page import="com.covid.TestPcr"%>
<%@page import="java.util.ArrayList" 
import="com.DAO.CasDAO"
import="com.DAO.TestPcrDAO"
	import="com.covid.Cas"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>covid</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/stylesheet.css">
</head>
<body>
	<h1>Tableau de gestion des cas de covid</h1>
	<%--  <jsp:useBean id="lcas" --%>
	<%-- 		class="com.DAO.CasDAO" scope="page"></jsp:useBean> --%>
	<%-- 	<jsp:setProperty property="*" name="lcas" />  --%>
	<!--  <table> -->
	<%-- <c:forEach items="${lcas.getListeId_cas()}" var="a"> --%>
	<!-- <tr> -->
	<%-- <td>${a.id_cas}</td> --%>
	<%-- <td>${a.nom_complet}</td> --%>
	<%-- <td>${a.telephone}</td> --%>
	<%-- <td>${a.adresse}</td> --%>
	<%-- <td>${a.code_postal}</td> --%>
	<%-- <td>${a.etat}</td> --%>
	<!-- </tr> -->
	<%-- </c:forEach> --%>
	<%
		if (request.getAttribute("erreur") != null) {
	%>

	<div class="errors">
		<%=request.getAttribute("erreur")%></div>
	<%
		}
	%>
	<%
		ArrayList<Cas> liste = new ArrayList<Cas>();
		ArrayList<TestPcr> listep= new ArrayList<TestPcr>();
	
	%>
	<table>
		<tr>

			<form action="controleur" method="post">
				<input type="hidden" name="action" value="deconnexion" /> <input
					type="submit" value="deconnexion" />
			</form>
			<td>
				<form action="controleur" method="post">
					<input type="hidden" name="action" value="ajouterCas" /> <input
						type="submit" value="ajouter un cas" />
				</form>
				<table border=1>
					<tr>
						<th>id</th>
						<th>nom prenom</th>
						<th>telephone</th>
						<th>adresse</th>
						<th>codepostal</th>
						<th>etat</th>
					</tr>
					<%
						
					%><tr>
						<%try {
							liste = CasDAO.getListe();
							String str = "";
							str = "=======liste des cas enregistrés=======\n";
							for (int i = 0; i < liste.size(); i++) {
								str = str + liste.get(i).toString() + "\n";
							}
							System.out.println(str);
							for (int i = 0; i < liste.size(); i++) {
						%><td><%=liste.get(i).getId_cas()%></td>
						<%
							
						%><td><%=liste.get(i).getNom_complet()%></td>
						<%
							
						%><td><%=liste.get(i).getTelephone()%></td>
						<%
							
						%><td><%=liste.get(i).getAdresse()%></td>
						<%
							
						%><td><%=liste.get(i).getCode_postal()%></td>
						<%
							
						%><td><%=liste.get(i).getEtat()%></td>
					</tr>
					<%
						}

					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					%>

					</tr>
				</table>
			</td>
			<td>
			</td>
			<td><form action="controleur" method="post">
					<input type="hidden" name="action" value="ajouterTestPcr" /> <input
						type="submit" value="ajouter un testPcr" />
				</form>
				<table border=1>
					<tr>
						<th>id</th>
						<th>jour</th>
						<th>mois</th>
						<th>annee</th>
						<th>cas teste</th>
						<th>resultat</th>
					</tr>
					<%
						
					%><tr>
						<%
						try {
							listep = TestPcrDAO.getListe();
							String str1 = "";
							str1 = "=======liste des testPcr enregistrés=======\n";
							for (int i = 0; i < listep.size(); i++) {
								str1 = str1 + listep.get(i).toString() + "\n";
							}
							System.out.println(str1);
							for (int i = 0; i < listep.size(); i++) {
						%><td><%=listep.get(i).getId_test()%></td>
						<%
							
						%><td><%=listep.get(i).getJour()%></td>
						<%
							
						%><td><%=listep.get(i).getMois()%></td>
						<%
							
						%><td><%=listep.get(i).getAnnee()%></td>
						<%
							
						%><td><%=listep.get(i).getId_cas()%></td>
						<%
							
						%><td><%=listep.get(i).getResultat()%></td>
					</tr>
					<%
						}

					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					%>

					</tr>
				</table></td>
		</tr>
	</table>

</body>
</html>