package com.apptus.listener;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.apptus.base.TestBase;
import com.apptus.utils.TestUtil;

public class CustomListeners extends TestBase implements ITestListener {
	
	private TestUtil utils =  new TestUtil();;
	
	public void onTestStart(ITestResult result) {
		System.out.println("Test Method has Started ");
		utils.takeScreenShot(driver);
	}

	public void onTestSuccess(ITestResult result) {
		System.out.println("Test Success ");
		utils.takeScreenShot(driver);
	}

	public void onTestFailure(ITestResult result) {
		System.out.println("Test Failure ");
		utils.takeScreenShot(driver);
	}

	public void onTestSkipped(ITestResult result) {
		System.out.println("Test has Skipped ");
		utils.takeScreenShot(driver);
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	public void onStart(ITestContext context) {
		System.out.println("Test Case has Started ");
		utils.takeScreenShot(driver);
	}

	public void onFinish(ITestContext context) {
		System.out.println("Test Case has Finished ");
		utils.takeScreenShot(driver);
	}

}