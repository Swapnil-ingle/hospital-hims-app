package com.github.swapnil.hims.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.swapnil.hims.entities.Patient;

public interface PatientDao extends JpaRepository<Patient, Long> {
	public Optional<Patient> findById(Long id);

	public void deleteById(Long id);

	public Patient findByPatientId(String patientId);
}
