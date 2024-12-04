package FrameWorkSelenium.test;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.interactions.Action;
//import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import FrameWorkSelenium.BaseTestComopents.BaseTest;
import FrameWorkSelenium.BaseTestComopents.Retry;
import FrameWorkSelenium.pageobjects.addToCart;
@Listeners(FrameWorkSelenium.BaseTestComopents.TestNGListeners.class)
public class ErrorValidations extends BaseTest {

	@Test(groups={"ErrorHangling"}, retryAnalyzer=Retry.class)

	public void ErrorsubmitOrder() throws IOException, InterruptedException {
		// WebDriverManager.chromedriver().setup();
		String productname = "ADIDAS ORIGINAL";

		lobject.loginApplication("chuppishetty3@gmail.com", "Chuppishetty3");

		Assert.assertEquals("Incorrect email  password." , lobject.ErroMessage());
	
		
//		List<WebElement> products = cartObject.getProductList();
//
//		cartObject.AddProductToCart(productname);
//
//		cartObject.gotoCartPage();
//
//		Boolean match = cartObject.VarifiyProductName(productname);
//		Assert.assertTrue(match);
//
//		CheckoutPage checkoutobject = cartObject.checkOutPage();
//		checkoutobject.SelectCountry("India");
//		ConfirmationPage confirmationMesageObject = checkoutobject.submitOrder();
//		String ConfirmationMessage = confirmationMesageObject.GetConfirmationMessage();
//
//		Assert.assertTrue(ConfirmationMessage.equalsIgnoreCase("Thankyou for the order."));
//
//		System.out.println("pass");

	}
	
	@Test
	public void submitOrder1() throws IOException, InterruptedException
	{
		String productname = "ADIDAS ORIGINAL";
		addToCart cartObject = lobject.loginApplication("chuppishetty4@gmail.com", "Chuppi@4");
		List<WebElement> products = cartObject.getProductList();

		cartObject.AddProductToCart(productname);

		cartObject.gotoCartPage();
		// driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

		Boolean match = cartObject.VarifiyProductName("ADIDAS ORIGINAL3");

//		List<WebElement> cartproduct = driver.findElements(By.cssSelector(".cartSection h3"));
//		Boolean match = cartproduct.stream().anyMatch(cart -> cart.getText().equals("ADIDAS ORIGINAL"));
		Assert.assertTrue(match);

	
	}
	
	

}
