package com.github.swapnil.hims.bo.importer.impl;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.github.swapnil.hims.bo.importer.BulkImporter;
import com.github.swapnil.hims.bo.importer.Importer;
import com.github.swapnil.hims.dto.PatientDetail;
import com.github.swapnil.hims.service.PatientService;

@Service
public class PatientImporter extends Importer implements BulkImporter {
	@Autowired
	private PatientService patientSvc;

    private final TransactionTemplate txTmpl;

    public PatientImporter(PlatformTransactionManager transactionManager) {
        this.txTmpl = new TransactionTemplate(transactionManager);
    }

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
	public void saveToDb(List<Object> patients) {
		List<PatientDetail> patientList = patients.stream().map(patient -> (PatientDetail) patient)
				.collect(Collectors.toList());
		saveAll(patientList, this.importType, BATCH_SIZE);
	}

	private void saveAll(List<PatientDetail> patients, Importer.Type action, Integer batchSize) {
		txTmpl.execute(new TransactionCallbackWithoutResult() {
		    protected void doInTransactionWithoutResult(TransactionStatus status) {
	    		createPatients(patients, action, batchSize);
		    }
		});
	}

	private void createPatients(List<PatientDetail> patients, Importer.Type action, Integer batchSize) {
		int startAt = 0;
		while (true) {
			List<PatientDetail> patientBatch = patients.stream().skip(startAt).limit(batchSize)
					.collect(Collectors.toList());

			for (PatientDetail patient : patientBatch) {
				if (action == Importer.Type.CREATE) {
					patientSvc.save(patient);
				} else {
					patientSvc.update(patient);
				}
			}

			if (patientBatch.size() != batchSize) {
				break;
			}

			startAt += batchSize;
		}
	}
}
