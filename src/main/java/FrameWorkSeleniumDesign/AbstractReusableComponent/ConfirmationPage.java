package FrameWorkSeleniumDesign.AbstractReusableComponent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmationPage extends AbstractComponent {
	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(css=".hero-primary")
	WebElement ConfirmationMessage;
	
	public String GetConfirmationMessage()
	{
		return ConfirmationMessage.getText();
		
	}
	
}
