package FrameWorkSelenium.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import FrameWorkSeleniumDesign.AbstractReusableComponent.AbstractComponent;

public class addToCart extends AbstractComponent {

	// TODO Auto-generated method stub

	WebDriver driver;

	public addToCart(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);// initelement takes the driver from the constructer and assigns in the
												// below code usingthe driver of current driver
	}

	// List<WebElement> products= driver.findElements(By.cssSelector(".mb-3"));

	@FindBy(css = ".mb-3")
	List<WebElement> itemswebelement;

	@FindBy(css = ".ng-animation")
	WebElement spinner;

	By productElement = By.cssSelector(".mb-3");
	By addTocardProduct = By.cssSelector(".card-body button:last-of-type");
	By ToastMessage = By.cssSelector("#toast-container");

	public List<WebElement> getProductList() {
		waitForElementToAppear(productElement);
		return itemswebelement;
	}

	public WebElement getProductName(String productWebElement) {
		WebElement prod = getProductList().stream().filter(
				webelements1 -> webelements1.findElement(By.cssSelector("b")).getText().equals(productWebElement))
				.findFirst().orElse(null);
		return prod;
	}

	public void AddProductToCart(String productWebElement) throws InterruptedException {
		WebElement prod = getProductName(productWebElement);
		prod.findElement(addTocardProduct).click();
		waitForElementToAppear(ToastMessage);
		waitForElementToDisappear(spinner);
	}

}
