package com.techtest.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.testng.log4testng.Logger;

import com.techtest.dao.IBackendService;
import com.techtest.dto.ProductData;
import com.techtest.dto.UserData;
import com.techtest.exception.ExcelLoadException;
import com.techtest.util.ExcelLoader;

/**
 * FILE_NAME: BackendServiceImpl.java
 * 
 * MODULE DESCRIPTION: Used to fetch data from db and store
 * Note: as db is not present, keeping all the things in excel and loading file
 * 
 * Sep 16, 2018 3:44:33 PM 2018
 * 
*/
public class BackendServiceImpl implements IBackendService {
	
	private static final Logger LOGGER = Logger.getLogger(BackendServiceImpl.class);
	
	/**
	 * To get the user information
	 * @param userId
	 * @return
	 * @throws ExcelLoadException 
	 * @throws DBException
	 */
	@Override
	public UserData getUserInfo(final Double msisdn) throws ExcelLoadException{
		LOGGER.debug("getUserInfo start" );
		return getCacheData(msisdn);
	}
	
	public UserData getCacheData(final Double msisdn) throws ExcelLoadException{
		return ExcelLoader.getInstance().getUserMapData().get(msisdn);
	}
	/**
	 * To get the product information by product Id
	 * @param prodId
	 * @return
	 * @throws ExcelLoadException 
	 * @throws DBException
	 */
	@Override
	public List<ProductData> getStoreDataInfo(final List<String> productIdList) throws ExcelLoadException {
		LOGGER.debug("getStoreDataInfo start" );
		List<ProductData> productList = new ArrayList<>();
		for (String productId : productIdList) {
			productList.add(getStoreDataInfo(productId));
		}
		return productList;
	}
	
	public ProductData getStoreDataInfo(String prodId) throws ExcelLoadException {
		LOGGER.debug("getStoreDataInfo start" );
		return ExcelLoader.getInstance().getProtuctMapData().get(prodId);
	}
}
