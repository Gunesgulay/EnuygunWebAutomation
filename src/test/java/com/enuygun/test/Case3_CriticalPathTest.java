package com.enuygun.test;

import com.enuygun.base.HomePage;
import com.enuygun.base.FlightListPage;
import com.enuygun.config.ConfigFileReader;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Case3_CriticalPathTest extends com.enuygun.base.BaseTest {

    HomePage homePage;
    FlightListPage flightListPage;
    ConfigFileReader configFileReader = new ConfigFileReader();

    @BeforeMethod
    public void setUpPages() {
        homePage = new HomePage();
        flightListPage = new FlightListPage();
    }

    @Test(description = "Critical user journey: Search and select a flight")
    public void criticalUserJourneyTest() throws InterruptedException {

        String departureCity = configFileReader.getDepartureCity();
        String destinationCity = configFileReader.getDestinationCity();
        String departureDate = configFileReader.getDepartureDate();

        homePage.clickAcceptCookiesButton();
        homePage.enterDepartureCity(departureCity);
        homePage.selectIstanbulDepartureCityFromList();
        homePage.clickDestinationField();
        homePage.enterDestinationCity(destinationCity);
        homePage.selectAnkaraDestinationCityFromList();
        homePage.clickDepartureDateField();
        homePage.selectDepartureDate(departureDate);
        homePage.clickOneWayTripButton();
        homePage.clickSubmitButton();
        flightListPage.verifyDepartureDate(departureDate);
        flightListPage.verifyDepartureAndArrivalAirports();
        flightListPage.verifyFlightTicketsDisplayedCorrectly();
    }
}
