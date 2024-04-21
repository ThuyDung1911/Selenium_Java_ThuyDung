package com.thuydung.listeners;

import com.aventstack.extentreports.Status;
import com.thuydung.helpers.CaptureHelper;
import com.thuydung.helpers.PropertiesHelper;
import com.thuydung.keywords.WebUI;
import com.thuydung.reports.ExtentReportManager;
import com.thuydung.reports.ExtentTestManager;
import com.thuydung.utils.JiraCreateIssue;
import com.thuydung.utils.JiraServiceProvider;
import com.thuydung.utils.LogUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestListener implements ITestListener {
    public String getTestName(ITestResult result) {
        return result.getTestName() != null ? result.getTestName()
                : result.getMethod().getConstructorOrMethod().getName();
    }

    public String getTestDescription(ITestResult result) {
        return result.getMethod().getDescription() != null ? result.getMethod().getDescription() : getTestName(result);
    }

    @Override
    public void onStart(ITestContext result) {
        PropertiesHelper.loadAllFiles();
        LogUtils.info("Start Testing " + result.getName());
        System.out.println("onStart: " + result.getStartDate());

    }

    @Override
    public void onFinish(ITestContext result) {
        LogUtils.info("End Testing " + result.getName());
        ExtentReportManager.getExtentReports().flush();
        WebUI.stopSoftAssertAll();
        System.out.println("onFinish: " + result.getEndDate());
    }

    @Override
    public void onTestStart(ITestResult result) {
        LogUtils.info(getTestName(result) + " is starting...");
        ExtentTestManager.saveToReport(getTestName(result), getTestDescription(result));
        if (PropertiesHelper.getValue("VIDEO_RECORD").equals("true")) {
            CaptureHelper.startRecord(result.getName());
        }
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        LogUtils.error("Test failed but it is in defined success ratio " + getTestName(result));
        ExtentTestManager.logMessage("Test failed but it is in defined success ratio " + getTestName(result));
//        AllureManager.saveTextLog("Test failed but it is in defined success ratio " + getTestName(result));
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LogUtils.info(result.getName() + " is passed");
        ExtentTestManager.logMessage(Status.PASS, getTestName(result) + " is passed");

        if (PropertiesHelper.getValue("SCREENSHOT_PASS").equals("true")) {
            CaptureHelper.takeScreenshot(result.getName());
        }

        if (PropertiesHelper.getValue("VIDEO_RECORD").equals("true")) {
            WebUI.sleep(1);
            CaptureHelper.stopRecord();
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {

        CaptureHelper.takeScreenshot(result);
        LogUtils.error(result.getName() + " is failed");

        if (PropertiesHelper.getValue("SCREENSHOT_FAIL").equals("true")) {
            CaptureHelper.takeScreenshot(result.getName());
        }

        if (PropertiesHelper.getValue("VIDEO_RECORD").equals("true")) {
            WebUI.sleep(1);
            CaptureHelper.stopRecord();
        }
        boolean isLogIssue = result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(JiraCreateIssue.class).isCreateIssue();
        if (isLogIssue) {
            JiraServiceProvider JiraServiceProvider = new JiraServiceProvider();
            String issueDescription = "Failure reason from Automation Selenium\n\n" + result.getThrowable().getMessage() + "\n";

            issueDescription.concat(ExceptionUtils.getFullStackTrace(result.getThrowable()));
            String issueSummary = result.getMethod().getConstructorOrMethod().getMethod().getName() + " Failed in Automation Selenium";
            Date now = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy-HH:mm:ss");
            String timestamp = formatter.format(now);
            JiraServiceProvider.createJiraIssue("Bug", issueSummary + " " + timestamp, issueDescription);
            JiraServiceProvider.addAttachmentToJiraIssue("Report/screenshots/" + result.getName() + ".png");

        }

        //Add Screenshot to ExtentReport
        ExtentTestManager.addScreenShot(Status.FAIL, "Evidence " + getTestName(result) + " failed");
        //In log error khi lỗi vào report
        ExtentTestManager.logMessage(Status.FAIL, result.getThrowable().toString());
        ExtentTestManager.logMessage(Status.FAIL, getTestName(result) + " is failed");

        //Allure report
        LogUtils.error("Screenshot captured for test case: " + getTestName(result));
//        AllureManager.saveTextLog(getTestName(result) + "is failed and screenshot taken!");
//        AllureManager.saveScreenshotPNG();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Đây là test case bị bỏ qua: " + result.getName());
        ExtentTestManager.logMessage(Status.SKIP, getTestDescription(result) + " is skipped");
//        AllureManager.saveTextLog(getTestName(result) + " is skipped");
        if (PropertiesHelper.getValue("VIDEO_RECORD").equals("true")) {
            WebUI.sleep(0.5);
            CaptureHelper.stopRecord();
        }
    }
}
