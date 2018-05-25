<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Connexion</title>
<style>
	.error {
		color: red;
	}
</style>
</head>
<body>

<h3>Connexion</h3>

<%  if (request.getAttribute("error") != null) { %>

	<p class="error"><%= request.getAttribute("error") %></p>
	
<% } %>

<form action="connexion" method="POST" >
	<input name="login" type="text" placeholder="Identifiant"></input>
	<input name="password" type="password" placeholder="Mot de passe"></input>
	<input type="submit" value="Connexion"></input>
</form>

</body>
</html>