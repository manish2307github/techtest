package com.techtest.exception;

/**
 * FILE_NAME: ServiceException.java
 * 
 * MODULE DESCRIPTION: This exception class maintains service related exceptions
 * 
 * Sep 16, 2018 2:14:01 PM 2018
 * 
*/
public class ServiceException extends Exception {
	private static final long serialVersionUID = -1303172439293583927L;

	public ServiceException(final String errorMsg, final Throwable throwable){
		super(errorMsg, throwable);
	}
}
