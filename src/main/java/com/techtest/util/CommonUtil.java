package com.techtest.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.testng.log4testng.Logger;

/**
 * FILE_NAME: CommonUtil.java
 * 
 * MODULE DESCRIPTION: Common utility class for common methods
 * 
 * Sep 16, 2018 2:18:19 PM 2018
 * 
 * 
*/
public class CommonUtil {
	private static final Logger LOGGER = Logger.getLogger(CommonUtil.class);
	private CommonUtil(){
		
	}
	public static Date calculateDateMMDDYY(final String date) {
		final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		Date firstDate = null;
		try {
			firstDate = sdf.parse(date);
		} catch (ParseException e) {
			LOGGER.error(e);
		}
		return firstDate;
	}
	
	public static boolean getDifferenceFromCurrentDate(final Date inputDate){
		long diffInMillies = Math.abs(new Date().getTime() - inputDate.getTime());
		long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
		if(diff>730){
			LOGGER.debug("user is more than 2 years old");
			return true;
		} else{
			LOGGER.debug("user is less than 2 years old");
			return false;
		}
	}
}
