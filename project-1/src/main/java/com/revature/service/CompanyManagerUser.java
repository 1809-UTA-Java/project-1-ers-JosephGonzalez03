package com.revature.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import com.revature.model.ErsUser;
import com.revature.model.Reimbursement;
import com.revature.model.ReimbursementStatus;
import com.revature.repository.ErsUserDao;
import com.revature.repository.ReimbursementDao;
import com.revature.repository.ReimbursementStatusDao;
import com.revature.util.HibernateUtil;

public class CompanyManagerUser extends ErsUser implements Manager {

	public CompanyManagerUser(ErsUser u) {
		super(u.getId(), u.getUsername(), u.getPassword(), u.getFirstName(), u.getLastName(), u.getEmail(),
				u.getUserRole());
	}

	@Override
	public boolean reviewPendingReimbursementReq(int rmbmtId, String reviewOption) {

		// load reimbursement under review
		ReimbursementDao dao = HibernateUtil.getReimbursementDao();
		Reimbursement rmbmt = dao.getReimbursementById(rmbmtId);
		System.out.println("" + rmbmt.getId());
		// load reimbursement status from database
		ReimbursementStatusDao rsDao = HibernateUtil.getRimbursementStatusDao();
		ReimbursementStatus rs = rsDao.getReimbursementStatusByStatus(reviewOption);
		rmbmt.setReimbursementStatus(rs);
		rmbmt.setResolverId(this.getId());
		
		// save time submitted as current time when method is executed
		LocalDateTime time = LocalDateTime.now();
		rmbmt.setResolved_ts(Timestamp.valueOf(time));

		return dao.updateReimbursement(rmbmt);
	}

	@Override
	public boolean viewImage(Reimbursement rmbmt) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Reimbursement> viewAllPendingReimbursementReqs() {
		ReimbursementDao dao = HibernateUtil.getReimbursementDao();
		return dao.getReimbursementsByStatus("pending");
	}

	@Override
	public List<Reimbursement> viewAllResolvedReimbursementReqs() {
		ReimbursementDao dao = HibernateUtil.getReimbursementDao();
		return dao.getReimbursementsByStatus("resolved");
	}

	@Override
	public List<ErsUser> viewAllEmployees() {
		ErsUserDao dao = HibernateUtil.getErsUserDao();
		return dao.getErsUsersByRole("employee");
	}

	@Override
	public List<Reimbursement> viewReimbursementReqs(ErsUser user) {
		ReimbursementDao dao = HibernateUtil.getReimbursementDao();
		return dao.getReimbursementsByUsername(user.getUsername());
	}

}
