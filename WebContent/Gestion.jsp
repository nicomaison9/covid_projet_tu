<%@page import="org.apache.tomcat.util.security.Escape"%>
<%-- <%@page import="DAO.CasDAO"%> --%>
<%@page import="DAO.CasDAO" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%-- <jsp:useBean id="lcas"
		class="DAO.CasDAO" scope="page"></jsp:useBean>
	<jsp:setProperty property="*" name="lcas" /> --%>
<%-- <table>
<c:forEach items="${lcas.getListeId_cas()}" var="a">
<tr>
<td>${a.id_cas}</td>
<td>${a.nom_complet}</td>
<td>${a.telephone}</td>
<td>${a.adresse}</td>
<td>${a.code_postal}</td>
<td>${a.etat}</td>
</tr>
</c:forEach>
</table> --%>
</body>
</html>