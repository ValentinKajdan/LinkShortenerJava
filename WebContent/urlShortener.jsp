<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Url Shortener</title>
</head>
<body>
<% if(request.getAttribute("url") != null) { %>
		<p style="color: red"><%= request.getAttribute("url") %></p>
	<% } %>
	<form action="urlshortener/url" method="post">
		<label for="base_url">Url to shorten :</label>
		<input type="text" name="base_url"><br><br>
		<button type="submit">valider</button>
	</form>
</body>
</html>