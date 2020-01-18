package com.github.swapnil.hims.service;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

public interface BulkImportService {
	public File makeInputFile(MultipartFile multipartFile);
}
