package com.swapnil.hospihims.service;

import java.util.List;

import com.swapnil.hospihims.entity.Patient;

public interface PatientService {
	public Patient getPatientById(int id);
	
	public void savePatient(Patient patient);

	public List<Patient> getPatients();
}
