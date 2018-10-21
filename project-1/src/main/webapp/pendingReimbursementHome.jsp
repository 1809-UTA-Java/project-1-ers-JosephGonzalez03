<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.revature.model.Reimbursement"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

<%
	List<Reimbursement> pendingList = (ArrayList<Reimbursement>) session.getAttribute("pendingList");
%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>PENDING REIMBURSEMENTS</title>
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
				<th>ACTION</th>
				<th>SUBMIT</th>
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
				<td><%=%></td>
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

	<form action="ReviewReimbursement" method="post">
		<table>
			<tr>
				<td>ID:</td>
				<td><input type="text" name="rmbmtId" required="required"></td>
			</tr>
			<tr>
				<td>ACTION:</td>
				<td><select name="reviewOption">
						<option value="approved">approve</option>
						<option value="denied">deny</option>
				</select></td>
			</tr>

		</table>
	</form>

	<button type="submit">Update</button>

</body>
</html>