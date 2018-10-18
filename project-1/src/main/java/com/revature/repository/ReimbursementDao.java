package com.revature.repository;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.revature.model.Reimbursement;
import com.revature.util.HibernateUtil;

public class ReimbursementDao {
	@SuppressWarnings("unchecked")
	public List<Reimbursement> getReimbursementsByStatus(String status) {
		Session session = HibernateUtil.getSession();
		return session.createCriteria(Reimbursement.class)
				.add(Restrictions.eq("ReimbursementStatus.status", status))
				.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Reimbursement> getReimbursementsByUsernameAndStatus(String username, String status) {
		Session session = HibernateUtil.getSession();
		return session.createCriteria(Reimbursement.class)
				.add(Restrictions.eq("username", username))
				.add(Restrictions.eq("ReimbursementStatus.status", status))
				.list();
	}
	
	public Reimbursement getReimbursementById(int id) {
		Session session = HibernateUtil.getSession();
		return (Reimbursement) session.createCriteria(Reimbursement.class)
				.add(Restrictions.eq("ReimbursementStatus.id", id))
				.list().get(0);
	}
	
	public int saveReimbursement(Reimbursement r) {
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		int result = (int) session.save(r);
		tx.commit();
		return result;
	}
	
	public boolean updateReimbursement(Reimbursement r) {
		Session session = HibernateUtil.getSession();
		String hql = "update Reimbursement set resolver = :resolver, " +
					 "reimbursementStatus = :newStatus" + 
					"where id = :id";
		Query query = session.createQuery(hql);
		query.setEntity("resolver", r.getResolver());
		query.setEntity("newStatus", r.getReimbursementStatus());
		query.setInteger("id", r.getId());
		
		if(query.executeUpdate() > 0) {
			return true;
		} else {
			return false;
		}
	}
}
