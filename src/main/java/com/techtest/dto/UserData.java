package com.techtest.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * FILE_NAME: UserData.java
 * 
 * MODULE DESCRIPTION: To store user information in user bean 
 * 
 *  Sep 16, 2018 7:57:14 PM 2018
 * 
 * 
*/
public class UserData implements Serializable{
	private static final long serialVersionUID = 1601224355049119716L;
	private String userName;
	private String msisdn;
	private String userType;
	private Date registrationDate;
	
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the userType
	 */
	public String getUserType() {
		return userType;
	}
	/**
	 * @param userType the userType to set
	 */
	public void setUserType(String userType) {
		this.userType = userType;
	}
	/**
	 * @return the msisdn
	 */
	public String getMsisdn() {
		return msisdn;
	}
	/**
	 * @param msisdn the msisdn to set
	 */
	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}
	/**
	 * @return the registrationDate
	 */
	public Date getRegistrationDate() {
		return registrationDate;
	}
	/**
	 * @param registrationDate the registrationDate to set
	 */
	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

}
