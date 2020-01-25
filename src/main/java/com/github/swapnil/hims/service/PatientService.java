package com.github.swapnil.hims.service;

import java.util.List;

import com.github.swapnil.hims.dto.PatientDetail;

public interface PatientService {
	public PatientDetail getById(Long id);
	
	public void save(PatientDetail patient);

	public List<PatientDetail> getPatients();

	public void update(Long id, PatientDetail patient);

	public void delete(Long id);

	public void update(PatientDetail patientDetail);
}
