package com.github.swapnil.hims.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.github.swapnil.hims.service.BulkImportService;
import com.github.swapnil.hims.utils.DateUtils;

@Service
public class BulkImportServiceImpl implements BulkImportService {

	private final static String DATA_DIR = "/Users/swapnil/links/hospital-hims-app/data/";

	@Override
	public File makeInputFile(MultipartFile multipartFile) {
		String[] filenameParts = multipartFile.getOriginalFilename().split("\\.");
		File inputFile = null;
		InputStream is = null;

		try {
			// TODO: Add a check if the extension of the file is not CSV then it should be
			// rejected.
			is = multipartFile.getInputStream();
			inputFile = new File(DATA_DIR + "input_" + DateUtils.getCurrentTimeStamp() + "." + filenameParts[1]);
			Files.copy(is, Paths.get(inputFile.getPath()));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(is);
		}

		return inputFile;
	}
}
