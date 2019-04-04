package com.swapnil.hospihims.service;

import com.swapnil.hospihims.entity.Patient;
import com.swapnil.hospihims.factory.HimsSessionFactory;

public class PatientService {
	
	private final HimsSessionFactory sessionFactory = new HimsSessionFactory();
	
	public void addPatient(Patient patient) {
		sessionFactory.getSession().beginTransaction();
		sessionFactory.getSession().save(patient);
		sessionFactory.getSession().getTransaction().commit();
	}
	
}
