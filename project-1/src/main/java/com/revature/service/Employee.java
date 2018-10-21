package com.revature.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.Part;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.revature.model.ErsUser;
import com.revature.model.Reimbursement;

public interface Employee {
	void submitReimbursementReq(double amount, String description, Part receipt, String type) throws IOException;

	boolean uploadImage(Part content, Reimbursement rmbmt) throws IOException;

	List<Reimbursement> viewPendingReimbursementReqs() throws JsonProcessingException, IOException;

	List<Reimbursement> viewResolvedReimbursementReqs() throws JsonProcessingException, IOException;

	ErsUser viewInfo();

	boolean updateInfo(ErsUser u);
}
