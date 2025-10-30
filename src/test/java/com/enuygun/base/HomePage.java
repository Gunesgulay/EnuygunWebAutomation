package com.enuygun.base;

import com.enuygun.log.Log;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.enuygun.element.HomePageElements.*;

public class HomePage extends BaseTest {

    Logger logger = LogManager.getLogger(Log.class.getName());

    public void clickRoundTripButton() {
        logger.info("Clicking the round-trip button.");
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement roundTripButton = wait.until(ExpectedConditions.elementToBeClickable(ROUND_TRIP_BUTTON));
            roundTripButton.click();
            logger.info("Round-trip button clicked.");
        } catch (Exception e) {
            logger.error("Round-trip button could not be found or clicked!", e);
            Assert.fail("Round-trip button could not be found or clicked!");
        }
    }

    public void clickOneWayTripButton() {
        logger.info("Clicking the one-way-trip button.");
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement oneWayTripButton = wait.until(ExpectedConditions.elementToBeClickable(ONE_WAY_TRIP_BUTTON));
            oneWayTripButton.click();
            logger.info("One-way-trip button clicked.");
        } catch (Exception e) {
            logger.error("One-way-trip button could not be found or clicked!", e);
            Assert.fail("One-way-trip button could not be found or clicked!");
        }
    }

    public void clickAcceptCookiesButton() {
        logger.info("Clicking the accept cookies button.");
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement acceptCookiesButton = wait.until(ExpectedConditions.elementToBeClickable(ACCEPT_COOKIES));
            acceptCookiesButton.click();
            logger.info("Accept cookies button clicked.");
        } catch (Exception e) {
            logger.error("Accept cookies button could not be found or clicked!", e);
            Assert.fail("Accept cookies button could not be found or clicked!");
        }
    }

    public void enterDepartureCity(String city) {
        logger.info("Entering departure city: " + city);
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement fromField = wait.until(ExpectedConditions.elementToBeClickable(ENTER_DEPARTURE_CITY));
            fromField.click();
            fromField.sendKeys(city);
            logger.info("Departure city entered.");
        } catch (Exception e) {
            logger.error("Could not enter departure city!", e);
            Assert.fail("Could not enter departure city!");
        }
    }

    public void selectIstanbulDepartureCityFromList() {
        logger.info("Selecting departure city from the list.");
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement suggestion = wait.until(ExpectedConditions.elementToBeClickable(SELECT_ISTANBUL_DEPARTURE));
            suggestion.click();
            logger.info("Departure city selected.");
        } catch (Exception e) {
            logger.error("Could not select departure city from list!", e);
            Assert.fail("Could not select departure city from list!");
        }
    }

    public void clickDestinationField() {
        logger.info("Clicking the destination field.");
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement toField = wait.until(ExpectedConditions.elementToBeClickable(ENTER_DESTINATION_CITY));
            toField.click();
            logger.info("Destination field clicked.");
        } catch (Exception e) {
            logger.error("Could not click destination field!", e);
            Assert.fail("Could not click destination field!");
        }
    }

    public void enterDestinationCity(String city) {
        logger.info("Entering destination city: " + city);
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement toField = wait.until(ExpectedConditions.visibilityOfElementLocated(ENTER_DESTINATION_CITY));
            toField.sendKeys(city);
            logger.info("Destination city entered.");
        } catch (Exception e) {
            logger.error("Could not enter destination city!", e);
            Assert.fail("Could not enter destination city!");
        }
    }

    public void selectAnkaraDestinationCityFromList() {
        logger.info("Selecting Ankara as the destination city.");
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement suggestion = wait.until(ExpectedConditions.elementToBeClickable(SELECT_ANKARA_DESTINATION));
            suggestion.click();
            logger.info("Ankara selected as the destination.");
        } catch (Exception e) {
            logger.error("Could not select Ankara from list!", e);
            Assert.fail("Could not select Ankara from list!");
        }
    }

    public void selectLefkosaDestinationCityFromList() {
        logger.info("Selecting Lefkoşa as the destination city.");
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement suggestion = wait.until(ExpectedConditions.elementToBeClickable(SELECT_LEFKOSA_DESTINATION));
            suggestion.click();
            logger.info("Lefkoşa selected as the destination.");
        } catch (Exception e) {
            logger.error("Could not select Lefkoşa from list!", e);
            Assert.fail("Could not select Lefkoşa from list!");
        }
    }

    public void clickSubmitButton() {
        logger.info("Clicking the submit button.");
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(SUBMIT_BUTTON));
            submitButton.click();
            logger.info("Submit button clicked.");
        } catch (Exception e) {
            logger.error("Could not click submit button!", e);
            Assert.fail("Could not click submit button!");
        }
    }

    public void clickDepartureDateField() {
        logger.info("Clicking the departure date field.");
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement departureDate = wait.until(ExpectedConditions.elementToBeClickable(CLICK_DEPARTURE_DATE));
            departureDate.click();
            logger.info("Departure date field clicked.");
        } catch (Exception e) {
            logger.error("Could not click departure date field!", e);
            Assert.fail("Could not click departure date field!");
        }
    }

    public void selectDepartureDate(String targetDate) {
        logger.info("Selecting departure date: " + targetDate);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            for (int i = 0; i < 12; i++) {
                String dayXPath = String.format("//button[@title='%s' and not(@disabled)]", targetDate);
                List<WebElement> matchingDays = driver.findElements(By.xpath(dayXPath));

                if (!matchingDays.isEmpty()) {
                    logger.info("Found the target date element: " + targetDate);
                    WebElement dayButton = matchingDays.get(0);
                    wait.until(ExpectedConditions.elementToBeClickable(dayButton));
                    dayButton.click();
                    logger.info("Successfully clicked the departure date: " + targetDate);
                    return;
                }

                logger.info("Target date not visible in current calendar view. Clicking 'next month'.");
                WebElement nextMonthButton = wait.until(ExpectedConditions.elementToBeClickable(MONTH_FORWARD_BUTTON));
                nextMonthButton.click();
                TimeUnit.MILLISECONDS.sleep(500);
            }

            Assert.fail("Could not find the specified date (" + targetDate + ") within 12 months.");
        } catch (Exception e) {
            logger.error("An error occurred while selecting the departure date.", e);
            Assert.fail("Failed to select departure date: " + e.getMessage());
        }
    }
}
