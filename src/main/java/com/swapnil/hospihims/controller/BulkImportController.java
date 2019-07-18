package com.swapnil.hospihims.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.swapnil.hospihims.service.BulkImportService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/bo-import")
@Api(value="Bulk import REST API", description = "Operations pertaining to bulk importing")
public class BulkImportController {
	@Autowired
	private BulkImportService bulkImportSvc;
	
	@ApiOperation(value = "BulkImport multiple patient objects")
	@RequestMapping(value="/patients", method = RequestMethod.POST)
	public void importPatient(
			@ApiParam(value = "input file", required = true)
			@RequestParam("file") MultipartFile file) {
		try {
			File inputFile = bulkImportSvc.makeInputFile(file);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
