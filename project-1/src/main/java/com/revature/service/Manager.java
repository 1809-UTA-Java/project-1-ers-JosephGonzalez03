package com.revature.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.revature.model.ErsUser;
import com.revature.model.Reimbursement;
import com.revature.model.User;

public interface Manager {
	boolean reviewPendingReimbursementReq(ErsUser eu, int rmbmtId, String reviewOption);
	boolean viewImage(Reimbursement rmbmt);
	List<Reimbursement> viewAllPendingReimbursementReqs();
	List<Reimbursement> viewAllResolvedReimbursementReqs();
	List<ErsUser> viewAllEmployees();
	List<Reimbursement> viewReimbursementReqs(ErsUser user);
}
