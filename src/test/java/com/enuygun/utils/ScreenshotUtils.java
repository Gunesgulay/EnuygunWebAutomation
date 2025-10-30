package com.enuygun.utils;

import com.enuygun.driver.DriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.enuygun.driver.DriverManager.driver;

public class ScreenshotUtils {

    private ScreenshotUtils() {
    }

    public static String getBase64Image() {
        return ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BASE64);
    }

    public static void screenShot(String text) {
        DateFormat dateFormat;
        dateFormat = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");
        String date = dateFormat.format(new Date());
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir") + "/report/screenshot/" + text + "(" + date + ")" + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}