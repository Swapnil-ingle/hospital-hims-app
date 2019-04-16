package com.swapnil.hospihims.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.swapnil.hospihims.entity.Patient;
import com.swapnil.hospihims.service.PatientService;

@RestController
@RequestMapping("/patient")
public class PatientController {
	@Autowired
	private PatientService patientSvc;
	
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
	
	@RequestMapping(method= RequestMethod.POST, value = "/add")
	public Patient addPatient(@RequestBody Patient patient) {
		try {
			patientSvc.savePatient(patient);
			return patient;
		} catch (Exception e) {
			System.out.println("Something went wrong: ");
			e.printStackTrace();
		}
		
		return null;
	}
}
