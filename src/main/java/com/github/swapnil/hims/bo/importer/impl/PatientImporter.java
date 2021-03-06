package com.github.swapnil.hims.bo.importer.impl;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.apache.commons.csv.CSVRecord;
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

	private Type importType = Type.CREATE;

	@Override
	public void setType(Type type) {
		this.importType = type;
	}

	@Override
	public void importFromFile(File input) throws IOException {
		startImport(input, this);
	}

	@SuppressWarnings("unchecked")
	@Override
	public PatientDetail rowToObject(CSVRecord record) {
		PatientDetail patientDetail = new PatientDetail();

		patientDetail.setIdentifier(toLongValue(getIfMapped(record, HEADERS.IDENTIFIER)));
		patientDetail.setPatientId(getIfMapped(record, HEADERS.PATIENT_PROTOCOL_ID));
		patientDetail.setName(getIfMapped(record, HEADERS.NAME));
		patientDetail.setAddress(getIfMapped(record, HEADERS.ADDRESS));
		patientDetail.setCity(getIfMapped(record, HEADERS.CITY));
		patientDetail.setContactNumber(toLongValue(getIfMapped(record, HEADERS.CONTACT_NUMBER)));
		patientDetail.setEmail(getIfMapped(record, HEADERS.EMAIL));

		return patientDetail;
	}

	@Override
	public String[] getHeaders() {
		return Arrays.stream(HEADERS.values()).map(Enum::name).toArray(String[]::new);
	}

	@Override
	public void saveToDb(Object patient) {
		PatientDetail patientDetail = (PatientDetail) patient;
		if (this.importType == Type.CREATE) {
			patientSvc.save(patientDetail);
		} else if (this.importType == Type.UPDATE) {
			patientSvc.update(patientDetail);
		}
	}
}
