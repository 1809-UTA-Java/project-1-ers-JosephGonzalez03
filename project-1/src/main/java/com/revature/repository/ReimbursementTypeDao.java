package com.revature.repository;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.revature.model.ReimbursementType;
import com.revature.util.HibernateUtil;

public class ReimbursementTypeDao {
	public ReimbursementType getReimbursementTypeByType(String type) {
		Session session = HibernateUtil.getSession();
		ReimbursementType out = (ReimbursementType) session.createCriteria(ReimbursementType.class)
				.add(Restrictions.eq("type", type))
				.uniqueResult();
		session.close();
		return out;
	}
}
