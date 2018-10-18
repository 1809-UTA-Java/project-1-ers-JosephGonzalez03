package com.revature.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.revature.repository.*;

public class HibernateUtil {
	private static SessionFactory sessionFactory(String filename) {
		Configuration config = new Configuration().configure(filename);

		ServiceRegistry serviceR = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();

		return config.buildSessionFactory(serviceR);
	}

	private static SessionFactory sf = sessionFactory("hibernate.cfg.xml");

	public static Session getSession() {
		return sf.openSession();
	}

	public static void shutdown() {
		sf.close();
	}

	public static ErsUserDao getErsUserDao() {
		return new ErsUserDao();
	}

	public static ReimbursementDao getReimbursementDao() {
		return new ReimbursementDao();
	}
	
	public static ReimbursementStatusDao getRimbursementStatusDao() {
		return new ReimbursementStatusDao();
	}

	public static ReimbursementTypeDao getRimbursementTypeDao() {
		return new ReimbursementTypeDao();
	}
}
