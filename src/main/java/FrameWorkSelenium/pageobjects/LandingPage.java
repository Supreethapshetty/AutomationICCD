package FrameWorkSelenium.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import FrameWorkSeleniumDesign.AbstractReusableComponent.AbstractComponent;

public class LandingPage extends AbstractComponent{

	
		// TODO Auto-generated method stub
		
		WebDriver driver;
		
		public LandingPage(WebDriver driver)
		{
			super(driver);
			this.driver=driver;
			PageFactory.initElements(driver, this);// initelement takes the driver from the constructer and assigns in the below code usingthe driver of current driver
		}
		
	//	WebElement userEmail=driver.findElement(By.id("userEmail"));
		//page object should not hold any kind of data
		//page factory

		//==constructed as line 22 in run time
		@FindBy(id="userEmail") 
		private WebElement userEmailadressElement;
		
		@FindBy(id="userPassword") 
		private WebElement passwordElement;
		
		@FindBy(id="login") 
		private WebElement CTAElement;
		
		@FindBy(css="[class*='FlyInOut']") 
		private WebElement DisplayError;
		
		
		//action
		public addToCart loginApplication(String emailid, String PassWord ) {
			userEmailadressElement.sendKeys(emailid);
			passwordElement.sendKeys(PassWord);
			CTAElement.click();
			addToCart lobject = new addToCart(driver);
			return lobject;
		}
		
		public void URL()
		{
			driver.get("https://rahulshettyacademy.com/client");
		}
		
		public String ErroMessage()
		{
			waitForWebElementToAppear(DisplayError);
			return DisplayError.getText();
		}
	}


