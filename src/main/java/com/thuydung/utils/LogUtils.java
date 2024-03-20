package com.thuydung.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;

import java.io.File;

public class LogUtils {
    //Initialize Log4j instance
    private static final Logger Log = LogManager.getLogger(LogUtils.class);

    public static synchronized void startTestCase(String sTestCaseName) {
        sTestCaseName = sTestCaseName.replaceAll("[^a-zA-Z0-9]", "_").replaceAll("_+", "_");
        startLog(System.getProperty("user.dir"), sTestCaseName);
        info("\n\n************** Execution Started : " + sTestCaseName + "**************\n");
    }

    public static void endTestCase(String sTestCaseName) {
        info("\n\n************** Execution End : " + sTestCaseName + "**************\n");
    }

    private static void startLog(String dirPath, String testCaseName) {

        int noOfFiles = 0;

        File dir = new File(dirPath);
        if (dir.exists()) {
            int count = 0;
            for (File file : dir.listFiles()) {
                if (file.isFile() && file.getName().endsWith(".log") && file.getName().contains(testCaseName)) {
                    count++;
                }
            }
            noOfFiles = count;
        }

        noOfFiles++;
        String logFileName = testCaseName + "_" + noOfFiles;

        ThreadContext.put("logFilename", logFileName);
    }

    public static Logger getCurrentLog() {
        return Log;
    }

    public static String getCallInfo() {

        String callInfo;
        String className = Thread.currentThread().getStackTrace()[3].getClassName();
        String methodName = Thread.currentThread().getStackTrace()[3].getMethodName();
        int lineNumber = Thread.currentThread().getStackTrace()[3].getLineNumber();

        callInfo = className + ":" + methodName + " " + lineNumber + " ==> ";
        return callInfo;

    }

    public static void trace(Object message) {
        getCurrentLog().trace(message);
    }

    public static void trace(Object message, Throwable t) {
        getCurrentLog().trace(message, t);
    }

    //Info Level Logs
    public static void info(String message) {
        Log.info(message);
    }

    public static void info(Object object) {
        Log.info(object);
    }

    //Warn Level Logs
    public static void warn(String message) {
        Log.warn(message);
    }

    public static void warn(Object object) {
        Log.warn(object);
    }

    //Error Level Logs
    public static void error(String message) {
        Log.error(message);
    }

    public static void error(Object object) {
        Log.error(object);
    }

    //Fatal Level Logs
    public static void fatal(String message) {
        Log.fatal(message);
    }

    //Debug Level Logs
    public static void debug(String message) {
        Log.debug(message);
    }

    public static void debug(Object object) {
        Log.debug(object);
    }
}
