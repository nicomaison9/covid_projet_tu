<%-- <%@page import="org.apache.tomcat.util.security.Escape"%> --%>
<%-- <%@page import="DAO.CasDAO"%> --%>
<%@page import="com.covid.TestPcr"%>
<%@page import="java.util.ArrayList" import="com.DAO.CasDAO"
	import="com.DAO.TestPcrDAO" import="com.covid.Cas"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>covid</title>
<%-- <link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/stylesheet.css"> --%>
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
	
</head>
<body><center>
	<h1>Tableau de bord</h1>
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
	ArrayList<TestPcr> listep = new ArrayList<TestPcr>();
	%>
	<table>
		<tr>

			<form action="controleur" method="post">
				<input type="hidden"  name="action" value="deconnexion" /> <input
					type="submit" value="deconnexion" class="btn btn-danger" />
			</form>
			<td><center>
					<h2>gestion des cas</h2>
				</center>
				<form action="controleur" method="post">
					<input type="hidden" name="action" value="ajouterCas" /> <input
						type="submit" value="ajouter un cas" />
				</form>
				<table class="table">
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
						<%
							try {
							liste = CasDAO.getListe();
							String str = "";
							str = "=======liste des cas enregistrés=======\n";
							for (int i = 0; i < liste.size(); i++) {
								str = str + liste.get(i).toString() + "\n";
							}
							System.out.println(str);
							for (int i = 0; i < liste.size(); i++) {
						%><td><%=liste.get(i).getId_cas()%>
							<form action="controleur" method="post">
								<input type="hidden" name="id_casToSearch"
									value="<%=liste.get(i).getId_cas()%>" /> <input type="hidden"
									name="action" value="testsDuCas" /> <input type="submit"
									value="tests" />
							</form></td>
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
				</table></td>
			<td></td>
			<td>
				<center>
					<h2>gestion des tests</h2>
				</center>
				<form action="controleur" method="post">
					<input type="hidden" name="action" value="ajouterTestPcr" /> <input
						type="submit" value="ajouter un testPcr" />
				</form>
				<table class="table">
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
				</table>
			</td>
		</tr>
		<tr></tr>
		<tr><%if (request.getAttribute("id_casToSearch") != null){%>
			<td>
				<center>
					<h2>
						détail des tests du cas
						<%=request.getAttribute("id_casToSearch")%></h2>
				</center>
				<center>
					<table class="table">
						<tr>
							<th>id</th>
							<th>jour</th>
							<th>mois</th>
							<th>annee</th>
							<th>cas testé</th>
							<th>resultat</th>
						</tr>
						<%
							ArrayList<TestPcr> listec = new ArrayList<TestPcr>();
						String id_cas = (String) request.getAttribute("id_casToSearch");

						try {
							listec = TestPcrDAO.getListe();
							String str1 = "";
							str1 = "=======liste des testsPcr enregistrés pour un cas=======\n";
							for (int i = 0; i < listec.size(); i++) {
								String strc = listec.get(i).getId_cas() + "";
								if (strc.equals(id_cas)) {
							str1 = str1 + listec.get(i).toString() + "\n";
								}
							}
							System.out.println(str1);
							for (int i = 0; i < listec.size(); i++) {
								String strc = listec.get(i).getId_cas() + "";
								if (strc.equals(id_cas)) {
						%><tr>
							<td><%=listec.get(i).getId_test()%></td>
							<%
								
							%><td><%=listec.get(i).getJour()%></td>
							<%
								
							%><td><%=listec.get(i).getMois()%></td>
							<%
								
							%><td><%=listec.get(i).getAnnee()%></td>
							<%
								
							%><td><%=listec.get(i).getId_cas()%></td>
							<%
								
							%><td><%=listec.get(i).getResultat()%></td>
						</tr>
						<%
							}
						}

						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						%>

					</table>
				</center>
			</td>
			<td></td>
			<td></td>
			<%} %>
		</tr>
	</table>
</center>
</body>
</html>