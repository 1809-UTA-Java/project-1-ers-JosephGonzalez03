<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
				<td>${user.username}</td>
			</tr>
			<tr>
				<td>Password:</td>
				<td>${user.password}</td>
			</tr>

			<tr>
				<td>First Name:</td>
				<td>${user.firstName}</td>
			</tr>

			<tr>
				<td>Last Name:</td>
				<td>${user.lastName}</td>
			</tr>

			<tr>
				<td>Email:</td>
				<td>${user.email}</td>
			</tr>
		</tbody>
	</table>

	<br>
	<br>

	<h1>UPDATE FORM</h1>
	--ADD SERVLET LATER
	<form action="" method="post">
		<table>
			<tr>
				<th>New</th>
				<th>Value</th>
			</tr>

			<tr>
				<td>User Name:</td>
				<td><input type="text" name="userName" required="required"></td>
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