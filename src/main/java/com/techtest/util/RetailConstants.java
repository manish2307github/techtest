package com.techtest.util;

/**
 * FILE_NAME: RetailConstants.java
 * 
 * MODULE DESCRIPTION: Comnstant class for retail app
 * 
 * Sep 16, 2018 10:11:09 PM 2018
 * 
 * 
*/
public class RetailConstants {
	public enum UserType{
		NORMAL("Normal"),AFFILIATE("Affiliate"),EMPLOYEE("Employee");
		private String userType;
		UserType(String userTypeInput){
			this.userType = userTypeInput;
		}
		public String getValue(){
			return this.userType;
		}
	}
	public enum ProductType{
		Grocery("Grocery"),OTHERS("OTHERS");
		private String prodType;
		ProductType(String prodTypeInput){
			this.prodType = prodTypeInput;
		}
		public String getValue(){
			return this.prodType;
		}
	}
}
