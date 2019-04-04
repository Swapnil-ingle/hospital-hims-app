package com.swapnil.hospihims.factory;

import com.swapnil.hospihims.dao.PatientDao;

public class DaoFactory {
	public PatientDao getPatientDao() {
		return new PatientDao();
	}
}
