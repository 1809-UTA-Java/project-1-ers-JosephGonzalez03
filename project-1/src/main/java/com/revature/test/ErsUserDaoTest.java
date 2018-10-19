package com.revature.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.revature.model.ErsUser;
import com.revature.model.UserRole;
import com.revature.repository.ErsUserDao;
import com.revature.util.HibernateUtil;

public class ErsUserDaoTest {

	ErsUser u1 = new ErsUser();
	ErsUser u2 = new ErsUser();
	List<ErsUser> users = new ArrayList<>();

	@Before
	public void setUp() throws Exception {
		ErsUserDao dao = HibernateUtil.getErsUserDao();

		u1.setId(12345);
		u1.setUsername("josephdg3");
		u1.setPassword("pass");
		u1.setFirstName("Joe");
		u1.setLastName("Gonz");
		u1.setEmail("josep@test.com");
		u1.setUserRole(new UserRole(100, "employee"));
		
		u2.setId(67891);
		u2.setUsername("john01");
		u2.setPassword("pass");
		u2.setFirstName("John");
		u2.setLastName("Gabe");
		u2.setEmail("john01@test.com");
		u2.setUserRole(new UserRole(101, "manager"));

		dao.saveUser(u1);
		dao.saveUser(u2);
	}


	@After
	public void tearDown() throws Exception {
		ErsUserDao dao = HibernateUtil.getErsUserDao();
		dao.deleteUser(u1);
		dao.deleteUser(u2);
		users.clear();
	}

//	@Test
//	public void testGetAllUsers() {
//		users.add(u1);
//		users.add(u2);
//		
//		ErsUserDao dao = HibernateUtil.getErsUserDao();
//		List<ErsUser> actual = dao.getAllUsers();
//		boolean areSame = users.containsAll(actual);
//		
//		assertEquals(true, areSame);
//	}

	@Test
	public void testGetErsUserByUsername() {
		ErsUserDao dao = HibernateUtil.getErsUserDao();
		ErsUser actual = dao.getErsUserByUsername(u1.getUsername());

		assertEquals(u1, actual);
	}

	@Test
	public void testGetErsUsersByRole() {
		users.add(u1);
		
		ErsUserDao dao = HibernateUtil.getErsUserDao();
		List<ErsUser> actual = dao.getErsUsersByRole(u1.getUserRole().getRole());
		boolean areSame = users.containsAll(actual);

		assertEquals(true, areSame);
	}

//	@Test
//	public void testUpdateErsUser() {
//		fail("Not yet implemented");
//	}

}
