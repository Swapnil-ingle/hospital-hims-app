package com.swapnil.hospihims.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swapnil.hospihims.dao.PatientDao;
import com.swapnil.hospihims.entity.Patient;
import com.swapnil.hospihims.service.PatientService;

@Service
public class PatientServiceImpl implements PatientService {
	@Autowired
	private PatientDao patientDao;
	
	@Override
	@Transactional
	public Patient getPatientById(int id) {
		return patientDao.getPatientById(id);
	}

	@Override
	@Transactional
	public void savePatient(Patient patient) {
		if (StringUtils.isEmpty(patient.getPid())) {
			throw new IllegalArgumentException("Patient ID is required!");
		}

		if (StringUtils.isEmpty(patient.getName())) {
			throw new IllegalArgumentException("Patient name is required!");
		}

		if (StringUtils.isEmpty(patient.getEmail())) {
			throw new IllegalArgumentException("Patient e-mail is required!");
		}

		Patient dbPatient = patientDao.getPatientByPid(patient.getPid());
		if (dbPatient != null) {
			throw new IllegalArgumentException("Patient with the same pid already exists!");
		}

		patientDao.saveOrUpdate(patient);
	}

	@Override
	@Transactional
	public List<Patient> getPatients() {
		return patientDao.getPatients();
	}

	@Override
	@Transactional
	public void updatePatient(int id, Patient patient) {
		Patient dbPatient = patientDao.getPatientById(id);

		if (dbPatient == null) {
			throw new IllegalArgumentException("Patient with id " + id + " does not exists.");
		}

		patient.setIdentifier(id);
		patientDao.saveOrUpdate(patient);
	}

	@Override
	@Transactional
	public void deletePatient(int id) {
		patientDao.deletePatient(id);
	}
}
