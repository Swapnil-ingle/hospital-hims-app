package com.swapnil.hospihims.service;

import com.swapnil.hospihims.entity.Patient;

public interface PatientService {
	public Patient getPatientById(int id);
	
	public void savePatient(Patient patient);
}
