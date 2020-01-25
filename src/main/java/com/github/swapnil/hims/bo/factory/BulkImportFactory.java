package com.github.swapnil.hims.bo.factory;

import com.github.swapnil.hims.bo.importer.BulkImporter;
import com.github.swapnil.hims.bo.importer.Importer;

public interface BulkImportFactory {
	public BulkImporter getPatientImporter();

	public BulkImporter getPatientImporter(Importer.Type type);
}
