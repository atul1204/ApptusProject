package com.apptus.testcases;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.apptus.base.TestBase;
import com.apptus.listener.CustomListeners;
import com.apptus.pages.CataloguePage;
import com.apptus.pages.LoginPage;
import com.apptus.utils.TestUtil;

@Listeners(CustomListeners.class)
public class CatalogueDetailsVerificationTest extends TestBase {

	private TestUtil utils;
	private String emailAddress, password, textMessageAfterAddingToCartActual, nameOfProductAddedActual, totalPriceOfProductAddedActual,colorAndSizeOfProductAddedActual, quantityOfProductAddedActual;
	private String textMessageAfterAddingToCartExpected, nameOfProductAddedExpected, totalPriceOfProductAddedExpected,colorAndSizeOfProductAddedExpected, quantityOfProductAddedExpected;
	private CataloguePage cataloguePage;
	private LoginPage loginPage;

	public CatalogueDetailsVerificationTest() {
		super();
	}

	@BeforeMethod(alwaysRun = true)
	public void setUp() {
		initialization();
		loginPage = new LoginPage();
		cataloguePage = new CataloguePage();
		utils = new TestUtil();
	}
	
	@Test(priority = 1, invocationCount = 1, groups ="UITest")
	public void assertCatalogueDetails() throws InvalidFormatException, IOException{
		
		emailAddress = utils.getCellValue("Sheet1",1, 1);
		password     = utils.getCellValue("Sheet1",1, 2);
		textMessageAfterAddingToCartExpected = utils.getCellValue("Sheet2",1, 2);
		nameOfProductAddedExpected = utils.getCellValue("Sheet2",2, 2);
		totalPriceOfProductAddedExpected = utils.getCellValue("Sheet2",3, 2);
		colorAndSizeOfProductAddedExpected = utils.getCellValue("Sheet2",4, 2);
		quantityOfProductAddedExpected = utils.getCellValue("Sheet2",5, 2);

		loginPage.loginIntoApptus(emailAddress, password);
		cataloguePage.clickOnTShirtsCategoryButton();
		utils.takeScreenShot(driver, "AfterClickingOnTShirtCategory");
		cataloguePage.clickOnTitleLinkForThetShirt();
		utils.takeScreenShot(driver, "AfterClickingOnTShirtLink");
		cataloguePage.clickOnAddToCartButton();
		
		textMessageAfterAddingToCartActual = cataloguePage.getTextMessageAfterAddingToCart();
		nameOfProductAddedActual = cataloguePage.getNameOfProductAdded();
		colorAndSizeOfProductAddedActual = cataloguePage.getColorAndSizeOfProductAdded();
		quantityOfProductAddedActual = cataloguePage.getQuantityOfProductAdded();
		totalPriceOfProductAddedActual = cataloguePage.getTotalPriceOfProductAdded();
		
		Assert.assertEquals(textMessageAfterAddingToCartActual, textMessageAfterAddingToCartExpected);
		Assert.assertEquals(nameOfProductAddedActual, nameOfProductAddedExpected);
		Assert.assertEquals(colorAndSizeOfProductAddedActual, colorAndSizeOfProductAddedExpected);
		Assert.assertEquals(totalPriceOfProductAddedActual, totalPriceOfProductAddedExpected);
		Assert.assertEquals(quantityOfProductAddedActual+".0", quantityOfProductAddedExpected);
		
	}
	
	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.quit();
	}
}
