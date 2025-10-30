package com.enuygun.base;

import com.enuygun.log.Log;
import com.enuygun.model.FlightData;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.enuygun.element.FlightListPageElements.*;

public class FlightListPage extends BaseTest {

    Logger logger = LogManager.getLogger(Log.class.getName());

    public void clickDepartureArrivalTimeFilter() {
        try {
            logger.info("Entering clickDepartureArrivalTimeFilter method.");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            WebElement filterButton = wait.until(ExpectedConditions.elementToBeClickable(DEPARTURE_ARRIVAL_TIME_FILTER));
            filterButton.click();
            logger.info("Clicked the 'Departure and Arrival Time' filter section.");
            logger.info("Exiting clickDepartureArrivalTimeFilter method.");
        } catch (Exception e) {
            logger.error("Error in clickDepartureArrivalTimeFilter: " + e.getMessage(), e);
            Assert.fail("Failed during clickDepartureArrivalTimeFilter method.");
        }
    }

    public void setDepartureTimeFilter() {
        try {
            logger.info("Entering setDepartureTimeFilter method.");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            WebElement leftHandle = wait.until(ExpectedConditions.visibilityOfElementLocated(SLIDER_LEFT_HANDLE));
            WebElement rightHandle = wait.until(ExpectedConditions.visibilityOfElementLocated(SLIDER_RIGHT_HANDLE));
            WebElement sliderRail = driver.findElement(SLIDER_RAIL);

            int railWidth = sliderRail.getSize().getWidth();
            double pxPerHour = railWidth / 24.0;

            int leftOffset = (int) (pxPerHour * 10);
            int rightOffset = (int) (pxPerHour * 6);

            Actions actions = new Actions(driver);

            logger.info("Moving left slider handle.");
            actions.clickAndHold(leftHandle).moveByOffset(leftOffset, 0).release().perform();

            Thread.sleep(500);

            logger.info("Moving right slider handle.");
            actions.clickAndHold(rightHandle).moveByOffset(-rightOffset, 0).release().perform();
            logger.info("Departure time filter set between 10:00 and 18:00.");
            logger.info("Exiting setDepartureTimeFilter method.");
        } catch (Exception e) {
            logger.error("Error in setDepartureTimeFilter: " + e.getMessage(), e);
            Assert.fail("Failed during setDepartureTimeFilter method.");
        }
    }

    public void wait(int seconds) throws InterruptedException {
        TimeUnit.SECONDS.sleep(seconds);
    }

    public void verifyDepartureTimesBetween10and18() {
        try {
            logger.info("Entering verifyDepartureTimesBetween10and18 method.");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

            final By ITEM_LOCATOR_INSIDE = FLIGHT_ITEM_DEPARTURE_TIME;
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(FLIGHT_ITEM_DEPARTURE_TIME));
            wait.until(ExpectedConditions.visibilityOfElementLocated(FLIGHT_ITEM_DEPARTURE_TIME));
            wait(4);

            LocalTime startTime = LocalTime.of(10, 0);
            LocalTime endTime = LocalTime.of(18, 0);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

            List<WebElement> flightItems = driver.findElements(ITEM_LOCATOR_INSIDE);
            Assert.assertTrue(flightItems.size() > 0, "No flight cards found after filtering!");
            logger.info(flightItems.size() + " flight items found.");

            for (WebElement flightItem : flightItems) {
                String departureTimeString = flightItem.getText().trim();
                LocalTime departureTime = LocalTime.parse(departureTimeString, formatter);

                Assert.assertTrue((!departureTime.isBefore(startTime)) && (!departureTime.isAfter(endTime)),
                        "Flight departure time is not between 10:00 and 18:00 (inclusive): " + departureTimeString);
                logger.debug("Verified departure time: " + departureTimeString + " is between " + startTime + " and " + endTime + " (inclusive).");
            }
            logger.info("All flight departure times are between 10:00 and 18:00 (inclusive).");
            logger.info("Exiting verifyDepartureTimesBetween10and18 method.");
        } catch (Exception e) {
            logger.error("Error in verifyDepartureTimesBetween10and18: " + e.getMessage(), e);
            Assert.fail("Failed during verifyDepartureTimesBetween10and18 method.");
        }
    }

    public void verifyDepartureAndArrivalAirports() {
        logger.info("Entering verifyDepartureAndArrivalAirports method.");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        final By LOADER_LOCATOR = LOADER_TEXT;
        try {
            logger.info("Waiting for loader to disappear.");
            wait.until(ExpectedConditions.invisibilityOfElementLocated(LOADER_LOCATOR));
            logger.info("Loader disappeared.");
        } catch (Exception ignored) {
            logger.warn("Loader did not disappear within timeout or was not present.");
        }

        final By ANY_FLIGHT_ITEM_LOCATOR = FLIGHT_ITEM_CARD;

        for (int attempt = 0; attempt < 3; attempt++) {
            try {
                logger.info("Attempt " + (attempt + 1) + " to verify departure and arrival airports.");
                wait.until(ExpectedConditions.visibilityOfElementLocated(ANY_FLIGHT_ITEM_LOCATOR));
                logger.info("Flight items are visible.");

                List<WebElement> flightCards = driver.findElements(ANY_FLIGHT_ITEM_LOCATOR);
                Assert.assertFalse(flightCards.isEmpty(), "Flight list not found or empty.");
                logger.info(flightCards.size() + " flight cards found.");

                for (WebElement card : flightCards) {
                    WebElement routeElement = card.findElement(AIRPORT_SUMMARY_STEPS);
                    List<WebElement> airportCodes = routeElement.findElements(ITEM_AIRPORT_SPAN);
                    Assert.assertTrue(airportCodes.size() >= 2, "Not enough airport codes found on a flight card.");

                    String departureAirport = airportCodes.get(0).getText().trim().toUpperCase();
                    String arrivalAirport = airportCodes.get(airportCodes.size() - 1).getText().trim().toUpperCase();

                    boolean isDepartureCorrect = departureAirport.equals("IST") || departureAirport.equals("SAW");
                    boolean isArrivalCorrect = arrivalAirport.equals("ESB");

                    Assert.assertTrue(isDepartureCorrect && isArrivalCorrect,
                            "Incorrect flight route found! Found: " + departureAirport + " -> " + arrivalAirport);
                    logger.debug("Verified flight route: " + departureAirport + " -> " + arrivalAirport);
                }

                logger.info("Verification Successful: All listed " + flightCards.size() + " flights match the expected route.");
                logger.info("Exiting verifyDepartureAndArrivalAirports method.");
                return;

            } catch (StaleElementReferenceException e) {
                logger.warn("StaleElement exception caught. Retrying... (Attempt: " + (attempt + 1) + ")");
                try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
                if (attempt == 2) {
                    Assert.fail("StaleElement exception could not be resolved after 3 attempts. There is a serious problem with page loading.");
                }
            } catch (TimeoutException e) {
                logger.warn("Timeout exception caught. Retrying... (Attempt: " + (attempt + 1) + ")");
                if (attempt == 2) {
                    Assert.fail("Elements could not be loaded after 3 attempts. Terminating test.");
                }
            } catch (Exception e) {
                logger.error("Unexpected error: " + e.getMessage(), e);
                Assert.fail("Unexpected error: " + e.getMessage());
                return;
            }
        }
    }

    public void verifyFlightTicketsDisplayedCorrectly() {
        try {
            logger.info("Entering verifyFlightTicketsDisplayedCorrectly method.");
            final By FLIGHT_LIST_ITEM_LOCATOR = FLIGHT_ITEM_CARD;
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            JavascriptExecutor js = (JavascriptExecutor) driver;

            logger.info("Waiting for flight list to load.");
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(FLIGHT_LIST_ITEM_LOCATOR));
            logger.info("Flight list loaded.");

            List<WebElement> flightItems = driver.findElements(FLIGHT_LIST_ITEM_LOCATOR);
            Assert.assertFalse(flightItems.isEmpty(), "Flight list is empty. No tickets could be displayed.");
            logger.info(flightItems.size() + " flight tickets found.");

            int checkedVisibleCount = 0;
            for (WebElement flightItem : flightItems) {
                boolean isVisible = (Boolean) js.executeScript(
                        "var rect = arguments[0].getBoundingClientRect();" +
                                "return (rect.top >= 0 && rect.bottom <= (window.innerHeight || document.documentElement.clientHeight));",
                        flightItem);

                if (isVisible) {
                    checkedVisibleCount++;
                    logger.debug("Checking visible flight item.");

                    boolean airlineInfoPresent = flightItem.findElements(FLIGHT_SUMMARY_AIRLINE).size() > 0;
                    Assert.assertTrue(airlineInfoPresent, "Airline information not found for the visible flight.");
                    logger.debug("Airline info present.");

                    boolean routeInfoPresent = flightItem.findElements(AIRPORT_SUMMARY_STEPS).size() > 0;
                    Assert.assertTrue(routeInfoPresent, "Route information not found for the visible flight.");
                    logger.debug("Route info present.");

                    boolean departureTimePresent = flightItem.findElements(FLIGHT_ITEM_DEPARTURE_TIME).size() > 0;
                    Assert.assertTrue(departureTimePresent, "Departure time information not found for the visible flight.");
                    logger.debug("Departure time present.");

                    boolean pricePresent = flightItem.findElements(FLIGHT_INFO_PRICE).size() > 0;
                    Assert.assertTrue(pricePresent, "Price information not found for the visible flight.");
                    logger.debug("Price info present.");

                    logger.info("First visible flight ticket on the screen verified successfully (Check Count: 1).");
                    break;
                }
            }

            Assert.assertTrue(checkedVisibleCount > 0, "No visible flight tickets found on the screen.");
            logger.info("Flight list loaded and at least one ticket is displayed correctly with its components.");
            logger.info("Exiting verifyFlightTicketsDisplayedCorrectly method.");
        } catch (Exception e) {
            logger.error("Error in verifyFlightTicketsDisplayedCorrectly: " + e.getMessage(), e);
            Assert.fail("Failed during verifyFlightTicketsDisplayedCorrectly method.");
        }
    }

    public void clickAirlinesFilter() {
        try {
            logger.info("Entering clickAirlinesFilter method.");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement airlinesFilter = wait.until(ExpectedConditions.elementToBeClickable(AIRLINES_FILTER));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", airlinesFilter);
            airlinesFilter.click();
            logger.info("Clicked the Airlines filter section.");
            logger.info("Exiting clickAirlinesFilter method.");
        } catch (Exception e) {
            logger.error("Error in clickAirlinesFilter: " + e.getMessage(), e);
            Assert.fail("Failed during clickAirlinesFilter method.");
        }
    }

    public void selectTurkishAirlines() {
        try {
            logger.info("Entering selectTurkishAirlines method.");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement thyCheckbox = wait.until(ExpectedConditions.elementToBeClickable(TURKISH_AIRLINES_CHECKBOX));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", thyCheckbox);
            thyCheckbox.click();
            logger.info("Selected Turkish Airlines flights.");
            logger.info("Exiting selectTurkishAirlines method.");
        } catch (Exception e) {
            logger.error("Error in selectTurkishAirlines: " + e.getMessage(), e);
            Assert.fail("Failed during selectTurkishAirlines method.");
        }
    }

    public void verifyOnlyTurkishAirlines() {
        try {
            logger.info("Entering verifyOnlyTurkishAirlines method.");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

            final By AJET_ELEMENT_LOCATOR = AJET_AIRLINE_ELEMENT;
            final By FLIGHT_ITEM_LOCATOR = FLIGHT_ITEM_CARD;
            final By AIRLINE_NAME_LOCATOR = AIRLINE_NAME_IN_CARD;

            try {
                logger.info("Waiting for AJET element to disappear.");
                wait.until(ExpectedConditions.invisibilityOfElementLocated(AJET_ELEMENT_LOCATOR));
                logger.info("AJET element disappeared.");
            } catch (TimeoutException ignored) {
                logger.warn("AJET element did not disappear within timeout or was not present.");
            }

            logger.info("Waiting for flight items to be visible.");
            wait.until(ExpectedConditions.visibilityOfElementLocated(FLIGHT_ITEM_LOCATOR));
            List<WebElement> flightCards = driver.findElements(FLIGHT_ITEM_LOCATOR);
            logger.info("Flight items are visible. Found " + flightCards.size() + " flight cards.");

            Assert.assertTrue(flightCards.size() > 0, "Flight list is empty!");

            for (WebElement card : flightCards) {
                String airlineName = "";
                try {
                    WebElement airlineElement = card.findElement(AIRLINE_NAME_LOCATOR);
                    airlineName = airlineElement.getText().trim().toLowerCase();
                } catch (NoSuchElementException ignored) {
                    logger.warn("Airline name element not found for a flight card. Skipping.");
                    continue;
                }

                if (airlineName.isEmpty()) {
                    logger.warn("Airline name is empty for a flight card. Skipping.");
                    continue;
                }

                if (!airlineName.contains("Türk Hava Yolları") && !airlineName.contains("türk hava yolları")) {
                    Assert.fail("THY filter failed! A non-THY flight was found: " + airlineName);
                }
                logger.debug("Verified airline: " + airlineName);
            }

            logger.info("All listed flights belong to Turkish Airlines. (Total " + flightCards.size() + " flights checked)");
            logger.info("Exiting verifyOnlyTurkishAirlines method.");
        } catch (Exception e) {
            logger.error("Error in verifyOnlyTurkishAirlines: " + e.getMessage(), e);
            Assert.fail("Failed during verifyOnlyTurkishAirlines method.");
        }
    }

    public void verifyPricesAreSortedAscending() {
        try {
            logger.info("Entering verifyPricesAreSortedAscending method.");
            List<WebElement> priceElements = driver.findElements(FLIGHT_INFO_PRICE);

            Assert.assertTrue(priceElements.size() > 1, "Not enough prices found!");
            logger.info(priceElements.size() + " price elements found.");

            List<Double> prices = new ArrayList<>();
            for (WebElement priceElement : priceElements) {
                String priceValue = priceElement.getAttribute("data-price").replace(",", ".");
                try {
                    prices.add(Double.parseDouble(priceValue));
                } catch (NumberFormatException e) {
                    logger.error("Incorrect price format: " + priceValue, e);
                    Assert.fail("Incorrect price format: " + priceValue);
                }
            }

            for (int i = 0; i < prices.size() - 1; i++) {
                Assert.assertTrue(prices.get(i) <= prices.get(i + 1),
                        "Price sorting is incorrect: " + prices.get(i) + " > " + prices.get(i + 1));
                logger.debug("Verified price order: " + prices.get(i) + " <= " + prices.get(i + 1));
            }

            logger.info("Prices are sorted in ascending order.");
            logger.info("Exiting verifyPricesAreSortedAscending method.");
        } catch (Exception e) {
            logger.error("Error in verifyPricesAreSortedAscending: " + e.getMessage(), e);
            Assert.fail("Failed during verifyPricesAreSortedAscending method.");
        }
    }

    public List<FlightData> scrapeAllFlightData() {
        logger.info("Starting to scrape all flight data from the results page.");
        List<FlightData> flightDataList = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

        try {
            List<WebElement> flightCards = driver.findElements(FLIGHT_ITEM_CARD);
            logger.info("Total number of flight cards found without scrolling: " + flightCards.size());

            for (WebElement card : flightCards) {
                try {
                    String airline = card.findElement(AIRLINE_NAME_IN_CARD).getText().trim();
                    LocalTime departureTime = LocalTime.parse(card.findElement(FLIGHT_ITEM_DEPARTURE_TIME).getText().trim(), formatter);
                    LocalTime arrivalTime = LocalTime.parse(card.findElement(FLIGHT_ARRIVAL_TIME).getText().trim(), formatter);
                    double price = Double.parseDouble(card.findElement(FLIGHT_INFO_PRICE).getAttribute("data-price").replace(",", "."));

                    List<WebElement> durationElements = card.findElements(By.xpath(".//span[@data-testid='departureFlightTime']"));
                    String duration = !durationElements.isEmpty() ? durationElements.get(0).getText().trim() : "N/A";

                    List<WebElement> transferElements = card.findElements(By.xpath(".//div[@data-testid='transferStateTransfer']"));
                    String transferInfo = !transferElements.isEmpty() ? transferElements.get(0).getText().trim() : "Direct";

                    flightDataList.add(new FlightData(airline, departureTime, arrivalTime, duration, transferInfo, price));

                } catch (NoSuchElementException | NumberFormatException e) {
                    logger.warn("Could not parse data for one of the flight cards, skipping it. Error: " + e.getMessage());
                }
            }

        } catch (Exception e) {
            logger.error("A critical error occurred during the scraping process: " + e.getMessage(), e);
            Assert.fail("Failed to scrape flight data due to a critical error.");
        }

        logger.info("Successfully scraped " + flightDataList.size() + " flight data entries.");
        return flightDataList;
    }

    public void verifyDepartureDate(String expectedDate) {
        logger.info("Verifying the departure date on the flight list page for: " + expectedDate);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        try {
            WebElement dateDisplayElement = wait.until(ExpectedConditions.visibilityOfElementLocated(DEPARTURE_DATE));

            String displayedDateAttribute = dateDisplayElement.getAttribute("data-date");
            logger.info("Found displayed date attribute: " + displayedDateAttribute);

            String displayedDate = displayedDateAttribute.split(" ")[0];

            logger.info("Comparing displayed date '" + displayedDate + "' with expected date '" + expectedDate + "'.");
            Assert.assertEquals(displayedDate, expectedDate, "The displayed departure date does not match the selected date.");
            logger.info("Successfully verified that the displayed departure date is correct.");

        } catch (Exception e) {
            logger.error("An error occurred while verifying the departure date.", e);
            Assert.fail("Failed to verify the departure date on the flight list page: " + e.getMessage());
        }
    }
}
