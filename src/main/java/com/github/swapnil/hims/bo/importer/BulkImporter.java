package com.github.swapnil.hims.bo.importer;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.csv.CSVRecord;

public interface BulkImporter {
	public void importFromFile(File input) throws IOException;

	public <T> T rowToObject(CSVRecord record);

	public String[] getHeaders();

	public void saveToDb(List<Object> patients);

	public void setType(Importer.Type type);
}
