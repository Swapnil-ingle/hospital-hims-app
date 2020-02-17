package com.github.swapnil.hims.bo.importer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;

import com.github.swapnil.hims.utils.CSVReader;

public abstract class Importer {
	protected final static Integer BATCH_SIZE = 25;

	public static enum Type {
		CREATE, UPDATE
	};

	protected void startImport(File input, BulkImporter importer) throws IOException {
		CSVReader csvReader = new CSVReader(input, importer);
		List<Object> records = new ArrayList<>();

		for (CSVRecord record : csvReader.getCsvReader()) {
			records.add(importer.rowToObject(record));

			if (records.size() % BATCH_SIZE == 0) {
				importer.saveToDb(records);
				records.clear();
			}
		}

		importer.saveToDb(records); // TODO: If the records.size is 0. This should not be called.
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
