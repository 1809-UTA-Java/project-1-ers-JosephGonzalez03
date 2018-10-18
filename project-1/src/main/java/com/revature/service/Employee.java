package com.revature.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import com.revature.model.Reimbursement;

public interface Employee {
	void submitReimbursementReq(HttpServletRequest req);
	boolean uploadImage(Part content, Reimbursement rmbmt);
	boolean viewPendingReimbursementReqs(HttpServletRequest req);
	boolean viewResolvedReimbursementReqs(HttpServletRequest req);
	void viewInfo(HttpServletRequest req);
	boolean updateInfo(HttpServletRequest req);
}
