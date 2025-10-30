package com.enuygun.report;

import com.aventstack.extentreports.ExtentTest;

public class ExtentManager {

    private ExtentManager() {
    }

    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    public static ExtentTest getExtentTest() {
        return test.get();
    }

    public static void setExtentTest(ExtentTest extentTest) {
        test.set(extentTest);
    }

    public static void unload() {
        test.remove();
    }
}