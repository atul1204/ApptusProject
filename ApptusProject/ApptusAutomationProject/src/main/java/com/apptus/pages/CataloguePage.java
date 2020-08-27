package com.apptus.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.apptus.base.TestBase;

public class CataloguePage extends TestBase {
	
	private WebDriverWait wait = new WebDriverWait(driver, 60);
	private List<WebElement> findElements;
	
	@FindBy(xpath="//a[@class='login']")
	WebElement signInButton;
	
	@FindBy(xpath="//a[@class='logout']")
	WebElement logoutButton;
	
	@FindBy(xpath="//a[@title='T-shirts']")
	WebElement tShirtsCategoryButton;
	
	@FindBy(xpath="//a[@title='Faded Short Sleeve T-shirts'][@class='product-name']")
	WebElement titleLinkForThetShirt;
	
	@FindBy(xpath="//span[text()='Add to cart']")
	WebElement addToCartButton;
	
	@FindBy(xpath="//i[@class='icon-ok']/..")
	WebElement messageAfterAddingInCart;
	
	@FindBy(xpath="//div[@class='layer_cart_product_info']//span[@id='layer_cart_product_title']")
	WebElement nameOfProductAdded;
	
	@FindBy(xpath="//div[@class='layer_cart_product_info']//span[@id='layer_cart_product_attributes']")
	WebElement colorAndSizeOfProductAdded;
	
	@FindBy(xpath="//div[@class='layer_cart_product_info']//span[@id='layer_cart_product_quantity']")
	WebElement quantityOfProductAdded;
	
	@FindBy(xpath="//div[@class='layer_cart_product_info']//span[@id='layer_cart_product_price']")
	WebElement totalPriceOfProductAdded;
	
	@FindBy(xpath="//a[@title='Proceed to checkout']")
	WebElement proceedToCheckoutButton;
	
	public CataloguePage() {

		PageFactory.initElements(driver, this);
	}
	
	public void clickOnSignInButton(){
		signInButton.click();
	}
	
	public void clickOnTShirtsCategoryButton(){
		
		findElements = driver.findElements(By.xpath("//a[@title='T-shirts']"));
		findElements.get(1).click();
	}
	
	public void clickOnTitleLinkForThetShirt(){
		titleLinkForThetShirt.click();
	}

	public void clickOnAddToCartButton(){
		addToCartButton.click();
		wait.until(ExpectedConditions.visibilityOf(messageAfterAddingInCart));		
	}
	
	public String getTextMessageAfterAddingToCart(){
		return messageAfterAddingInCart.getText();
	}
	
	public String getNameOfProductAdded(){
		return nameOfProductAdded.getText();
	}
	
	public String getColorAndSizeOfProductAdded(){
		return colorAndSizeOfProductAdded.getText();
	}
	
	public String getQuantityOfProductAdded(){
		return quantityOfProductAdded.getText();
	}
	
	public String getTotalPriceOfProductAdded(){
		return totalPriceOfProductAdded.getText();
	}
	
	public void clickOnProceedToCheckoutButton(){
		proceedToCheckoutButton.click();
	}
	
	public WebElement getTShirtsCategoryButton(){
		return tShirtsCategoryButton;
	}
	
	public void clickOnLogOutoutButton(){
		logoutButton.click();
	}
}
