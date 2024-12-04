package FrameWorkSeleniumDesign.AbstractReusableComponent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage extends AbstractComponent {
	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}

	@FindBy(css = "[placeholder='Select Country']")
	WebElement countryTypeElement;

	@FindBy(xpath = "(//button[contains(@class,'ta-item')])[2]")
	WebElement countryselectedElement;

	@FindBy(css = ".action__submit")
	WebElement SubmitCTAElement;

	By resultCountrylist= By.cssSelector(".form-group section");
	public void SelectCountry(String CountryName) {

		Actions a = new Actions(driver);
		a.sendKeys(countryTypeElement, CountryName).build().perform();
		waitForElementToAppear(resultCountrylist);
		countryselectedElement.click();
	}
	
	public ConfirmationPage submitOrder()
	{
		SubmitCTAElement.click();
		return new ConfirmationPage(driver);
	}

}
