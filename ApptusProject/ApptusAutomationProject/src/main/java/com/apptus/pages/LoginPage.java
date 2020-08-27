package com.apptus.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.apptus.base.TestBase;

public class LoginPage extends TestBase {
	
	private WebDriverWait wait = new WebDriverWait(driver, 60);
	
	@FindBy(xpath="//a[@class='login']")
	private WebElement SignInButton;
	
	@FindBy(xpath="//input[@id='email']")
	private WebElement alreadyRegisteredEmailAddressTextBox;
	
	@FindBy(xpath="//input[@id='passwd']")
	private WebElement alreadyRegisteredPasswordTextBox;
	
	@FindBy(xpath="//button[@id='SubmitLogin']")
	private WebElement alreadyRegisteredSignInButton;
	
	public LoginPage() {

		PageFactory.initElements(driver, this);
	}
	
	public void clickOnSignInButton(){
		SignInButton.click();
	}
	
	public void enterEmailInalreadyRegisteredEmailAddressTextBox(String email){
		alreadyRegisteredEmailAddressTextBox.sendKeys(email);
	}
	
	public void enterPasswordInalreadyRegisteredPasswordTextBox(String password){
		alreadyRegisteredPasswordTextBox.sendKeys(password);
	}
	
	public void clickOnAlreadyRegisteredSignInButton(){
		alreadyRegisteredSignInButton.click();
	}
	
	public CataloguePage loginIntoApptus(String emailId, String password){
		
		clickOnSignInButton();
		wait.until(ExpectedConditions.visibilityOf(alreadyRegisteredEmailAddressTextBox));		
		enterEmailInalreadyRegisteredEmailAddressTextBox(emailId);
		enterPasswordInalreadyRegisteredPasswordTextBox(password);
		clickOnAlreadyRegisteredSignInButton();
		return new CataloguePage();
	}
}
