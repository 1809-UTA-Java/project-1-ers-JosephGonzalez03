package com.revature.model;

public interface Manager {
	boolean reviewPendingReimbursementReqs();
	boolean viewImage(Reimbursement rmbmt);
	boolean viewAllPendingReimbursementReqs();
	boolean viewAllResolvedReimbursementReqs();
	boolean viewAllEmployees();
	boolean viewReimbursementReqs(Employee emply);
}
