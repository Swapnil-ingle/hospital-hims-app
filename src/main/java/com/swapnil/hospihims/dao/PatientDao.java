package com.swapnil.hospihims.dao;

import java.util.List;

import com.swapnil.hospihims.entity.Patient;

public interface PatientDao {
	public Patient getPatientById(int id);
	
	public void saveOrUpdate(Patient patient);

	public List<Patient> getPatients();

	public void update(Patient patient);

	public void deletePatient(int id);
}
