<html>
<head>
<title>Reimbursement Form</title>
<link rel="stylesheet" type="text/css" href="styles/custom.css">
</head>

<body>
	<h1>REIMBURSEMENT FORM</h1>

	<form action="SubmitReimbursementForm" method="post">
		<table>
			<tr>
				<td>Amount:</td>
				<td><input type="text" name="amount" required="required"></td>
			</tr>
			<tr>
				<td>Description:</td>
				<td><input type="text" name="description" required="required"></td>
			</tr>
			<tr>
				<td>Receipt:</td>
				<td><input type="file" name="receipt"></td>
			</tr>
			<tr>
				<td>Reimbursement Type:</td>
				<td><select name="type">
						<option value="travel">travel</option>
						<option value="supplies">supplies</option>
						<option value="meals">meals</option>
						<option value="hotel">hotel</option>
				</select></td>
			</tr>

		</table>
		<button formaction="SubmitReimbursementForm" type="submit">Submit</button>
	</form>
</body>
</html>