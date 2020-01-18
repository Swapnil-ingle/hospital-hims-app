package com.github.swapnil.hims.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUtils {
	private DateUtils() {
		// Private constructor to block object initialization 
	}
	
	private final static String DEF_TIMESTAMP_FORMAT = "HHmmss_ddMMyyyy";
	
	public static String getCurrentTimeStamp() {
		return getCurrentTimeStamp(DEF_TIMESTAMP_FORMAT);
	}
	
	public static String getCurrentTimeStamp(String timestampFormat) {
		SimpleDateFormat sdf = new SimpleDateFormat(timestampFormat);
		return sdf.format(Calendar.getInstance().getTime());
	}
}
