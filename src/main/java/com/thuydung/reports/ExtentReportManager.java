package com.thuydung.reports;

import com.thuydung.helpers.PropertiesHelper;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {

    private static final ExtentReports extentReports = new ExtentReports();

    public synchronized static ExtentReports getExtentReports() {
        ExtentSparkReporter reporter = new ExtentSparkReporter(PropertiesHelper.getValue("EXTENT_REPORT_PATH"));
        reporter.config().setReportName("Extent Report | " + PropertiesHelper.getValue("AUTHOR"));
        extentReports.attachReporter(reporter);
//        extentReports.setSystemInfo("Framework Name", "Test Automation Selenium Java");
//        extentReports.setSystemInfo("Author", PropertiesHelper.getValue("AUTHOR"));
        return extentReports;
    }

}