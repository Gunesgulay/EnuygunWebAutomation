package com.enuygun.driver;

import com.enuygun.config.ConfigFileReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.time.Duration;

public class DriverManager {

    public static WebDriver driver;
    public static WebDriverWait webDriverWait;
    ConfigFileReader configFileReader = new ConfigFileReader();

    public static WebDriver getDriver() {
        return driver;
    }

    public void setDriver(String testBrowser) throws MalformedURLException {
        switch (testBrowser) {
            case "firefox": {
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("start-maximized");
                firefoxOptions.addArguments("--disable-notifications");
                driver = new FirefoxDriver(firefoxOptions);
                break;
            }
            default: {
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--remote-allow-origins=*");
                chromeOptions.addArguments("start-maximized");
                chromeOptions.addArguments("--disable-notifications");
                driver = new ChromeDriver(chromeOptions);
                break;
            }
        }
        driver.manage().deleteAllCookies();
        driver.navigate().to(configFileReader.getBaseUrl());
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(configFileReader.getImplicitlyWait()));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(configFileReader.getPageLoadTimeout()));
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(configFileReader.getWebDriverWait()));
    }
}