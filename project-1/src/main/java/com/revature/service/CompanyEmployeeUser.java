package com.revature.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.Part;

import com.revature.model.ErsUser;
import com.revature.model.Reimbursement;
import com.revature.repository.ErsUserDao;
import com.revature.repository.ReimbursementDao;
import com.revature.util.HibernateUtil;

public class CompanyEmployeeUser extends ErsUser implements Employee {


	public CompanyEmployeeUser(ErsUser u) {
		super(u.getId(), u.getUsername(), u.getPassword(), u.getFirstName(), u.getLastName(), u.getEmail(), u.getUserRole());
	}

	@Override
	public void submitReimbursementReq(Reimbursement rmbmt) {
		ReimbursementDao dao = HibernateUtil.getReimbursementDao();
		dao.saveReimbursement(rmbmt);
	}

	@Override
	public boolean uploadImage(Part content, Reimbursement rmbmt) throws IOException {
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
			if (is != null)
				is.close();
			if (os != null)
				os.close();
		}

		return isSuccessfull;
	}

	@Override
	public List<Reimbursement> viewPendingReimbursementReqs() {
		ReimbursementDao dao = HibernateUtil.getReimbursementDao();
		return dao.getReimbursementsByUsernameAndStatus(this.getUsername(), "pending");
	}

	@Override
	public List<Reimbursement> viewResolvedReimbursementReqs() {
		ReimbursementDao dao = HibernateUtil.getReimbursementDao();
		List<Reimbursement> rmbmtList = dao.getReimbursementsByUsernameAndStatus(this.getUsername(), "approved");
		rmbmtList.addAll(dao.getReimbursementsByUsernameAndStatus(this.getUsername(), "denied"));
		return rmbmtList;
	}

	@Override
	public ErsUser viewInfo() {
		return this;
	}

	@Override
	public void updateInfo(ErsUser u) {
		ErsUserDao dao = HibernateUtil.getErsUserDao();

		this.setPassword(u.getPassword());
		this.setFirstName(u.getFirstName());
		this.setLastName(u.getLastName());
		this.setEmail(u.getEmail());

		dao.updateErsUser(this);
	}

}
