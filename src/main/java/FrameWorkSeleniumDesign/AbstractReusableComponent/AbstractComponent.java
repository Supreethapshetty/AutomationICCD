package FrameWorkSeleniumDesign.AbstractReusableComponent;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractComponent {
WebDriver driver;
	public AbstractComponent(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	public void waitForElementToAppear(By bilocater)
	{
		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(bilocater));
		
		//By. -> bilocator
	}
	
	public void waitForWebElementToAppear(WebElement we)
	{
		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(we));
		
		//By. -> bilocator
	}
	
	public void waitForElementToDisappear(WebElement ele) throws InterruptedException
	{
		Thread.sleep(1000);
//		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(5));
//		 wait.until(ExpectedConditions.invisibilityOf(ele));
	}
	
	@FindBy(css = "[routerlink*='cart']")
	WebElement HeaderCartWebelement;
	
	@FindBy(css = "[routerlink*='myorders']")
	WebElement HeaderMyOrderWebelement;
	
	
	
	public void gotoCartPage()
	{
		HeaderCartWebelement.click();
	}
	
	@FindBy(css=".cartSection h3")
	List<WebElement> ProductListCart ;
	

	@FindBy(css=".totalRow button")
	WebElement CheckOutCTA ;
	
	public Boolean VarifiyProductName(String productname)
	{
		Boolean match = ProductListCart.stream().anyMatch(cart -> cart.getText().equals(productname));
		return match;
	}
	
	public CheckoutPage checkOutPage()
	{
		CheckOutCTA.click();
	return new CheckoutPage(driver);
	}
	
	public OrderPage gotoOrderPage()
	{
		HeaderMyOrderWebelement.click();
		OrderPage orderPage  = new OrderPage(driver);
		
		return orderPage;
	}
	
}
