package com.swapnil.hospihims.dao;

import com.swapnil.hospihims.entity.Patient;

public interface PatientDao {
	public Patient getPatientById(int id);
	
	public void saveOrUpdate(Patient patient);
}
