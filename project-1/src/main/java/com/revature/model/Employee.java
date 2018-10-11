package com.revature.model;

import java.awt.Image;
import java.util.Scanner;

public interface Employee extends User {
	boolean submitReimbursementReq(Reimbursement rbmt);
	boolean uploadImage(Image img);
	boolean viewPendingReimbursementReqs();
	boolean viewResolvedReimbursementReqs();
	boolean viewInfo();
	boolean updateInfo(Scanner s);
}
