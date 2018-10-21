<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.revature.model.ErsUser"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

<%
	List<ErsUser> uList = (ArrayList<ErsUser>) session.getAttribute("users");
%>
<html>
<head>
<link rel="stylesheet" href="styles/custom.css">
<meta charset="ISO-8859-1">
<title>EMPLOYEES DIRECTORY</title>
</head>
<body>

	<table>
		<thead>
			<tr>
				<th>ID</th>
				<th>USERNAME</th>
				<th>FIRST NAME</th>
				<th>LAST NAME</th>
				<th>EMAIL</th>
			</tr>
		</thead>
		<tbody>

			<!--foreach to generate full table-->
			<%
				for (ErsUser u : uList) {
			%>
			<tr>
				<td><%=u.getId()%></td>
				<td><%=u.getUsername()%></td>
				<td><%=u.getFirstName()%></td>
				<td><%=u.getLastName()%></td>
				<td><%=u.getEmail()%></td>
			</tr>
			<%
				}
			%>
		</tbody>
	</table>

	<form action="PullEmployeeRequests" method="get">
		Pull Reimbursement Requests for: <input type="text" name="username"
			required="required" placeholder="employee username">
		<button type="submit">Search</button>
	</form>

</body>
</html>