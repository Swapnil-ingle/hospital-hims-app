package com.github.swapnil.hims.bo.importer;

import java.io.File;
import java.io.IOException;

import org.apache.commons.csv.CSVRecord;

public interface BulkImporter {
	public void importFromFile(File input) throws IOException;

	public <T> T rowToObject(CSVRecord record);

	public String[] getHeaders();

	public void saveToDb(Object patient);

	public void setType(Importer.Type type);
}
