package com.github.swapnil.hims.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.swapnil.hims.dto.PatientDetail;
import com.github.swapnil.hims.service.PatientService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/patient")
@Api(value = "Patient REST API", description = "Operations pertaining to a patient in Hospital management system")
public class PatientController {
	@Autowired
	private PatientService patientSvc;
	
	@ApiOperation(value = "Get list of patients")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<PatientDetail> getPatients() {
		try {
			return patientSvc.getPatients();
		} catch (Exception e) {
			System.out.println("Something went wrong: ");
			e.printStackTrace();
		}
		return null;
	}

	@ApiOperation(value = "Get a patient by Id")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public PatientDetail getPatient(
		@ApiParam(value = "Patient Id from which patient object will be retrieved", required = true)
		@PathVariable(value="id") Long id) {
		try {
			return patientSvc.getById(id);
		} catch (Exception e) {
			System.out.println("Something went wrong: ");
			e.printStackTrace();
		}

		return null;
	}
	
	@ApiOperation(value = "Add a patient")
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public PatientDetail addPatient(
			@ApiParam(value = "Patient object to be stored in the database", required = true)
			@RequestBody PatientDetail patient) {
		try {
			patientSvc.save(patient);
			return patient;
		} catch (Exception e) {
			System.out.println("Something went wrong: ");
			e.printStackTrace();
		}
		
		return null;
	}

	@ApiOperation(value = "Update a patient by Id")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public PatientDetail updatePatient(
		@ApiParam(value = "Patient id ", required = true) @PathVariable(value="id") Long id,
		@ApiParam(value = "Patient object", required = true) @RequestBody PatientDetail patient) {
		try {
			patientSvc.update(id, patient);
			return patient;
		} catch (Exception e) {
			System.out.println("Something went wrong: ");
			e.printStackTrace();
		}

		return null;
	}
	
	@ApiOperation(value = "Delete a patient by Id")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deletePatient(
			@ApiParam(value = "Patient id ", required = true) 
			@PathVariable(value="id") Long id) {
		try {
			PatientDetail dbPatient = patientSvc.getById(id);
			if (dbPatient == null) {
				throw new IllegalArgumentException("Patient with id " + id + " does not exists.");
			}
			
			patientSvc.delete(id);
		} catch (Exception e) {
			System.out.println("Something went wrong: ");
			e.printStackTrace();
		}
	}
}
