<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.revature.model.Reimbursement"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

<%List<Reimbursement> resolvedList = (ArrayList<Reimbursement>) session.getAttribute("resolvedList");%>
<%List<Reimbursement> pendingList = (ArrayList<Reimbursement>) session.getAttribute("pendingList");%>
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
				<th>Resolved Reimbursements</th>
			</tr>
			<tr>
				<th>ID</th>
				<th>AMOUNT</th>
				<th>DESCRIPTION</th>
				<th>TIME SUBMITTED</th>
				<th>TIME RESOLVED</th>
				<th>RESOLVER</th>
				<th>TYPE</th>
			</tr>
		</thead>
		<tbody>
			<%
				for (Reimbursement rmbmt : resolvedList) {
			%>
			<tr>
				<td><%=rmbmt.getId()%></td>
				<td><%=rmbmt.getAmount()%></td>
				<td><%=rmbmt.getDescription()%></td>
				<td><%=rmbmt.getSubmitted_ts()%></td>
				<td><%=rmbmt.getResolved_ts()%></td>
				<td><%=rmbmt.getResolverId()%></td>
				<td><%=rmbmt.getReimbursementType().getType()%></td>
			</tr>
			<%
				}
			%>
		</tbody>
	</table>

	<br>
	<br>

	<table>
		<thead>
			<tr>
				<th>Pending Reimbursements</th>
			</tr>
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
				for (Reimbursement rmbmt : pendingList) {
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

	<br>
	<br>

	<form action="ReviewReimbursement" method="post">
		ID: <input type="text" name="rmbmtId" required="required"> <br>
		ACTION: <select name="reviewOption">
			<option value="approved">approve</option>
			<option value="denied">deny</option>
		</select> <br>
		<button type="submit">Submit</button>
	</form>


</body>
</html>