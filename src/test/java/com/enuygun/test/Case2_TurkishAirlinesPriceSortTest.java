package com.enuygun.test;

import com.enuygun.base.HomePage;
import com.enuygun.base.FlightListPage;
import com.enuygun.config.ConfigFileReader;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Case2_TurkishAirlinesPriceSortTest extends com.enuygun.base.BaseTest {

    HomePage homePage;
    FlightListPage flightListPage;
    ConfigFileReader configFileReader = new ConfigFileReader();

    @BeforeMethod
    public void setUpPages() {
        homePage = new HomePage();
        flightListPage = new FlightListPage();
    }

    @Test(description = "Price Sorting for Turkish Airlines")
    public void priceSortingForTurkishAirlines() throws InterruptedException {

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
        flightListPage.clickAirlinesFilter();
        flightListPage.selectTurkishAirlines();
        flightListPage.verifyOnlyTurkishAirlines();
        flightListPage.verifyPricesAreSortedAscending();
    }
}
