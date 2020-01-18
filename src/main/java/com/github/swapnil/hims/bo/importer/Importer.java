package com.github.swapnil.hims.bo.importer;

import java.io.File;
import java.io.IOException;

import org.apache.commons.csv.CSVRecord;

import com.github.swapnil.hims.utils.CSVReader;

public abstract class Importer {
	public void startImport(File input, BulkImporter importer) throws IOException {
		CSVReader csvReader = new CSVReader(input, importer);

		for (CSVRecord record : csvReader.getCsvReader()) {
			importer.saveToDb(importer.rowToObject(record));
		}

		csvReader.closeCsvReader();
	}
}
