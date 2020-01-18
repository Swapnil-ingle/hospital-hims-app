package com.github.swapnil.hims.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.swapnil.hims.entities.Patient;

public interface PatientDao extends JpaRepository<Patient, String> {
	public Patient findById(Long id);

	public void deleteById(Long id);

	public Patient findByPatientId(String patientId);
}
