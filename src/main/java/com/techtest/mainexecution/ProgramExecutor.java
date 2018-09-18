package com.techtest.mainexecution;

import java.util.Arrays;

import com.techtest.exception.ServiceException;
import com.techtest.services.IUserProductService;
import com.techtest.services.impl.UserProductServiceImpl;

/**
 * FILE_NAME: ProgramExecutor.java
 * 
 * MODULE DESCRIPTION: This is the main thread to execute the program
 * Can be executed by executable jar from command line or from editor 
 * 
 * Sep 16, 2018 8:28:42 PM 2018
 * 
 * 
*/
public class ProgramExecutor {
	public static void main(String[] args) {
		final IUserProductService producService = new UserProductServiceImpl();
		String[] prodArray = {"L_S_SOAP","HUL_PASTE"};
		try {
			System.out.println(producService.getProductPrice(new Double("9812897649"), Arrays.asList(prodArray)));
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
}
