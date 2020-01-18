package com.github.swapnil.hims.utils;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

import com.github.swapnil.hims.bo.importer.BulkImporter;

public class CSVReader {
	private Reader reader;
	
	private CSVParser csvReader;
	
	public Reader getReader() {
		return reader;
	}

	public void setReader(Reader reader) {
		this.reader = reader;
	}

	public CSVParser getCsvReader() {
		return csvReader;
	}

	public void setCsvReader(CSVParser csvReader) {
		this.csvReader = csvReader;
	}
	
	public CSVReader(File file, BulkImporter importer) throws IOException {
		this.reader = Files.newBufferedReader(Paths.get(file.getAbsolutePath()));
		this.csvReader = new CSVParser(reader, CSVFormat.DEFAULT
				.withHeader(importer.getHeaders())
				.withFirstRecordAsHeader()
				.withIgnoreHeaderCase()
				.withTrim());
	}
	
	public CSVReader(File file) throws IOException {
		this.reader = Files.newBufferedReader(Paths.get(file.getAbsolutePath()));
		this.csvReader = new CSVParser(reader, CSVFormat.DEFAULT);
	}
	
	public List<String> readHeaders() throws IOException {
		return csvReader.getHeaderNames();
	}
	
	public void closeCsvReader() throws IOException {
		reader.close();
		csvReader.close();
	}
}
