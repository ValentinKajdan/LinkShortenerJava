<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
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

<h3>Liste des utilisateurs</h3>

<table>
	<tr>
		<th>ID</th>
		<th>username</th>
		<th>password</th>
		<th>Delete</th>
	</tr>
<c:forEach items="${users}" var="user">
    <tr>
        <td>${user[0]}</td>
        <td>${user[1]}</td>
        <td>${user[2]}</td>
        <td>
        	<a href="/FirstServlet/delete?id=${user[0]}">X</a>
		</td>
    </tr>
</c:forEach>
</table>

<a href="/FirstServlet/add"><button>Add user</button></a>

</body>
</html>