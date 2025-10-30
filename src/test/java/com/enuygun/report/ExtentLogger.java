package com.enuygun.report;

import static com.aventstack.extentreports.MediaEntityBuilder.createScreenCaptureFromBase64String;
import static com.enuygun.utils.ScreenshotUtils.getBase64Image;

public class ExtentLogger {

    private ExtentLogger() {
    }

    public static void pass(String message) {
        ExtentManager.getExtentTest().pass(message);
    }

    public static void fail(String message) {
        ExtentManager.getExtentTest().fail(message, createScreenCaptureFromBase64String(getBase64Image()).build());
    }

    public static void skip(String message) {
        ExtentManager.getExtentTest().skip(message);
    }

    public static void info(String message) {
        ExtentManager.getExtentTest().info(message);
    }

    public static void infoScreen(String message) {
        ExtentManager.getExtentTest().info(message, createScreenCaptureFromBase64String(getBase64Image()).build());
    }
}