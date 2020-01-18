package com.github.swapnil.hims.bo.factory;

import com.github.swapnil.hims.bo.importer.BulkImporter;

public interface BulkImportFactory {
	public BulkImporter getPatientImporter();
}
