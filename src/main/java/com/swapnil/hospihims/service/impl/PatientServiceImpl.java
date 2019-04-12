package com.swapnil.hospihims.service.impl;

import javax.transaction.Transactional;

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
}
