package com.revature.repository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.model.ErsUser;
import com.revature.util.HibernateUtil;

public class ErsUserDao {

	@SuppressWarnings("unchecked")
	public List<ErsUser> getAllUsers() {
		Session session = HibernateUtil.getSession();
		return session.createCriteria(ErsUser.class).list();
	}

	@SuppressWarnings("unchecked")
	public ErsUser getErsUserByUsername(String username) {
		Session session = HibernateUtil.getSession();

		List<ErsUser> users = new ArrayList<>();

		users = session.createQuery("from ErsUser where username = :username").setString("username", username).list();
		return users.isEmpty() ? null : users.get(0);
	}

	@SuppressWarnings("unchecked")
	public List<ErsUser> getErsUsersByRole(String role) {
		Session session = HibernateUtil.getSession();
		return session.createQuery("select eu from ErsUser eu join eu.userRole ur where ur.role = :role")
				.setString("role", role).list();
	}

	
	public int saveUser(ErsUser u) {
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		int result = (int) session.save(u);
		tx.commit();
		return result;
	}

	public boolean updateErsUser(ErsUser u) {
		Session session = HibernateUtil.getSession();
		String hql = "update ErsUser set password = :password, " +
					 "set firstName = :first, " +
					 "set lastName = :last, " +
					 "set email = :email";
		Query query = session.createQuery(hql);
		query.setString("password", u.getPassword());
		query.setString("first", u.getFirstName());
		query.setString("last", u.getLastName());
		query.setString("email", u.getEmail());
		
		if(query.executeUpdate() > 0) {
			return true;
		} else {
			return false;
		}
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
