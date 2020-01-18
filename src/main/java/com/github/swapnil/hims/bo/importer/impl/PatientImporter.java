package com.github.swapnil.hims.bo.importer.impl;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.swapnil.hims.bo.importer.BulkImporter;
import com.github.swapnil.hims.bo.importer.Importer;
import com.github.swapnil.hims.dto.PatientDetail;
import com.github.swapnil.hims.service.PatientService;

@Component
public class PatientImporter extends Importer implements BulkImporter {
	@Autowired
	private PatientService patientSvc;

	private static enum HEADERS {
		IDENTIFIER, PATIENT_PROTOCOL_ID, NAME, ADDRESS, CITY, CONTACT_NUMBER, EMAIL
	}

	@Override
	public void importFromFile(File input) throws IOException {
		startImport(input, this);
	}

	@SuppressWarnings("unchecked")
	@Override
	public PatientDetail rowToObject(CSVRecord record) {
		PatientDetail patientDetail = new PatientDetail();

		patientDetail.setIdentifier(toLongValue(record.get(HEADERS.IDENTIFIER)));
		patientDetail.setPatientId(record.get(HEADERS.PATIENT_PROTOCOL_ID));
		patientDetail.setName(record.get(HEADERS.NAME));
		patientDetail.setAddress(record.get(HEADERS.ADDRESS));
		patientDetail.setCity(record.get(HEADERS.CITY));
		patientDetail.setContactNumber(toLongValue(record.get(HEADERS.CONTACT_NUMBER)));
		patientDetail.setEmail(record.get(HEADERS.EMAIL));

		return patientDetail;
	}

	private Long toLongValue(String input) {
		if (input == null || StringUtils.isBlank(input)) {
			return null;
		}

		return Long.valueOf(input);
	}

	@Override
	public String[] getHeaders() {
		return Arrays.stream(HEADERS.values()).map(Enum::name).toArray(String[]::new);
	}

	@Override
	public void saveToDb(Object patient) {
		PatientDetail patientDetail = (PatientDetail) patient;
		patientSvc.savePatient(patientDetail);
	}
}
