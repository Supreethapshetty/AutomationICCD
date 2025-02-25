package FrameWorkSelenium.BaseTestComopents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import FrameWorkSelenium.resources.ExtentReporterNG;

public class TestNGListeners extends BaseTest  implements ITestListener{
	ExtentTest test;
	ExtentReports extent=ExtentReporterNG.getReportObject();
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	
	   public void onTestStart(ITestResult result) {
		test=extent.createTest(result.getMethod().getMethodName());//creates a test 
		extentTest.set(test);
	       // System.out.println("Test Started: " + result.getName());
	    }
	    
	    @Override
	    public void onTestSuccess(ITestResult result) {
	    	extentTest.get().log(Status.PASS, "Test Passed");
	       // System.out.println("Test Passed: " + result.getName());
	    }
	    
	    @Override
	    public void onTestFailure(ITestResult result) {
	    	extentTest.get().fail(result.getThrowable());
	    	
	    	try {
	    	driver=(WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
	    	}
	    	catch (Exception e1) {
	    		e1.printStackTrace();
	    	}
	    	String filepath=null;
	    	try {
				filepath=getScreenshot(result.getMethod().getMethodName(),driver);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	extentTest.get().addScreenCaptureFromPath(filepath, result.getMethod().getMethodName());
	      //  System.out.println("Test Failed: " + result.getName());
	    }
	    
	    @Override
	    public void onTestSkipped(ITestResult result) {
	        //System.out.println("Test Skipped: " + result.getName());
	    }
	    
	    @Override
	    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	    	
	    	
	    	
	       // System.out.println("Test failed but within success percentage: " + result.getName());
	    }
	    
	    @Override
	    public void onStart(ITestContext context) {
	       // System.out.println("Test Started: " + context.getName());
	    }
	    
	    @Override
	    public void onFinish(ITestContext context) {
	    	
	    	extent.flush();
	       // System.out.println("Test Finished: " + context.getName());
	    }
}
