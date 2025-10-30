package com.enuygun.base;

import com.enuygun.driver.DriverManager;
import org.testng.annotations.*;
import java.net.MalformedURLException;

public class BaseTest extends DriverManager {

    @Parameters("browser")
    @BeforeMethod(groups = {"hook"})
    public void before(@Optional("browser") String browser) throws MalformedURLException {
        setDriver(browser);
    }

    @AfterMethod(groups = {"hook"})
    public void after() {
        if (driver != null) {
            driver.quit();
        }
    }
}