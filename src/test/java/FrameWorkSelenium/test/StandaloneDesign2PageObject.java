package FrameWorkSelenium.test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
//import java.time.Duration;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.interactions.Action;
//import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import FrameWorkSelenium.BaseTestComopents.BaseTest;
import FrameWorkSelenium.pageobjects.LandingPage;
import FrameWorkSelenium.pageobjects.addToCart;
import FrameWorkSeleniumDesign.AbstractReusableComponent.CheckoutPage;
import FrameWorkSeleniumDesign.AbstractReusableComponent.ConfirmationPage;
import FrameWorkSeleniumDesign.AbstractReusableComponent.OrderPage;

@Listeners(FrameWorkSelenium.BaseTestComopents.TestNGListeners.class)
public class StandaloneDesign2PageObject extends BaseTest {

	String ProductName = "ADIDAS ORIGINAL";

	@Test(dataProvider = "getData", groups = { "purchase" })
	// public static void main(String[] args) throws InterruptedException {
	// TODO Auto-generated method stub
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {
		// WebDriverManager.chromedriver().setup();

		// initialiseDriver();
		// WebDriver driver = new ChromeDriver();
		// driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// LandingPage lobject = new LandingPage(driver);
		// lobject.URL();
		// LandingPage lobject=launchApplicatio() ;
		// driver.manage().window().maximize();

		// driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		// addToCart cartObject = new addToCart(driver);
		addToCart cartObject = lobject.loginApplication(input.get("email1"), input.get("password1"));

		List<WebElement> products = cartObject.getProductList();

		cartObject.AddProductToCart(input.get("productName1"));

		cartObject.gotoCartPage();
		// driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

		Boolean match = cartObject.VarifiyProductName(input.get("productName1"));

//		List<WebElement> cartproduct = driver.findElements(By.cssSelector(".cartSection h3"));
//		Boolean match = cartproduct.stream().anyMatch(cart -> cart.getText().equals("ADIDAS ORIGINAL"));
		Assert.assertTrue(match);

		CheckoutPage checkoutobject = cartObject.checkOutPage();
		checkoutobject.SelectCountry("India");
		ConfirmationPage confirmationMesageObject = checkoutobject.submitOrder();
		String ConfirmationMessage = confirmationMesageObject.GetConfirmationMessage();

		// driver.findElement(By.cssSelector(".totalRow button")).click();
		// Actions a = new Actions(driver);
		/// a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select
		// Country']")), "India").build().perform();
		// wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".form-group
		// section")));
		// driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
		// driver.findElement(By.cssSelector(".action__submit")).click();
		// String thankyou =
		// driver.findElement(By.cssSelector(".hero-primary")).getText();

		Assert.assertTrue(ConfirmationMessage.equalsIgnoreCase("Thankyou for the order."));

		System.out.println("pass");
		// driver.close();

	}

	@Test(dependsOnMethods = { "submitOrder" })
	public void OrderHistoryTest() {
		addToCart cartObject = lobject.loginApplication("chuppishetty3@gmail.com", "Chuppishetty3");
		OrderPage orderPage = cartObject.gotoOrderPage();
		Assert.assertTrue(orderPage.VarifiyProductNameInOrderList(ProductName));
	}
	


//@DataProvider
//public Object[][] getData()
//	HashMap<String, String> map =new HashMap<String,String>();
//	map.put("Email", "chuppishetty3@gmail.com");
//	map.put("password", "Chuppishetty3");
//	map.put("productName", "ADIDAS ORIGINAL");
//	
//	HashMap<String, String> map1 =new HashMap<String,String>();
//	map1.put("Email", "chuppishetty4@gmail.com");
//	map1.put("password", "Chuppishetty4");
//	map1.put("productName", "ZARA COAT 3");
	// return new Object[] [] {{map},{map1}};
//	
//	@DataProvider
//	public Object[][]getData1()
//	{
//		return new Object [] [] {{"chuppishetty3@gmail.com","Chuppishetty3","ADIDAS ORIGINAL"},{"chuppishetty4@gmail.com","Chuppishetty4","ZARA COAT 3"}}
//	}

	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> getJsonData = getJsonDataToMap(System.getProperty("user.dir")
				+ "\\src\\test\\java\\FrameWorkSeleniumDesign\\JsonData\\PurchaseOrder.json");
		return new Object[][] { { getJsonData.get(0) }, { getJsonData.get(1) } };
	}
}
