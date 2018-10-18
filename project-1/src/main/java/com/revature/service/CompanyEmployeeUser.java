package com.revature.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import com.revature.model.ErsUser;
import com.revature.model.Reimbursement;
import com.revature.repository.ErsUserDao;
import com.revature.repository.ReimbursementDao;
import com.revature.repository.ReimbursementStatusDao;
import com.revature.repository.ReimbursementTypeDao;
import com.revature.util.HibernateUtil;

public class CompanyEmployeeUser extends ErsUser implements Employee {

	@Override
	public void submitReimbursementReq(HttpServletRequest req) {
		String id = req.getParameter("id");
		String amount = req.getParameter("amount");
		String description = req.getParameter("description");
		String type = req.getParameter("type");

		ReimbursementTypeDao rtDao = HibernateUtil.getRimbursementTypeDao();
		ReimbursementStatusDao rsDao = HibernateUtil.getRimbursementStatusDao();

		// save everything except receipt image & author
		Reimbursement rmbmt = new Reimbursement();
		rmbmt.setId(Integer.parseInt(id));
		rmbmt.setAmount(Double.parseDouble(amount));
		rmbmt.setDescription(description);
		rmbmt.setAuthor(this);
		rmbmt.setReimbursementType(rtDao.getReimbursementTypeByType(type));
		rmbmt.setReimbursementStatus(rsDao.getReimbursementStatusByStatus("pending"));

		// save time submitted as current time when method is executed
		LocalDateTime time = LocalDateTime.now();
		rmbmt.setSubmitted_ts(Timestamp.valueOf(time));

		// save byte[] provided by user (if provided)
		boolean isSet = false;
		Part content = null;;
		
		try {
			content = req.getPart("content");
			isSet = true;
		} catch (Exception e) {
			isSet = false;
		}

		if (isSet) {
			uploadImage(content, rmbmt);
		}

		ReimbursementDao dao = HibernateUtil.getReimbursementDao();
		dao.saveReimbursement(rmbmt);
	}

	@Override
	public boolean uploadImage(Part content, Reimbursement rmbmt) {
		InputStream is = null;
		ByteArrayOutputStream os = null;
		boolean isSuccessfull = false;
		
		try {
			is = content.getInputStream();
			os = new ByteArrayOutputStream();
			
			byte[] buffer = new byte[1024];
			
			while (is.read(buffer) != -1) {
				os.write(buffer);
			}
			
			rmbmt.setRecipt(os.toByteArray());
			isSuccessfull = true;
		} catch (IOException e) {
			System.out.println("Could not upload file!");
			e.printStackTrace();
			isSuccessfull = false;
		} finally {
			try {
				if (is != null)
					is.close();
				if (os != null)
					os.close();
			} catch (IOException e) {
				e.getStackTrace();
				isSuccessfull = false;
			}
		}
		
		return isSuccessfull;
	}

	@Override
	public boolean viewPendingReimbursementReqs(HttpServletRequest req) {
		ReimbursementDao dao = HibernateUtil.getReimbursementDao();
		List<Reimbursement> rmbmtList = dao.getReimbursementsByUsernameAndStatus(getUsername(), "pending");

		if (rmbmtList.isEmpty()) {
			return false;
		} else {
			req.getSession().setAttribute("reimbursements", rmbmtList);
			return true;
		}
	}

	@Override
	public boolean viewResolvedReimbursementReqs(HttpServletRequest req) {
		ReimbursementDao dao = HibernateUtil.getReimbursementDao();
		List<Reimbursement> rmbmtList = dao.getReimbursementsByUsernameAndStatus(getUsername(), "resolved");

		if (rmbmtList.isEmpty()) {
			return false;
		} else {
			req.getSession().setAttribute("reimbursements", rmbmtList);
			return true;
		}
	}

	@Override
	public void viewInfo(HttpServletRequest req) {
		req.setAttribute("user", this);
	}

	@Override
	public boolean updateInfo(HttpServletRequest req) {
		ErsUserDao dao = HibernateUtil.getErsUserDao();

		String newPassword = req.getParameter("password");
		String newFirstName = req.getParameter("firstName");
		String newLastName = req.getParameter("lastName");
		String newEmail = req.getParameter("email");

		this.setPassword(newPassword);
		this.setFirstName(newFirstName);
		this.setLastName(newLastName);
		this.setEmail(newEmail);

		return dao.updateErsUser(this);
	}

}