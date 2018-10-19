package com.revature.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.revature.model.ErsUser;
import com.revature.model.Reimbursement;
import com.revature.model.ReimbursementStatus;
import com.revature.repository.ErsUserDao;
import com.revature.repository.ReimbursementDao;
import com.revature.repository.ReimbursementStatusDao;
import com.revature.util.HibernateUtil;

public class CompanyManagerUser extends ErsUser implements Manager {

	@Override
	public boolean reviewPendingReimbursementReqs(HttpServletRequest req) {
		int rmbmtId = Integer.parseInt(req.getParameter("rmbmtId"));
		String reviewOption = req.getParameter("reviewOption");
		
		// load reimbursement under review
		ReimbursementDao dao = HibernateUtil.getReimbursementDao();
		Reimbursement rmbmt = dao.getReimbursementById(rmbmtId);
		
		// load reimbursement status from database
		ReimbursementStatusDao rsDao = HibernateUtil.getRimbursementStatusDao();
		ReimbursementStatus rs = rsDao.getReimbursementStatusByStatus(reviewOption);
		rmbmt.setReimbursementStatus(rs);
		
		// update reimbursement in database
		return dao.updateReimbursement(rmbmt);
	}

	@Override
	public boolean viewImage(Reimbursement rmbmt) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean viewAllPendingReimbursementReqs(HttpServletRequest req) {
		ReimbursementDao dao = HibernateUtil.getReimbursementDao();
		List<Reimbursement> rmbmtList = dao.getReimbursementsByStatus("pending");
		
		if (rmbmtList.isEmpty()) {
			return false;
		} else {
			req.getSession().setAttribute("pendingList", rmbmtList);
			return true;
		}
	
	}

	@Override
	public boolean viewAllResolvedReimbursementReqs(HttpServletRequest req) {
		ReimbursementDao dao = HibernateUtil.getReimbursementDao();
		List<Reimbursement> rmbmtList = dao.getReimbursementsByStatus("resolved");
		
		if (rmbmtList.isEmpty()) {
			return false;
		} else {
			req.getSession().setAttribute("resolvedList", rmbmtList);
			return true;
		}
	}

	@Override
	public boolean viewAllEmployees(HttpServletRequest req) {
		ErsUserDao dao = HibernateUtil.getErsUserDao();
		List<ErsUser> usersList = dao.getAllUsers();
		
		if (usersList.isEmpty()) {
			return false;
		} else {
			req.getSession().setAttribute("users", usersList);
			return true;
		}
	}

	@Override
	public boolean viewReimbursementReqs(HttpServletRequest req, ErsUser user) {
		ReimbursementDao dao = HibernateUtil.getReimbursementDao();
		List<Reimbursement> rmbmtList = dao.getReimbursementsByUsernameAndStatus(user.getUsername(),"resolved");
		
		rmbmtList.addAll(dao.getReimbursementsByUsernameAndStatus(user.getUsername(),"pending"));
		
		if (rmbmtList.isEmpty()) {
			return false;
		} else {
			req.getSession().setAttribute("requestList", rmbmtList);
			return true;
		}
	}

}
