package Utility;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.annotations.ITestAnnotation;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;
import java.io.IOException;
//import com.aventstack.extentreports.reporter.ExtentReporter;
import java.text.*;
import java.util.Date;


public class Listeners extends TestListenerAdapter{
	
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	
	public void onStart(ITestContext context) {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss.SSS").format(new Date());
		String repName = "Test-Output"+timeStamp+".html";
		 htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"/Reports/"+repName);
		 htmlReporter.config().setDocumentTitle("Automation report");
		 htmlReporter.config().setReportName("Selenium reports");
		 htmlReporter.config().setTheme(Theme.DARK);
		 
		 extent = new ExtentReports();
		 extent.attachReporter(htmlReporter);
		 extent.setSystemInfo("Environment", "QA");
		 extent.setSystemInfo("user", "Ragavendran V");
		 
		
	}
	
	public void onTestSuccess(ITestResult result) {
		test=extent.createTest(result.getName());
		System.out.println(result.getName());
		test.log(Status.PASS, MarkupHelper.createLabel(result.getName(), ExtentColor.GREEN));
		
	}
	
	public void onTestFailure(ITestResult result) {
	test=extent.createTest(result.getName());
	test.log(Status.FAIL, MarkupHelper.createLabel(result.getName(), ExtentColor.RED));
	test.log(Status.FAIL, result.getThrowable());
	String screenShotPath = System.getProperty("user.dir") + "\\ScreenShots\\" + result.getName() + ".png";
	File f = new File(screenShotPath);
	if (f.exists()) {
		try {
			test.fail("Screenshot is below:" + test.addScreenCaptureFromPath(screenShotPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
	}

public void onTestSkipped(ITestResult result) {
	test=extent.createTest(result.getName());
	test.log(Status.SKIP, MarkupHelper.createLabel(result.getName(), ExtentColor.BROWN));
	
}

public void onFinish(ITestContext context) {
	extent.flush();
}
	


}
