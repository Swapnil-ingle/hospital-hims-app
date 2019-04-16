package com.swapnil.hospihims.service.impl;

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
		if (StringUtils.isEmpty(patient.getName())) {
			throw new IllegalArgumentException("Patient name is required!");
		}

		if (StringUtils.isEmpty(patient.getEmail())) {
			throw new IllegalArgumentException("Patient e-mail is required!");
		}

		patientDao.saveOrUpdate(patient);
	}
}
