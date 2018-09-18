package com.techtest.dao;

import java.util.List;

import com.techtest.dto.ProductData;
import com.techtest.dto.UserData;
import com.techtest.exception.ExcelLoadException;

/**
 * 
 * FILE_NAME: IBackendService.java
 * 
 * MODULE DESCRIPTION: This service contains the information related to user data
 * 
 * Sep 16, 2018 2:03:09 PM 2018
 * 
 *
 */
public interface IBackendService {
	
	/**
	 * To get the user information
	 * @param msisdn
	 * @return
	 * @throws DBException
	 */
	public UserData getUserInfo(final Double msisdn) throws ExcelLoadException;
	
	/**
	 * To get the product information by product Id
	 * @param productIdList
	 * @return
	 * @throws DBException
	 */
	public List<ProductData> getStoreDataInfo(final List<String> productIdList) throws ExcelLoadException;
}
