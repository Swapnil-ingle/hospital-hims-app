package com.swapnil.hospihims.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.swapnil.hospihims.dao.PatientDao;
import com.swapnil.hospihims.entity.Patient;
import com.swapnil.hospihims.service.PatientService;

@RestController
@RequestMapping("/patient")
public class PatientController {
	@Autowired
	private PatientService patientSvc;
	
	@Autowired
	private PatientDao patientDao;
	
    @RequestMapping("/get")
    public Patient getPatient(@RequestParam(value="id") int id) {
    	
    	try {
    		return patientSvc.getPatientById(id);
    	} catch (Exception e) {
    		System.out.println("Something went wrong: ");
    		e.printStackTrace();
    	} 
    	
    	return null;
    }
	
	@RequestMapping("/add")
	@Transactional
	public Patient addPatient(@RequestParam(value="name") String name, 
			@RequestParam(value="address") String address, 
			@RequestParam(value="city") String city, 
			@RequestParam(value="contactNumber") int contact, 
			@RequestParam(value="email") String email) {
		Patient patient = new Patient(name, address, city, contact, email);
		
		try {
			patientDao.saveOrUpdate(patient);
			return patient;
		} catch (Exception e) {
			System.out.println("Something went wrong: ");
			e.printStackTrace();
		}
		
		return null;
	}
}
