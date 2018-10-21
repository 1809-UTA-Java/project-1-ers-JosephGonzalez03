<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.revature.model.Reimbursement"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

<%
	List<Reimbursement> resolvedList = (ArrayList<Reimbursement>) session.getAttribute("resolvedList");
%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Pending Reimbursements</title>
<link rel="stylesheet" href="styles/custom.css">
</head>
<body>

	<table>
		<thead>
			<tr>
				<th>ID</th>
				<th>AMOUNT</th>
				<th>DESCRIPTION</th>
				<th>RECEIPT</th>
				<th>TIME SUBMITTED</th>
				<th>TIME RESOLVED</th>
				<th>EMPLOYEE</th>
				<th>TYPE</th>
			</tr>
		</thead>
		<tbody>

			<!--foreach to generate full table-->
			<%
				for (Reimbursement rmbmt : resolvedList) {
			%>
			<tr>
				<td><%=rmbmt.getId()%></td>
				<td><%=rmbmt.getAmount()%></td>
				<td><%=rmbmt.getDescription()%></td>
				<td><%=rmbmt.getRecipt()%></td>
				<td><%=rmbmt.getSubmitted_ts()%></td>
				<td><%=rmbmt.getResolved_ts()%></td>
				<td><%=rmbmt.getAuthor().getUsername()%></td>
				<td><%=rmbmt.getReimbursementType().getType()%></td>
			</tr>
			<%
				}
			%>
		</tbody>
	</table>
</body>
</html>