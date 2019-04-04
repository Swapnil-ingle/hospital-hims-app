package com.swapnil.hospihims.factory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.swapnil.hospihims.entity.Patient;

public class HimsSessionFactory {
	private Session session;
	
	public Session getSession() {
		if (session != null) {
			return session;
		}
		
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Patient.class)
				.buildSessionFactory();
		
		this.session = factory.getCurrentSession();
		return this.session;
	}
}
