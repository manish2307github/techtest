package com.techtest.services.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.techtest.dao.IBackendService;
import com.techtest.exception.ServiceException;
import com.techtest.services.IUserProductService;

/**
 * FILE_NAME: UserProductServiceImplTest.java
 * 
 * MODULE DESCRIPTION: Test class to test product price
 * 
 * Sep 16, 2018 9:57:55 PM 2018
 * 
 * 
*/
public class UserProductServiceImplTest {
	
	private IUserProductService userProdService;
	@Mock
	private IBackendService backendServiceDao;
	
	@Mock
	private IUserProductService userProdServiceMock;
	
	private String[] prodArray;
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
//		backendServiceDao =  new BackendServiceImpl();
		userProdService =  new UserProductServiceImpl();
		prodArray = new String[2];
		prodArray[0] = "L_S_SOAP";
		prodArray[1] = "HUL_PASTE";
	}
	
	/**
	 * no discount
	 */
	@Test
	public void userNormal() {
		try {
			assertEquals(Double.valueOf(13),Double.valueOf(userProdService.getProductPrice(new Double("9897897646"), Arrays.asList(prodArray))));
		} catch (NumberFormatException e) {
			assertNull(e);
		} catch (ServiceException e) {
			assertNull(e);
		}
	}
	/**
	 * 30 % discount
	 */
	@Test
	public void userEmployee() {
		try {
			assertEquals(Double.valueOf(9.1),Double.valueOf(userProdService.getProductPrice(new Double("9897897647"), Arrays.asList(prodArray))));
		} catch (NumberFormatException e) {
			assertNull(e);
		} catch (ServiceException e) {
			assertNull(e);
		}
	}
	/**
	 * 10% discount
	 */
	@Test
	public void userAffiliate() {
		try {
			assertEquals(Double.valueOf(11.7),Double.valueOf(userProdService.getProductPrice(new Double("9897827646"), Arrays.asList(prodArray))));
		} catch (NumberFormatException e) {
			assertNull(e);
		} catch (ServiceException e) {
			assertNull(e);
		}
	}
	
	/**
	 * 2 year old normal customer wil get 5% discount
	 */
//	@Test
//	public void user2YearOldNormal() {
//		try {
//			prodArray = new String[2];
//			prodArray[0] = "L_S_SOAP";
//			prodArray[1] = "HUL_PASTE";
//			assertEquals(Double.valueOf(12.35),Double.valueOf(userProdService.getProductPrice(new Double("9812897649"), Arrays.asList(prodArray))));
//		} catch (NumberFormatException e) {
//			assertNull(e);
//		} catch (ServiceException e) {
//			assertNull(e);
//		}
//	}
	
	/**
	 * amount greater than 100(Total amount --682) ---5% discount
	 */
	@Test
	public void usermoreShoppingOldNormal() {
		try {
			prodArray[1] = "LG_ELE_AC";
			assertEquals(Double.valueOf(647.9),Double.valueOf(userProdService.getProductPrice(new Double("9897897646"), Arrays.asList(prodArray))));
		} catch (NumberFormatException e) {
			assertNull(e);
		} catch (ServiceException e) {
			assertNull(e);
		}
	}
	
	/**
	 * Total value 5 $----
	 * discount 30% but only on 4$
	 */
	@Test
	public void usermoreShoppingOldEmployee() {
		try {
			prodArray[1] = "T_Salt";
			assertEquals(Double.valueOf(3.8),Double.valueOf(userProdService.getProductPrice(new Double("9897897647"), Arrays.asList(prodArray))));
		} catch (NumberFormatException e) {
			assertNull(e);
		} catch (ServiceException e) {
			assertNull(e);
		}
	}

	/**
	 * Total value 5 $----
	 * discount 30% but only on 4$
	 */
	@Test
	public void usermoreShoppingOldEmployee1() {
		try {
			prodArray[1] = "T_CHAT";
			assertEquals(Double.valueOf(3.8),Double.valueOf(userProdService.getProductPrice(new Double("9897897647"), Arrays.asList(prodArray))));
		} catch (NumberFormatException e) {
			assertNull(e);
		} catch (ServiceException e) {
			assertNull(e);
		}
	}
	
}
