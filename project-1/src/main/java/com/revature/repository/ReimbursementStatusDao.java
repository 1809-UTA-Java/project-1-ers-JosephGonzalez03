package com.revature.repository;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.revature.model.ReimbursementStatus;
import com.revature.util.HibernateUtil;

public class ReimbursementStatusDao {
	public ReimbursementStatus getReimbursementStatusByStatus(String status) {
		Session session = HibernateUtil.getSession();
		ReimbursementStatus out = (ReimbursementStatus) session.createCriteria(ReimbursementStatus.class)
				.add(Restrictions.eq("status", status))
				.uniqueResult();
		session.close();
		return out;
	}
}
