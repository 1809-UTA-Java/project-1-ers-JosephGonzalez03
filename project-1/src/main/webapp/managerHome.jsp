<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>MANAGER HOMEPAGE</title>
</head>
<body>
	<h1>EMPLOYEE HOMEPAGE</h1>
	<ul>
		<li><a href="allEmployees.jsp">View All Employees</a></li>
		<li><a href="resolvedReimbursmentHome.jsp">View Resolved Reimbursements</a></li>
		<li><a href="pendingReimbursementHome.jsp">Review Pending Reimbursements</a></li>
	</ul>

	<form action="Logout" method="post">
		<div>
			<button type="submit">Logout</button>
		</div>
	</form>
</body>
</html>