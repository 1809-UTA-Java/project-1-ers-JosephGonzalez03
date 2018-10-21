package com.revature.repository;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.model.ErsUser;
import com.revature.model.Reimbursement;
import com.revature.util.HibernateUtil;

public class ReimbursementDao {
	@SuppressWarnings("unchecked")
	public List<Reimbursement> getReimbursementsByUsername(String username) {
		Session session = HibernateUtil.getSession();
		String hql = "from Reimbursement r where r.author.username = :username";
		Query query = session.createQuery(hql);
		query.setString("username", username);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Reimbursement> getReimbursementsByStatus(String status) {
		Session session = HibernateUtil.getSession();
		String hql = "from Reimbursement r where r.reimbursementStatus.status = :status";
		Query query = session.createQuery(hql);
		query.setString("status", status);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Reimbursement> getReimbursementsByUsernameAndStatus(String username, String status) {
		Session session = HibernateUtil.getSession();
		String hql = "from Reimbursement r where r.author.username = :username and r.reimbursementStatus.status = :status";
		Query query = session.createQuery(hql);
		query.setString("username", username);
		query.setString("status", status);
		return query.list();
		}
	
	public Reimbursement getReimbursementById(int id) {
		Session session = HibernateUtil.getSession();
		String hql = "from Reimbursement where id = :idVar";
		return (Reimbursement) session.createQuery(hql).setInteger("idVar",id).uniqueResult();
		
//		return (Reimbursement) session.createCriteria(Reimbursement.class)
//				.add(Restrictions.eq("id", id))
//				.uniqueResult();
	}
	
	public int saveReimbursement(Reimbursement r) {
		Session session = HibernateUtil.getSession();
//		Transaction tx = session.beginTransaction();
		int result = (int) session.save(r);
//		tx.commit();
		return result;
	}
	
	public boolean updateReimbursement(Reimbursement r) {
		Session session = HibernateUtil.getSession();
		String hql = "update Reimbursement set resolver = :rs, " +
					 "reimbursementStatus = :newStatus " + 
					"where id = :id";
		Query query = session.createQuery(hql);
		query.setEntity("rs", r.getResolver());
		query.setEntity("newStatus", r.getReimbursementStatus());
		query.setInteger("id", r.getId());
		
		if(query.executeUpdate() > 0) {
			return true;
		} else {
			return false;
		}
	}
}
