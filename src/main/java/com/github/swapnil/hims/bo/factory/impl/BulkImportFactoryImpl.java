package com.github.swapnil.hims.bo.factory.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.swapnil.hims.bo.factory.BulkImportFactory;
import com.github.swapnil.hims.bo.importer.BulkImporter;
import com.github.swapnil.hims.bo.importer.impl.PatientImporter;

@Component
public class BulkImportFactoryImpl implements BulkImportFactory {
	@Autowired
	private PatientImporter patientImporter;

	@Override
	public BulkImporter getPatientImporter() {
		return patientImporter;
	}
}
