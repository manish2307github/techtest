package com.techtest.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.log4testng.Logger;

import com.techtest.dto.ProductData;
import com.techtest.dto.UserData;
import com.techtest.exception.ExcelLoadException;

/**
 * FILE_NAME: ExcelLoader.java
 * 
 * MODULE DESCRIPTION: To Load excel singleton instancce
 * 
 *Sep 16, 2018 8:24:34 PM 2018
 * 
 * 
*/
public class ExcelLoader {
	private static final Logger LOGGER = Logger.getLogger(ExcelLoader.class);
	private static ExcelLoader excelLoaderObj = null;
	private Map<Double, UserData> userMap;
	private Map<String, ProductData> productMap;
	private ExcelLoader() throws ExcelLoadException{
		this.userMap = loadUserData();
		this.productMap = loadProductData();
	}
	public static ExcelLoader getInstance() throws ExcelLoadException{ 
		if(excelLoaderObj ==  null){
			excelLoaderObj =  new  ExcelLoader();
		} else{
			return excelLoaderObj;
		}
		return excelLoaderObj;
	}

	private Map<Double, UserData> loadUserData() throws ExcelLoadException {
		File file = new File(ClassLoader.getSystemResource("User_Data.xlsx").getPath());
		FileInputStream inputStream;
		try {
			inputStream = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			throw new ExcelLoadException("User Data File is not present", e);
		}
		Workbook workbook = null;
		try {
			workbook = WorkbookFactory.create(inputStream);
		} catch (EncryptedDocumentException | InvalidFormatException | IOException e) {
			throw new ExcelLoadException("Workbook processing issue in user data", e);
		}
		LOGGER.debug("Retrieving Sheets using Iterator from user data");
		Sheet sheet = workbook.getSheetAt(0);
		final Map<Double, UserData> userMap =  new HashMap<>();
		for (Row row : sheet) {
			if(row.getRowNum()==0)
				continue;
			Iterator<Cell> cellIterator = row.cellIterator();
			while (cellIterator.hasNext()) {
				final UserData userDataObj =  new UserData();
                userDataObj.setUserName(cellIterator.next().getStringCellValue());
				userDataObj.setUserType(cellIterator.next().getStringCellValue());
				userDataObj.setRegistrationDate(CommonUtil.calculateDateMMDDYY(cellIterator.next().getStringCellValue()));
				userMap.put(new Double(cellIterator.next().getNumericCellValue()), userDataObj);
            }
		}
		LOGGER.debug("Testing map: "+ userMap);
		return userMap;
	}
	/**
	 * To load store product data, as db is not present singelton instance is created to laod data
	 * @return
	 * @throws EncryptedDocumentException
	 * @throws InvalidFormatException
	 * @throws IOException
	 */
	private Map<String, ProductData> loadProductData() throws ExcelLoadException {
		File file = new File(ClassLoader.getSystemResource("Store_Data.xlsx").getPath());
		FileInputStream inputStream;
		try {
			inputStream = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			LOGGER.error(e);
			throw new ExcelLoadException("ProductData File is not present", e);
		}
		Workbook workbook = null;
		LOGGER.debug("Retrieving Sheets using Iterator from store data");
		try {
			workbook = WorkbookFactory.create(inputStream);
		} catch (EncryptedDocumentException | InvalidFormatException | IOException e) {
			LOGGER.error(e);
			throw new ExcelLoadException("Workbook processing issue in product data", e);
		}
		Sheet sheet = workbook.getSheetAt(0);
		final Map<String, ProductData> prodMap =  new HashMap<>();
		for (Row row : sheet) {
			if(row.getRowNum()==0)
				continue;
			Iterator<Cell> cellIterator = row.cellIterator();
			while (cellIterator.hasNext()) {
				final ProductData productObj =  new ProductData();
				final String productCode = cellIterator.next().getStringCellValue();
				productObj.setProductCategory(cellIterator.next().getStringCellValue());
				productObj.setProductName(cellIterator.next().getStringCellValue());
				productObj.setProductPrice(cellIterator.next().getNumericCellValue());
				productObj.setProductDescription(cellIterator.next().getStringCellValue());
				prodMap.put(productCode, productObj);
            }
		}
		LOGGER.debug("Testing product Map: "+ prodMap);
		return prodMap;
	}
	
	public Map<Double, UserData> getUserMapData() {
		return this.userMap;
	}
	
	public Map<String, ProductData> getProtuctMapData() {
		return this.productMap;
	}
}
