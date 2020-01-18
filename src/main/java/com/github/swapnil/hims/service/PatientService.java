package com.github.swapnil.hims.service;

import java.util.List;

import com.github.swapnil.hims.dto.PatientDetail;

public interface PatientService {
	public PatientDetail getPatientById(Long id);
	
	public void savePatient(PatientDetail patient);

	public List<PatientDetail> getPatients();

	public void updatePatient(Long id, PatientDetail patient);

	public void deletePatient(Long id);
}
