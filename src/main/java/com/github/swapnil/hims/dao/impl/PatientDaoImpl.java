//package com.github.swapnil.hims.dao.impl;
//
//import java.util.List;
//
//import org.hibernate.SessionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//import com.github.swapnil.hims.dao.PatientDao;
//import com.github.swapnil.hims.entities.Patient;
//
//@Repository
//public class PatientDaoImpl implements PatientDao {
//	@Autowired
//	private SessionFactory sessionFactory;
//	
//	@Override
//	public Patient getPatientById(Long id) {
//		return sessionFactory.getCurrentSession().get(Patient.class, id);
//	}
//
//	@Override
//	public void saveOrUpdate(Patient patient) {
//		sessionFactory.getCurrentSession().saveOrUpdate(patient);
//	}
//
//	@Override
//	public void update(Patient patient) {
//		sessionFactory.getCurrentSession().update(patient);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<Patient> getPatients() {
//		return sessionFactory.getCurrentSession()
//				.createQuery("from Patient")
//				.list();
//	}
//
//	@Override
//	public void deletePatient(Long id) {
//		Patient patient = sessionFactory.getCurrentSession().load(Patient.class, id);
//		sessionFactory.getCurrentSession().delete(patient);
//	}
//
//	@Override
//	public Patient getPatientByPid(String pid) {
//		return (Patient) sessionFactory.getCurrentSession()
//				.createQuery("from Patient where patient_id = :patient_id")
//				.setParameter("patient_id", pid)
//				.uniqueResult();
//	}
//}
