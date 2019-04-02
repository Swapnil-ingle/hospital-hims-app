package com.swapnil.hospihims.controller;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.swapnil.hospihims.entity.Patient;

@RestController
@RequestMapping("/patient")
public class PatientController {
	
    @RequestMapping("/get")
    public Patient getPatient(@RequestParam(value="id") int id) {
    	Session session = null;
    	Patient patient = null;
    	try {
    		session = getSession();
    		session.beginTransaction();
    		patient = session.get(Patient.class, id);
    		session.getTransaction().commit();
    	} catch (Exception e) {
    		System.out.println("Something went wrong: ");
    		e.printStackTrace();
    	} 
    	
    	return patient;
    }
	
	@RequestMapping("/add")
	public void addPatient(@RequestParam(value="name") String name, 
			@RequestParam(value="address") String address, 
			@RequestParam(value="city") String city, 
			@RequestParam(value="contactNumber") int contact, 
			@RequestParam(value="email") String email) {
		Patient patient = new Patient(name, address, city, contact, email);
		Session session = null;
		
		try {
			session = getSession();
			savePatient(patient, session);
		} catch (Exception e) {
			System.out.println("Something went wrong: ");
			e.printStackTrace();
		} finally {
			if (session != null) { session.close(); }
		}
	}

	private Session getSession() {
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Patient.class)
									.buildSessionFactory();
		
		return factory.getCurrentSession();
	}
	
	private void savePatient(Patient patient, Session session) throws Exception {
		session.beginTransaction();
		
		session.save(patient);
		
		session.getTransaction().commit();
	}
}
