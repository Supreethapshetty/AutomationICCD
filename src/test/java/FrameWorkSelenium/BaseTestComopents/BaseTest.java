package FrameWorkSelenium.BaseTestComopents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import FrameWorkSelenium.pageobjects.LandingPage;

public class BaseTest {

	public WebDriver driver;
	public Properties prop;
	public LandingPage lobject;

	public WebDriver initialiseDriver() throws IOException {
		prop = new Properties();

		// String filePath =
		// System.getProperty("user.dir")+"/src/main/java/FrameWorkSelenium/resources/GlobalData.properties";)
		FileInputStream fis = new FileInputStream("./src/main/java/FrameWorkSelenium/resources/GlobalData.properties");
		prop.load(fis);

		// ternarory operator
		//System.getProperty takes the input from the terminal  cmd maven command
		String browserName = System.getProperty("browser") != null ? System.getProperty("browser")
				: prop.getProperty("browser");

		// String browserName = prop.getProperty("browser");

		if (browserName.contains("chrome")) {
			ChromeOptions options=new ChromeOptions();
			
			if (browserName.contains("headless"))
			{
			options.addArguments("headless");
			}
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440,990));
		}

		else if (browserName.equalsIgnoreCase("firefox")) {
			// firefox
			//System.setProperty("webdriver.gecko.driver", "C:\\Users\\supre\\OneDrive\\Desktop\\firefox");
			driver = new FirefoxDriver();
		}

		else if (browserName.equalsIgnoreCase("edge")) {
			// edge
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}

	public List<HashMap<String, String>> getJsonDataToMap(String path) throws IOException {
		// json to string
		String Jsondata = FileUtils.readFileToString(new File(path), StandardCharsets.UTF_8);
		// string to hashmap
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(Jsondata,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;
	}

//	public String getScreenShot(String TestCaseName, WebDriver driver) throws IOException
//	{
//		TakesScreenshot tS= (TakesScreenshot)driver;
//		File ScreenShot=tS.getScreenshotAs(OutputType.FILE);
//		File file= new File(System.getProperty("user.dir")+"//reports//"+TestCaseName+".png");
//		FileUtils.copyFile(ScreenShot, file);
//		return System.getProperty("user.dir")+"//reports//"+TestCaseName+".png";
//	}
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
	}

	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplicatio() throws IOException {
		driver = initialiseDriver();
		lobject = new LandingPage(driver);
		lobject.URL();
		return lobject;
	}

	@AfterMethod(alwaysRun = true)
	public void closeBrowser() {
		driver.quit();
	}
}
