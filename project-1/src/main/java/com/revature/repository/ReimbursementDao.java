package com.revature.repository;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.model.Reimbursement;
import com.revature.util.HibernateUtil;

public class ReimbursementDao {
	@SuppressWarnings("unchecked")
	public List<Reimbursement> getReimbursementsByUsername(String username) {
		Session session = HibernateUtil.getSession();
		String hql = "from Reimbursement r where r.author.username = :username";
		Query query = session.createQuery(hql);
		query.setString("username", username);
		List<Reimbursement> out = query.list();
		session.close();
		return out;
	}

	@SuppressWarnings("unchecked")
	public List<Reimbursement> getReimbursementsByStatus(String status) {
		Session session = HibernateUtil.getSession();
		String hql = "from Reimbursement r where r.reimbursementStatus.status = :status";
		Query query = session.createQuery(hql);
		query.setString("status", status);
		List<Reimbursement> out = query.list();
		session.close();
		return out;
	}

	@SuppressWarnings("unchecked")
	public List<Reimbursement> getReimbursementsByUsernameAndStatus(String username, String status) {
		Session session = HibernateUtil.getSession();
		String hql = "from Reimbursement r where r.author.username = :username and r.reimbursementStatus.status = :status";
		Query query = session.createQuery(hql);
		query.setString("username", username);
		query.setString("status", status);
		List<Reimbursement> out = query.list();
		session.close();
		return out;
	}

	public Reimbursement getReimbursementById(int id) {
		Session session = HibernateUtil.getSession();
		String hql = "from Reimbursement where id = :idVar";
		Reimbursement out = (Reimbursement) session.createQuery(hql).setInteger("idVar", id).uniqueResult();
		session.close();
		return out;
//		return (Reimbursement) session.createCriteria(Reimbursement.class)
//				.add(Restrictions.eq("id", id))
//				.uniqueResult();
	}

	public int saveReimbursement(Reimbursement r) {
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		int result = (int) session.save(r);
		tx.commit();
		return result;
	}

	public int saveReimbursementTest(Reimbursement r) {
		Session session = HibernateUtil.getSession();
		int result = (int) session.save(r);
		return result;
	}

	public boolean updateReimbursement(Reimbursement r) {
		Session session = HibernateUtil.getSession();
		String hql = "update Reimbursement set resolverId = :rId, " + "reimbursementStatus = :newStatus "
				+ "where id = :idVar";
		Query query = session.createQuery(hql);
		query.setInteger("rId", r.getResolverId());
		query.setEntity("newStatus", r.getReimbursementStatus());
		query.setInteger("idVar", r.getId());

		if (query.executeUpdate() > 0) {
			session.close();
			return true;
		} else {
			session.close();
			return false;
		}
	}
}
