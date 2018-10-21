package com.revature.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.model.ErsUser;
import com.revature.model.UserRole;
import com.revature.repository.ErsUserDao;
import com.revature.util.HibernateUtil;

/**
 * 
 * @author Joseph
 * NOTE: comment out transaction stuff in saveUser or 
 * else rollback won't work & tests will fail
 */
public class ErsUserDaoTest {

	static ErsUser u1 = new ErsUser();
	static ErsUser u2 = new ErsUser();
	static ErsUser u3 = new ErsUser();
	static List<ErsUser> users = new ArrayList<>();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		u1.setId(12000);
		u1.setUsername("josephdg3");
		u1.setPassword("pass");
		u1.setFirstName("Joe");
		u1.setLastName("Gonz");
		u1.setEmail("josep@test.com");
		u1.setUserRole(new UserRole(100, "employee"));
		
		u2.setId(12001);
		u2.setUsername("john01");
		u2.setPassword("pass");
		u2.setFirstName("John");
		u2.setLastName("Gabe");
		u2.setEmail("john01@test.com");
		u2.setUserRole(new UserRole(101, "manager"));
		
		u3.setId(1000000);
		u3.setUsername("joeUser");
		u3.setPassword("pas");
		u3.setFirstName("Joseph");
		u3.setLastName("Gonzalez");
		u3.setEmail("joseph@email.com");
		u3.setUserRole(new UserRole(100, "employee"));
	}


	@After
	public void tearDown() throws Exception {
		users.clear();
	}

	@Test
	public void testGetAllUsers() {
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		
		ErsUserDao dao = HibernateUtil.getErsUserDao();
		try {
			dao.saveUser(u1);
			dao.saveUser(u2);
			users.add(u1);
			users.add(u2);
			users.add(u3);
			List<ErsUser> actual = dao.getAllUsers();
			boolean areSame = users.containsAll(actual);
			assertEquals(true, areSame);
		} finally {
			tx.rollback();
		}
		
	}

	@Test
	public void testGetErsUserByUsername() {
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		
		ErsUserDao dao = HibernateUtil.getErsUserDao();
		try {
			dao.saveUser(u1);
			dao.saveUser(u2);
			ErsUser actual = dao.getErsUserByUsername(u3.getUsername());
			boolean areSame = u3.equals(actual);
			assertEquals(true, areSame);
		} finally {
			tx.rollback();
		}
	}

	@Test
	public void testGetErsUsersByRole() {
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		
		ErsUserDao dao = HibernateUtil.getErsUserDao();
		try {
			dao.saveUser(u1);
			dao.saveUser(u2);
			users.add(u1);
			users.add(u3);
			List<ErsUser> actual = dao.getErsUsersByRole(u1.getUserRole().getRole());
			boolean areSame = users.containsAll(actual);
			assertEquals(true, areSame);
		} finally {
			tx.rollback();
		}
	}

}
