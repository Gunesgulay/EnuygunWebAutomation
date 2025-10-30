package com.enuygun.test;

import com.enuygun.base.HomePage;
import com.enuygun.base.FlightListPage;
import com.enuygun.config.ConfigFileReader;
import org.testng.annotations.Test;

public class Case1_BasicFlightSearchTest extends com.enuygun.base.BaseTest {

    HomePage homePage = new HomePage();
    FlightListPage flightListPage = new FlightListPage();
    ConfigFileReader configFileReader = new ConfigFileReader();

    @Test(description = "Basic Flight Search and Time Filter")
    public void basicFlightSearchAndTimeFilterTest() throws InterruptedException {

        String departureCity = configFileReader.getDepartureCity();
        String destinationCity = configFileReader.getDestinationCity();

        homePage.clickAcceptCookiesButton();
        homePage.clickRoundTripButton();
        homePage.enterDepartureCity(departureCity);
        homePage.selectIstanbulDepartureCityFromList();
        homePage.clickDestinationField();
        homePage.enterDestinationCity(destinationCity);
        homePage.selectAnkaraDestinationCityFromList();
        homePage.clickSubmitButton();
        flightListPage.clickDepartureArrivalTimeFilter();
        flightListPage.setDepartureTimeFilter();
        flightListPage.verifyDepartureTimesBetween10and18();
        flightListPage.verifyDepartureAndArrivalAirports();
        flightListPage.verifyFlightTicketsDisplayedCorrectly();
    }
}

