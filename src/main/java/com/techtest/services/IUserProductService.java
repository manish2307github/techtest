package com.techtest.services;

import java.util.List;

import com.techtest.exception.ServiceException;

/**
 * 
 * FILE_NAME: IUserProductService.java
 * 
 * MODULE DESCRIPTION: This service contains the information related to user data, and will return the product price
 * 
 * Sep 16, 2018 2:03:09 PM 2018
 * 
 *
 */
public interface IUserProductService {
	/**
	 * Is used to calculate total price of product
	 * @param msisdn
	 * @param productId
	 * @return
	 * @throws ServiceException
	 */
	public double getProductPrice(final Double msisdn, final List<String> productId) throws ServiceException;
}
