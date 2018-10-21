<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.revature.model.Reimbursement"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%List<Reimbursement> rList = (ArrayList<Reimbursement>) session.getAttribute("rList");%>
<%String username = (String) session.getAttribute("username"); %>
<html>
<head>
<meta charset="ISO-8859-1">
<title> Employee Reimbursements</title>
<link rel="stylesheet" href="styles/custom.css">
</head>
<body>
	<table>
		<thead>
			<tr>
				<th><%=username %>'s Reimbursements</th>
			</tr>
			<tr>
				<th>ID</th>
				<th>AMOUNT</th>
				<th>DESCRIPTION</th>
				<th>TIME SUBMITTED</th>
				<th>TIME RESOLVED</th>
				<th>RESOLVER</th>
				<th>TYPE</th>
				<th>STATUS</th>
			</tr>
		</thead>
		<tbody>
			<%
				for (Reimbursement rmbmt : rList) {
			%>
			<tr>
				<td><%=rmbmt.getId()%></td>
				<td><%=rmbmt.getAmount()%></td>
				<td><%=rmbmt.getDescription()%></td>
				<td><%=rmbmt.getSubmitted_ts()%></td>
				<td><%=rmbmt.getResolved_ts()%></td>
				<td><%=rmbmt.getResolverId()%></td>
				<td><%=rmbmt.getReimbursementType().getType()%></td>
				<td><%=rmbmt.getReimbursementStatus().getStatus()%></td>
			</tr>
			<%
				}
			%>
		</tbody>
	</table>
</body>
</html>