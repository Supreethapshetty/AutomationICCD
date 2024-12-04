package FrameWorkSelenium.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

	//ExtentReports extent;
	public static ExtentReports getReportObject()
	{
	String path=System.getProperty("user.dir")+"\\reports\\index.html";		//ExtentReports
		ExtentSparkReporter reporter= new ExtentSparkReporter(path);
		reporter.config().setReportName("Web automation demo1 result");
		reporter.config().setDocumentTitle("Test Results");
		
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("test", "Supreetha Shetty");
		return extent;
	}
	
}
