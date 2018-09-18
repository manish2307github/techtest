package com.techtest.util;

import com.techtest.exception.ExcelLoadException;
import com.techtest.exception.ServiceException;

/**
 * FILE_NAME: ExceptionMapper.java
 * 
 * MODULE DESCRIPTION: Used to map Exception from DB-Dao(Excel loaded data) layer to service layer
 * 
 * Sep 16, 2018 2:15:01 PM 2018
 * 
*/
public class ExceptionMapper {
	
	private ExceptionMapper(){
		
	}
	
	public static ServiceException mapDBExceptionToSPException(final ExcelLoadException dbExp){
		return new ServiceException(dbExp.getMessage(), dbExp);
	}

}
