<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.revature.model.ErsUser"%>
<%ErsUser user = (ErsUser) session.getAttribute("user"); %>
	
<html>

<head>
<title>Personal Information</title>
<link rel="stylesheet" type="text/css" href="styles/custom.css">
</head>
<body>
	<h1>PERSONAL INFORMATION</h1>
	<table>
		<thead>
			<tr>
				<th>FIELDS</th>
				<th>INFO</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>User Name:</td>
				<td><%=user.getUsername() %></td>
			</tr>
			<tr>
				<td>First Name:</td>
				<td><%=user.getFirstName() %></td>
			</tr>

			<tr>
				<td>Last Name:</td>
				<td><%=user.getLastName() %></td>
			</tr>

			<tr>
				<td>Email:</td>
				<td><%=user.getEmail() %></td>
			</tr>
		</tbody>
	</table>

	<br>
	<br>

	<h1>UPDATE FORM</h1>
	<form action="/project-1/UpdatePersonalInfo" method="post">
		<table>
			<tr>
				<th>New</th>
				<th>Value</th>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type="text" name="password" required="required"></td>
			</tr>
			<tr>
				<td>First Name:</td>
				<td><input type="text" name="firstName" required="required"></td>
			</tr>
			<tr>
				<td>Last Name:</td>
				<td><input type="text" name="lastName" required="required"></td>
			</tr>
			<tr>
				<td>Email:</td>
				<td><input type="email" name="email" required="required"></td>
			</tr>
		</table>
		<button type="submit">Update</button>
	</form>
</body>
</html>