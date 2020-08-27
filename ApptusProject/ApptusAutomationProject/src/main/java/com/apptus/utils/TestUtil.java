package com.apptus.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.apptus.base.TestBase;

public class TestUtil extends TestBase {

	private String TESTDATA_SHEET_PATH = "/ApptusProject/ApptusAutomationProject/ExcelSheet/TestData.xlsx";
	private String SCREEN_SHOTS_FOLDER_PATH = "/ApptusProject/ApptusAutomationProject/ScreenShots/";
	private String CONFIG_PROP_PATH = "/ApptusProject/ApptusAutomationProject/src/main/java/com/apptus/config/config.properties";
	private FileInputStream fis;
	private Workbook wb;
	private String cellValue, Value; 
	private int sceenShotCounter = 1;
	private Properties prop;
	private File scrFile,DestFile;
	
	public String getCellValue(String sheetName, int row, int cell) throws InvalidFormatException, IOException  {

		fis = new FileInputStream(TESTDATA_SHEET_PATH);
		wb = WorkbookFactory.create(fis);
		cellValue = wb.getSheet(sheetName).getRow(row).getCell(cell).toString();
		System.out.println("The cell value fetched from Excel sheet is: " + cellValue);
		return cellValue;
	}

	public void takeScreenShot(WebDriver driver) {
		scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		DestFile=  new File(SCREEN_SHOTS_FOLDER_PATH + "ScreenShot by Listener " + sceenShotCounter++ +".png");

		try {
			FileUtils.copyFile(scrFile, DestFile);
			System.out.println("***Placed screen shot in " + SCREEN_SHOTS_FOLDER_PATH + " ***");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void takeScreenShot(WebDriver driver, String screenshotName) {
		scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		DestFile=  new File(SCREEN_SHOTS_FOLDER_PATH + screenshotName + ".png");

		try {
			FileUtils.copyFile(scrFile, DestFile);
			System.out.println("***Placed screen shot in " + SCREEN_SHOTS_FOLDER_PATH + " ***");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getConfigPropValue(String key) throws FileNotFoundException  {
		fis = new FileInputStream(CONFIG_PROP_PATH);
		prop = new Properties();
		Value = prop.getProperty(key);
		return Value;
	}
}
