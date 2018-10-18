package com.revature.service;

import javax.servlet.http.HttpServletRequest;

import com.revature.model.ErsUser;
import com.revature.model.Reimbursement;
import com.revature.model.User;

public interface Manager {
	boolean reviewPendingReimbursementReqs(HttpServletRequest req);
	boolean viewImage(Reimbursement rmbmt);
	boolean viewAllPendingReimbursementReqs(HttpServletRequest req);
	boolean viewAllResolvedReimbursementReqs(HttpServletRequest req);
	boolean viewAllEmployees(HttpServletRequest req);
	boolean viewReimbursementReqs(HttpServletRequest req, ErsUser user);
}
