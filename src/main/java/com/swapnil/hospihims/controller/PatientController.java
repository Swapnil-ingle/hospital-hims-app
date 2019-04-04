package com.swapnil.hospihims.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.swapnil.hospihims.dao.PatientDao;
import com.swapnil.hospihims.entity.Patient;
import com.swapnil.hospihims.factory.DaoFactory;
import com.swapnil.hospihims.factory.ServiceFactory;

@RestController
@RequestMapping("/patient")
public class PatientController {
	
    @RequestMapping("/get")
    public Patient getPatient(@RequestParam(value="id") int id) {
    	Patient patient = null;
    	try {
    		PatientDao patientDao = new DaoFactory().getPatientDao();
    		return patientDao.getPatientById(id);
    	} catch (Exception e) {
    		System.out.println("Something went wrong: ");
    		e.printStackTrace();
    	} 
    	
    	return patient;
    }
	
	@RequestMapping("/add")
	public Patient addPatient(@RequestParam(value="name") String name, 
			@RequestParam(value="address") String address, 
			@RequestParam(value="city") String city, 
			@RequestParam(value="contactNumber") int contact, 
			@RequestParam(value="email") String email) {
		Patient patient = new Patient(name, address, city, contact, email);
		
		try {
			new ServiceFactory().getPatientService().addPatient(patient);
			return patient;
		} catch (Exception e) {
			System.out.println("Something went wrong: ");
			e.printStackTrace();
		}
		
		return null;
	}
}
