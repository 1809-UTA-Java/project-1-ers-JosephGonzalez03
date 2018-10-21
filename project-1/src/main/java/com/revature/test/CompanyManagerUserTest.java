package com.revature.test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.revature.service.CompanyManagerUser;
import com.revature.util.HibernateUtil;

public class CompanyManagerUserTest {

	static Reimbursement r1 = new Reimbursement();
	static Reimbursement r2 = new Reimbursement();
	static List<Reimbursement> rList = new ArrayList<>();
	static ErsUser u1 = new ErsUser();
	static ErsUser u2 = new ErsUser();
	static List<ErsUser> uList = new ArrayList<>();
	static CompanyEmployeeUser e1 = null;
	static CompanyManagerUser e2 = null;
	static HttpServletRequest req;
	static HttpServletResponse resp;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
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
		
		r1.setId(10000);
		r1.setAmount(100.23);
		r1.setDescription("I traveled");
		r1.setAuthor(u1);
		r1.setReimbursementType(new ReimbursementType(100, "travel"));
		r1.setReimbursementStatus(new ReimbursementStatus(100, "pending"));

		r2.setId(10001);
		r2.setAmount(100.23);
		r2.setDescription("I traveled too");
		r2.setAuthor(u1);
		r2.setReimbursementType(new ReimbursementType(100, "travel"));
		r2.setReimbursementStatus(new ReimbursementStatus(100, "pending"));

		
		e1 = new CompanyEmployeeUser(u1);
		e2 = new CompanyManagerUser(u2);
		
	}

	@After
	public void tearDown() throws Exception {
		rList.clear();
		uList.clear();
	}
	
	@Test
	public void testReviewPendingReimbursementReq() throws IOException {
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		
		ErsUserDao dao = HibernateUtil.getErsUserDao();
		ReimbursementDao rdao = HibernateUtil.getReimbursementDao();
		try {
			dao.saveUserTest(u1);
			dao.saveUserTest(u2);
			rdao.saveReimbursementTest(r1);
			boolean successful = e2.reviewPendingReimbursementReq(u2, 2000, "approved");
			assertEquals(true, successful);
		} finally {
			tx.rollback();
		}
	}

//	@Test
//	public void testViewImage() {
//		fail("Not yet implemented");
//	}

	@Test
	public void testViewAllPendingReimbursementReqs() {
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		
		ErsUserDao dao = HibernateUtil.getErsUserDao();
		ReimbursementDao rdao = HibernateUtil.getReimbursementDao();
		try {
			dao.saveUserTest(u1);
			r1.setReimbursementStatus(new ReimbursementStatus(100, "pending"));
			r2.setReimbursementStatus(new ReimbursementStatus(100, "pending"));
			rList.add(r1);
			rList.add(r2);
			rdao.saveReimbursementTest(r1);
			rdao.saveReimbursementTest(r2);
			List<Reimbursement> actual = e2.viewAllPendingReimbursementReqs();
			boolean areSame = rList.containsAll(actual);
			assertEquals(true, areSame);
		} finally {
			tx.rollback();
		}
	}

	@Test
	public void testViewAllResolvedReimbursementReqs() {
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		
		ErsUserDao dao = HibernateUtil.getErsUserDao();
		ReimbursementDao rdao = HibernateUtil.getReimbursementDao();
		try {
			dao.saveUserTest(u1);
			r1.setReimbursementStatus(new ReimbursementStatus(101, "approved"));
			r2.setReimbursementStatus(new ReimbursementStatus(102, "denied"));
			rList.add(r1);
			rList.add(r2);
			rdao.saveReimbursementTest(r1);
			rdao.saveReimbursementTest(r2);
			List<Reimbursement> actual = e2.viewAllResolvedReimbursementReqs();
			boolean areSame = rList.containsAll(actual);
			assertEquals(true, areSame);
		} finally {
			tx.rollback();
		}
	}

	@Test
	public void testViewAllEmployees() {
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		
		ErsUserDao dao = HibernateUtil.getErsUserDao();
		try {
			dao.saveUserTest(u1);
			dao.saveUserTest(u2);
			uList.add(u1);
			List<ErsUser> actual = e2.viewAllEmployees();
			boolean areSame = uList.containsAll(actual);
			assertEquals(true, areSame);
		} finally {
			tx.rollback();
		}
	}

	@Test
	public void testViewReimbursementReqs() {
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		
		ErsUserDao dao = HibernateUtil.getErsUserDao();
		ReimbursementDao rdao = HibernateUtil.getReimbursementDao();
		try {
			dao.saveUserTest(u1);
			r1.setReimbursementStatus(new ReimbursementStatus(100, "pending"));
			r2.setReimbursementStatus(new ReimbursementStatus(102, "denied"));
			rList.add(r1);
			rList.add(r2);
			rdao.saveReimbursementTest(r1);
			rdao.saveReimbursementTest(r2);
			List<Reimbursement> actual = e2.viewReimbursementReqs(u1);
			boolean areSame = rList.containsAll(actual);
			assertEquals(true, areSame);
		} finally {
			tx.rollback();
		}
	}

}
