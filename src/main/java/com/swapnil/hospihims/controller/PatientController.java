package com.swapnil.hospihims.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.swapnil.hospihims.entity.Patient;
import com.swapnil.hospihims.service.PatientService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/patient")
@Api(value = "Hospital management system", description = "Operations pertaining to a patient in Hospital management system")
public class PatientController {
	@Autowired
	private PatientService patientSvc;
	
	@ApiOperation(value = "Get a patient by Id")
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public Patient getPatient(
    		@ApiParam(value = "Patient Id from which patient object will be retrieved", required = true)
    		@PathVariable(value="id") int id) {
    	try {
    		return patientSvc.getPatientById(id);
    	} catch (Exception e) {
    		System.out.println("Something went wrong: ");
    		e.printStackTrace();
    	} 
    	
    	return null;
    }
	
	@ApiOperation(value = "Add a patient")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public Patient addPatient(
			@ApiParam(value = "Patient object to be stored in the database", required = true)
			@RequestBody Patient patient) {
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
