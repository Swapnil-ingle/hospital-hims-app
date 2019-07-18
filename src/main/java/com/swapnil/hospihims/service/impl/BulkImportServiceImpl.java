package com.swapnil.hospihims.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.multipart.MultipartFile;

import com.swapnil.hospihims.service.BulkImportService;
import com.swapnil.hospihims.utils.DateUtils;

public class BulkImportServiceImpl implements BulkImportService {
	
	private final static String DATA_DIR = "/Users/swapnil/links/hospital-hims-app/data/"; 

	@Override
	public File makeInputFile(MultipartFile multipartFile) {
		File inputFile = null;
		InputStream is = null;
		
		try {
			is = multipartFile.getInputStream();
			inputFile = new File(DATA_DIR + "input_" + DateUtils.getCurrentTimeStamp() + ".csv");
			Files.copy(is, Paths.get(inputFile.getPath()));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(is);
		}
		
		return inputFile;
	}

}
