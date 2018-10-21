<%@page import="com.revature.model.Reimbursement"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

<%List<Reimbursement> pendingList = (ArrayList<Reimbursement>) session.getAttribute("pendingList");%>
<%List<Reimbursement> resolvedList = (ArrayList<Reimbursement>) session.getAttribute("resolvedList");%>
<html>
<head>
<title>Reimbursement Home</title>
<link rel="stylesheet" type="text/css" href="styles/custom.css">
</head>
<body>
	<h1>EMPLOYEE REIMBURSEMENTS</h1>
	<table>
		<thead>
			<tr>
				<th>Pending Reimbursements</th>
			</tr>
			<tr>
				<th>ID</th>
				<th>AMOUNT</th>
				<th>DESCRIPTION</th>
				<th>TIME SUBMITTED</th>
				<th>TIME RESOLVED</th>
				<th>TYPE</th>
			</tr>
		</thead>
		<tbody>
			<%
				for (Reimbursement rmbmt : pendingList) {
			%>
			<tr>
				<td><%=rmbmt.getId()%></td>
				<td><%=rmbmt.getAmount()%></td>
				<td><%=rmbmt.getDescription()%></td>
				<td><%=rmbmt.getSubmitted_ts()%></td>
				<td><%=rmbmt.getResolved_ts()%></td>
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
				<th>Resolved Reimbursements</th>
			</tr>
			<tr>
				<th>ID</th>
				<th>AMOUNT</th>
				<th>DESCRIPTION</th>
				<th>TIME SUBMITTED</th>
				<th>TIME RESOLVED</th>
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
				<td><%=rmbmt.getReimbursementType().getType()%></td>
			</tr>
				<%
					}
				%>
		</tbody>
	</table>

</body>
</html>