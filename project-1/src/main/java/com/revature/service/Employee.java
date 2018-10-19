package com.revature.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.revature.model.Reimbursement;

public interface Employee {
	void submitReimbursementReq(HttpServletRequest req) throws IOException;

	boolean uploadImage(Part content, Reimbursement rmbmt) throws IOException;

	boolean viewPendingReimbursementReqs(HttpServletRequest req, HttpServletResponse resp)
			throws JsonProcessingException, IOException;

	boolean viewResolvedReimbursementReqs(HttpServletRequest req, HttpServletResponse resp)
			throws JsonProcessingException, IOException;

	void viewInfo(HttpServletRequest req);

	boolean updateInfo(HttpServletRequest req);
}
