package com.github.swapnil.hims.service.impl;

import java.util.List;
import java.util.Optional;
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
		Optional<Patient> patient = patientDao.findById(id);

		if (patient == null) {
			logger.error(String.format("Patient with Id %d not found", id));
			throw new PatientException(String.format("Patient with Id %d not found", id));
		}

		return PatientDetail.from(patient.get());
	}

	@Override
	@Transactional
	public void save(PatientDetail patientDetail) {
		patientDao.save(getPatient(patientDetail));
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
		patientDao.save(getPatient(id, patientDetail));
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

	private Patient getPatient(PatientDetail patientDetail) {
		if (StringUtils.isEmpty(patientDetail.getPatientId())) {
			throw new PatientException("Patient ID is required!");
		}

		if (StringUtils.isEmpty(patientDetail.getName())) {
			throw new PatientException("Patient name is required!");
		}

		if (StringUtils.isEmpty(patientDetail.getEmail())) {
			throw new PatientException("Patient e-mail is required!");
		}

		ensureUniquePatientId(patientDetail.getPatientId());
		return patientDetail.toPatient();
	}

	private void ensureUniquePatientId(String patientId) {
		Patient dbPatient = patientDao.findByPatientId(patientId);

		if (dbPatient != null) {
			throw new PatientException(String.format("Patient with the same pid '%s' already exists!", dbPatient.getPatientId()));
		}
	}

	private Patient getPatient(Long id, PatientDetail patientDetail) {
		Patient patient = patientDao.findById(id).get();

		if (patient == null) {
			throw new PatientException("Patient with id " + id + " does not exists.");
		}

		patient.setName(StringUtils.isEmpty(patientDetail.getName()) ? patient.getName() : patientDetail.getName());
		patient.setCity(StringUtils.isEmpty(patientDetail.getCity()) ? patient.getCity() : patientDetail.getCity());
		patient.setAddress(StringUtils.isEmpty(patientDetail.getAddress()) ? patient.getAddress() : patientDetail.getAddress());
		patient.setEmail(StringUtils.isEmpty(patientDetail.getEmail()) ? patient.getEmail() : patientDetail.getEmail());
		patient.setContactNumber(patientDetail.getContactNumber() != null ? patient.getContactNumber() : patientDetail.getContactNumber());
		patient.setPatientId(StringUtils.isEmpty(patientDetail.getPatientId()) ? patient.getPatientId() : patientDetail.getPatientId());

		return patient;
	}
}
