package com.techtest.services.impl;

import java.util.List;

import org.testng.log4testng.Logger;

import com.techtest.dao.IBackendService;
import com.techtest.dao.impl.BackendServiceImpl;
import com.techtest.dto.ProductData;
import com.techtest.dto.UserData;
import com.techtest.exception.ExcelLoadException;
import com.techtest.exception.ServiceException;
import com.techtest.services.IUserProductService;
import com.techtest.util.CommonUtil;
import com.techtest.util.ExceptionMapper;
import com.techtest.util.RetailConstants;

/**
 * FILE_NAME: UserProductServiceImpl.java
 * 
 * MODULE DESCRIPTION: This is the implementation of main service that will return actual price of product
 * 
 * Sep 16, 2018 9:57:55 PM 2018
 * 
 * 
*/
public class UserProductServiceImpl implements IUserProductService {
	private static final Logger LOGGER = Logger.getLogger(UserProductServiceImpl.class);
	
	private IBackendService backendServiceDao;
	public UserProductServiceImpl(){
		backendServiceDao =  new BackendServiceImpl();
	}

	/**
	 * Calculate total price of product from product catalog
	 * Here assumption is one type of discount will be apply
	 */
	@Override
	public double getProductPrice(Double msisdn, List<String> productIdList) throws ServiceException {
		double totalCartOrderPrice = 0;
		double discountedPrice = 0;
		double nonDiscountedPrice = 0;
		int discountPer = 0;
		try {
			final UserData userObj = backendServiceDao.getUserInfo(msisdn);
			System.out.println(msisdn+ "<<MSISDND>>>>USER OBJ>>" + userObj);
			final List<ProductData> productList =   backendServiceDao.getStoreDataInfo(productIdList);
			for (ProductData productData : productList) {
				if(RetailConstants.ProductType.Grocery.getValue().equals(productData.getProductCategory())){
					LOGGER.debug("Product is non discounted");
					nonDiscountedPrice = nonDiscountedPrice+ productData.getProductPrice();
				} else{
					LOGGER.debug("Product is discounted");
					discountedPrice = discountedPrice+ productData.getProductPrice();
				}
			}
			totalCartOrderPrice = nonDiscountedPrice + discountedPrice;
			LOGGER.debug("Total cart order price" + totalCartOrderPrice );
			LOGGER.debug("getUserType: " + userObj.getUserType() );
			if(RetailConstants.UserType.EMPLOYEE.getValue().equals(userObj.getUserType())){
				LOGGER.debug("USer type is employee-->> so dicount will be 30%" );
				discountPer = 30;
			} else if (RetailConstants.UserType.AFFILIATE.getValue().equals(userObj.getUserType())) {
				LOGGER.debug("USer type is AFFILIATE-->> so dicount will be 10%" );
				discountPer = 10;
			} else if(CommonUtil.getDifferenceFromCurrentDate(userObj.getRegistrationDate())) {
				discountPer = 5;
			} else if(totalCartOrderPrice>100){
				discountPer = 5;
			}
		} catch (ExcelLoadException e) {
			LOGGER.error(e);
			throw ExceptionMapper.mapDBExceptionToSPException(e);
		}
		return nonDiscountedPrice+ (discountedPrice-(discountedPrice*discountPer/100));
	}

}
