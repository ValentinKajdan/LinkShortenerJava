<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add user</title>
<style>
	.error {
		color: red;
	}
	a {
		margin-bottom: 15px;
	}
	a, a:focus, a:hover, a:visited {
		color: black;
	}
</style>
</head>
<body>

<%@include file="header.jsp" %>

<h3>Add user</h3>

<a href="/urlshortener/home" >Back Home</a>

<%  if (request.getAttribute("addError") != null) { %>

	<p class="error"><%= request.getAttribute("addError") %></p>
	
<% } %>

<form action="/urlshortener/add" method="POST" >
	<input name="login" type="text" placeholder="Identifiant"></input>
	<input name="password" type="password" placeholder="Mot de passe"></input>
	<input type="submit" value="Ajouter"></input>
</form>

</body>
</html>