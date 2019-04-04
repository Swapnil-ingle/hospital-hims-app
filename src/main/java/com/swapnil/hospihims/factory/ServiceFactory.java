package com.swapnil.hospihims.factory;

import com.swapnil.hospihims.service.PatientService;

public class ServiceFactory {
	public PatientService getPatientService() {
		return new PatientService();
	}
}
