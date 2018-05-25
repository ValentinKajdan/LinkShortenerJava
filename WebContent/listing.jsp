<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Listing Links</title>
<style>
	table {
		border: 1px solid black;
		border-collapse: collapse;
		margin-bottom: 15px;
	}
	td, th {
		border: 1px solid black;
		padding: 10px 15px;
		text-align: center;
	}
</style>
</head>
<body>

<%@include file="header.jsp" %>

<h2>Bienvenue <%= request.getAttribute("username") %></h2>

<h3>Liste des liens</h3>

<table>
	<tr>
		<th>URL</th>
		<th>short URL</th>
	</tr>
<c:forEach items="${links}" var="link">
    <tr>
        <td>${link[0]}</td>
        <td>
        	<a href="${link[1]}">${link[1]}</a>
		</td>
    </tr>
</c:forEach>
</table>

<a href="/urlshortener/add"><button>Add user</button></a>

</body>
</html>