package Utilities;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import TestBase.baseclass;

public class ExtentReportManager implements ITestListener {

	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;

	String reportname;

	public void extentreports(ITestContext context) {

		String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		reportname = "Test-report" + timestamp + ".html";

		sparkReporter = new ExtentSparkReporter(".\\reports\\" + reportname);
		sparkReporter.config().setDocumentTitle("automated");
		sparkReporter.config().setReportName("functionaltesting");
		sparkReporter.config().setTheme(Theme.STANDARD);

		extent = new ExtentReports();

		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("operatingsystem", System.getProperty("os.name"));
		extent.setSystemInfo("username", System.getProperty("user.name"));
	}

	public void TestSuccess(ITestResult result) {
		test = extent.createTest(result.getName());
		test.log(Status.PASS, "passed");

	}

	public void Testfail(ITestResult result) {
		test = extent.createTest(result.getName());
		test.log(Status.FAIL, "failed");
		test.log(Status.FAIL, result.getThrowable().getMessage());

		try {
			String imgpath = new baseclass().ss(result.getName());
			test.addScreenCaptureFromPath(imgpath);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		{

		}

	}

	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getName());
		test.log(Status.SKIP, "Test Skipped");
		test.log(Status.SKIP, result.getThrowable().getMessage());
	}

	public void TestFinish(ITestContext Context) {
		extent.flush();

	}

}
