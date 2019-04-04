package com.swapnil.hospihims.dao;

import com.swapnil.hospihims.entity.Patient;
import com.swapnil.hospihims.factory.HimsSessionFactory;

public class PatientDao {
	
	private final HimsSessionFactory sessionFactory = new HimsSessionFactory();
	
	public Patient getPatientById(int id) {
		sessionFactory.getSession().beginTransaction();
		Patient patient = sessionFactory.getSession().get(Patient.class, id);
		sessionFactory.getSession().getTransaction().commit();
		
		return patient;
	}
}
