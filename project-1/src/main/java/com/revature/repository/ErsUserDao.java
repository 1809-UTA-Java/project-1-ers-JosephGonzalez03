package com.revature.repository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.model.ErsUser;
import com.revature.util.HibernateUtil;

/**
 * 
 * @author Joseph NOTE: comment out transaction stuff in saveUser or else
 *         rollback won't work & tests will fail
 */
public class ErsUserDao {

	@SuppressWarnings("unchecked")
	public List<ErsUser> getAllUsers() {
		Session session = HibernateUtil.getSession();
		List<ErsUser> out = session.createCriteria(ErsUser.class).list();
		session.close();
		return out;
	}

	@SuppressWarnings("unchecked")
	public ErsUser getErsUserByUsername(String username) {
		Session session = HibernateUtil.getSession();

		List<ErsUser> users = new ArrayList<>();

		users = session.createQuery("from ErsUser where username = :username").setString("username", username).list();
		session.close();
		return users.isEmpty() ? null : users.get(0);
	}

	@SuppressWarnings("unchecked")
	public List<ErsUser> getErsUsersByRole(String role) {
		Session session = HibernateUtil.getSession();
		List<ErsUser> out = session.createQuery("from ErsUser eu where eu.userRole.role = :role").setString("role", role).list();
		session.close();
		return out;
	}

	public int saveUser(ErsUser u) {
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		int result = (int) session.save(u);
		tx.commit();
		return result;
	}

	public int saveUserTest(ErsUser u) {
		Session session = HibernateUtil.getSession();
		int result = (int) session.save(u);
		return result;
	}
	
	public boolean updateErsUser(ErsUser u) {
		Session session = HibernateUtil.getSession();
		String hql = "update ErsUser set password = :password, " 
				+ "firstName = :first, " 
				+ "lastName = :last, "
				+ "email = :email where id = :idVar";
		Query query = session.createQuery(hql);
		query.setString("password", u.getPassword());
		query.setString("first", u.getFirstName());
		query.setString("last", u.getLastName());
		query.setString("email", u.getEmail());
		query.setInteger("idVar", u.getId());

		if (query.executeUpdate() > 0) {
			return true;
		} else {
			return false;
		}
	}

}
