package com.techtest.exception;

/**
 * FILE_NAME: ExcelLoadException.java
 * 
 * MODULE DESCRIPTION: Maintains Excel loading related exceptions
 * 
 *Sep 16, 2018 2:11:37 PM 2018
 * 
*/
public class ExcelLoadException extends Exception {
	private static final long serialVersionUID = -2032682137235600979L;
	public ExcelLoadException(final String errorMsg, final Throwable throwable){
		super(errorMsg, throwable);
	}
}
