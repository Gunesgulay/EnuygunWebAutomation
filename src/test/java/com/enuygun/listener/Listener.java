package com.enuygun.listener;

import com.enuygun.report.ExtentLogger;
import com.enuygun.report.ExtentReport;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import static com.enuygun.utils.ScreenshotUtils.screenShot;

public class Listener implements ITestListener, ISuiteListener {

    @Override
    public void onTestStart(ITestResult result) {
        ExtentReport.createTest(result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentLogger.pass(result.getName() + " is passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        screenShot("failScreenShot");
        ExtentLogger.fail(result.getName() + " is failed");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentLogger.skip(result.getName() + " is skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
    }

    @Override
    public void onStart(ITestContext context) {
        ExtentReport.initReports();
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentReport.tearDownReports();
    }
}