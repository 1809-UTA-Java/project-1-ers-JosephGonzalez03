package com.revature.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.model.ErsUser;
import com.revature.model.Reimbursement;
import com.revature.model.ReimbursementStatus;
import com.revature.model.ReimbursementType;
import com.revature.model.UserRole;
import com.revature.repository.ErsUserDao;
import com.revature.repository.ReimbursementDao;
import com.revature.service.CompanyEmployeeUser;
import com.revature.util.HibernateUtil;

public class CompanyEmployeeUserTest {

	static Reimbursement r1 = new Reimbursement();
	static List<Reimbursement> rList = new ArrayList<>();
	static ErsUser u1 = new ErsUser();
	static CompanyEmployeeUser e1 = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		u1.setId(12345);
		u1.setUsername("josephdg3");
		u1.setPassword("pass");
		u1.setFirstName("Joe");
		u1.setLastName("Gonz");
		u1.setEmail("josep@test.com");
		u1.setUserRole(new UserRole(100, "employee"));

		r1.setId(10000);
		r1.setAmount(100.23);
		r1.setDescription("I traveled");
		r1.setReimbursementType(new ReimbursementType(100, "travel"));
		r1.setReimbursementStatus(new ReimbursementStatus(100, "pending"));

		e1 = new CompanyEmployeeUser(u1);
	}

	@After
	public void tearDown() throws Exception {
		rList.clear();
	}

	@Test
	public void testSubmitReimbursementReq() {
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();

		boolean passed = false;
		ErsUserDao dao = HibernateUtil.getErsUserDao();
		try {
			dao.saveUserTest(u1);
			e1.submitReimbursementReq(100.23, "I travaled too", null, "travel");
			passed = true;
			assertEquals(true, passed);
		} catch (IOException e) {
			fail("Caught IOException");
		} finally {
			tx.rollback();
		}
	}

//	@Test
//	public void testUploadImage() {
//		fail("Not yet implemented");
//	}

	@Test
	public void testViewPendingReimbursementReqs() {
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();

		ErsUserDao dao = HibernateUtil.getErsUserDao();
		ReimbursementDao rdao = HibernateUtil.getReimbursementDao();
		try {
			dao.saveUserTest(u1);
			r1.setReimbursementStatus(new ReimbursementStatus(100, "pending"));
			rList.add(r1);
			rdao.saveReimbursementTest(r1);

			List<Reimbursement> actual = e1.viewPendingReimbursementReqs();
			boolean successful = rList.containsAll(actual);
			assertEquals(true, successful);
		} finally {
			tx.rollback();
		}
	}

	@Test
	public void testViewResolvedReimbursementReqs() {
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();

		ErsUserDao dao = HibernateUtil.getErsUserDao();
		ReimbursementDao rdao = HibernateUtil.getReimbursementDao();
		try {
			dao.saveUserTest(u1);
			r1.setReimbursementStatus(new ReimbursementStatus(100, "approved"));
			rList.add(r1);
			rdao.saveReimbursementTest(r1);

			List<Reimbursement> actual = e1.viewResolvedReimbursementReqs();
			boolean successful = rList.containsAll(actual);
			assertEquals(true, successful);
		} finally {
			tx.rollback();
		}
	}

	@Test
	public void testUpdateInfo() {
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();

		ErsUserDao dao = HibernateUtil.getErsUserDao();
		try {
			dao.saveUserTest(u1);
			
			u1.setPassword("newPass");
			u1.setFirstName("Joseph");
			boolean successful = e1.updateInfo(u1);
			assertEquals(true, successful);
		} finally {
			tx.rollback();
		}
	}

}
