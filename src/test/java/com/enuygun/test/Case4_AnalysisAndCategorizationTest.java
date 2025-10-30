package com.enuygun.test;

import com.enuygun.base.HomePage;
import com.enuygun.base.FlightListPage;
import com.enuygun.log.Log;
import com.enuygun.model.FlightData;
import com.enuygun.utils.CsvWriterUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class Case4_AnalysisAndCategorizationTest extends com.enuygun.base.BaseTest {

    HomePage homePage;
    FlightListPage flightListPage;
    Logger logger = LogManager.getLogger(Log.class.getName());

    @BeforeMethod
    public void setUpPages() {
        homePage = new HomePage();
        flightListPage = new FlightListPage();
    }

    @Test(description = "Scrape, Analyze and Categorize Flight Data")
    public void flightDataAnalysisTest() {

        String departureCity = "İstanbul";
        String destinationCity = "Lefkoşa";
        String csvFilePath = "target/flight_data_" + System.currentTimeMillis() + ".csv";

        homePage.clickAcceptCookiesButton();
        homePage.clickRoundTripButton();
        homePage.enterDepartureCity(departureCity);
        homePage.selectIstanbulDepartureCityFromList();
        homePage.clickDestinationField();
        homePage.enterDestinationCity(destinationCity);
        homePage.selectLefkosaDestinationCityFromList();
        homePage.clickSubmitButton();

        List<FlightData> flightData = flightListPage.scrapeAllFlightData();

        logger.info("Number of flights scraped: " + flightData.size());
        Assert.assertFalse(flightData.isEmpty(), "Scraping returned no flight data! The list is empty.");

        CsvWriterUtil.writeFlightsToCsv(flightData, csvFilePath);
    }
}
