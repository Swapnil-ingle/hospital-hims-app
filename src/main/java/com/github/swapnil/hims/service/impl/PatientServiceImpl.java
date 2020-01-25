package com.github.swapnil.hims.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.swapnil.hims.dao.PatientDao;
import com.github.swapnil.hims.dto.PatientDetail;
import com.github.swapnil.hims.entities.Patient;
import com.github.swapnil.hims.exception.PatientException;
import com.github.swapnil.hims.service.PatientService;

@Service
public class PatientServiceImpl implements PatientService {
	private final static Log logger = LogFactory.getLog(PatientServiceImpl.class);
	
	@Autowired
	private PatientDao patientDao;

	@Override
	@Transactional
	public PatientDetail getById(Long id) {
		Patient patient = patientDao.findById(id);

		if (patient == null) {
			logger.error(String.format("Patient with Id %d not found", id));
			throw new PatientException(String.format("Patient with Id %d not found", id));
		}

		return PatientDetail.from(patient);
	}

	@Override
	@Transactional
	public void save(PatientDetail patientDetail) {
		if (StringUtils.isEmpty(patientDetail.getPatientId())) {
			throw new PatientException("Patient ID is required!");
		}

		if (StringUtils.isEmpty(patientDetail.getName())) {
			throw new PatientException("Patient name is required!");
		}

		if (StringUtils.isEmpty(patientDetail.getEmail())) {
			throw new PatientException("Patient e-mail is required!");
		}

		Patient dbPatient = patientDao.findByPatientId(patientDetail.getPatientId());
		if (dbPatient != null) {
			throw new PatientException(String.format("Patient with the same pid '%s' already exists!", dbPatient.getPatientId()));
		}

		patientDao.save(patientDetail.toPatient());
	}

	@Override
	@Transactional
	public List<PatientDetail> getPatients() {
		return patientDao.findAll().stream().map(patient -> PatientDetail.from(patient))
				.collect(Collectors.toList());
	}

	@Override
	@Transactional
	public void update(Long id, PatientDetail patientDetail) {
		Patient dbPatient = patientDao.findById(id);

		if (dbPatient == null) {
			throw new PatientException("Patient with id " + id + " does not exists.");
		}

		dbPatient.setName(StringUtils.isEmpty(patientDetail.getName()) ? dbPatient.getName() : patientDetail.getName());
		dbPatient.setCity(StringUtils.isEmpty(patientDetail.getCity()) ? dbPatient.getCity() : patientDetail.getCity());
		dbPatient.setAddress(StringUtils.isEmpty(patientDetail.getAddress()) ? dbPatient.getAddress() : patientDetail.getAddress());
		dbPatient.setEmail(StringUtils.isEmpty(patientDetail.getEmail()) ? dbPatient.getEmail() : patientDetail.getEmail());
		dbPatient.setContactNumber(patientDetail.getContactNumber() != null ? dbPatient.getContactNumber() : patientDetail.getContactNumber());
		dbPatient.setPatientId(StringUtils.isEmpty(patientDetail.getPatientId()) ? dbPatient.getPatientId() : patientDetail.getPatientId());

		patientDao.save(dbPatient);
	}

	@Override
	public void update(PatientDetail patientDetail) {
		update(patientDetail.getIdentifier(), patientDetail);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		patientDao.deleteById(id);
	}
}
