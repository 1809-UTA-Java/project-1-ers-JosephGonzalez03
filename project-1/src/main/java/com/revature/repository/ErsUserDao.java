package com.revature.repository;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.model.ErsUser;
import com.revature.util.HibernateUtil;

/**
 * 
 * @author Joseph
 * NOTE: comment out transaction stuff in saveUser or 
 * else rollback won't work & tests will fail
 */
public class ErsUserDao {

	@SuppressWarnings("unchecked")
	public List<ErsUser> getAllUsers() {
		Session session = HibernateUtil.getSession();
		return session.createCriteria(ErsUser.class).list();
	}

	public ErsUser getErsUserByUsername(String username) {
		Session session = HibernateUtil.getSession();
		return (ErsUser) session.createQuery("from ErsUser where username= :uname")
				.setString("uname", username).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<ErsUser> getErsUsersByRole(String role) {
		Session session = HibernateUtil.getSession();
		return session.createQuery("from ErsUser eu where eu.userRole.role = :role")
				.setString("role", role).list();
	}

	
	public int saveUser(ErsUser u) {
		Session session = HibernateUtil.getSession();
//		Transaction tx = session.beginTransaction();
		int result = (int) session.save(u);
//		tx.commit();
		return result;
	}

	public void updateErsUser(ErsUser u) {
		Session session = HibernateUtil.getSession();
//		Transaction tx = session.beginTransaction();
		session.update(u);
//		tx.commit();
	}

	public boolean deleteUser(ErsUser u) {
		Session session = HibernateUtil.getSession();
		String hql = "delete ErsUser where id = :id";
		Query query = session.createQuery(hql);
		query.setInteger("id", u.getId());
		
		if(query.executeUpdate() > 0) {
			return true;
		} else {
			return false;
		}
	}

}
