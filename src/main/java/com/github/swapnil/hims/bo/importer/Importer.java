package com.github.swapnil.hims.bo.importer;

import java.io.File;
import java.io.IOException;

import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;

import com.github.swapnil.hims.utils.CSVReader;

public abstract class Importer {
	public static enum Type {
		CREATE, UPDATE
	};

	protected void startImport(File input, BulkImporter importer) throws IOException {
		CSVReader csvReader = new CSVReader(input, importer);

		for (CSVRecord record : csvReader.getCsvReader()) {
			importer.saveToDb(importer.rowToObject(record));
		}

		csvReader.closeCsvReader();
	}

	protected String getIfMapped(CSVRecord record, Enum<?> header) {
		if (record.isMapped(header.name())) {
			return record.get(header);
		}

		return null;
	}

	protected Long toLongValue(String input) {
		if (input == null || StringUtils.isBlank(input)) {
			return null;
		}

		return Long.valueOf(input);
	}
}
