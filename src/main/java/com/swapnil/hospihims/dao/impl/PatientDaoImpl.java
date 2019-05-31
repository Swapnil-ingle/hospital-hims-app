package com.swapnil.hospihims.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.swapnil.hospihims.dao.PatientDao;
import com.swapnil.hospihims.entity.Patient;

@Repository
public class PatientDaoImpl implements PatientDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Patient getPatientById(int id) {
		return sessionFactory.getCurrentSession().get(Patient.class, id);
	}

	@Override
	public void saveOrUpdate(Patient patient) {
		sessionFactory.getCurrentSession().saveOrUpdate(patient);
	}

	@Override
	public void update(Patient patient) {
		sessionFactory.getCurrentSession().update(patient);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Patient> getPatients() {
		return sessionFactory.getCurrentSession()
				.createQuery("from Patient")
				.list();
	}
}
