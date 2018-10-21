<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
<head>
<link rel="stylesheet" href="styles/custom.css">
<meta charset="ISO-8859-1">
<title>MANAGER HOMEPAGE</title>
</head>
<body>
	<h1>MANAGER HOMEPAGE</h1>
	<ul>
		<li><a href="ViewAllEmployees">Look Up Reimbursements by Employee</a></li>
		<li><a href="ViewPendingReimbursements">Review Reimbursements</a></li>
	</ul>

	<form action="Logout" method="post">
		<div>
			<button type="submit">Logout</button>
		</div>
	</form>
</body>
</html>