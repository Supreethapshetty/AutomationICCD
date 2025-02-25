package FrameWorkSeleniumDesign.AbstractReusableComponent;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderPage extends AbstractComponent {

	
	WebDriver driver;
	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement>  ListOFPRoductName ;
	
	
	public Boolean VarifiyProductNameInOrderList(String productname)
	{
		Boolean match = ListOFPRoductName.stream().anyMatch(cart -> cart.getText().equalsIgnoreCase(productname));
		return match;
	}
}
