package com.enuygun.element;

import org.openqa.selenium.By;

public class FlightListPageElements {

    public static final By DEPARTURE_ARRIVAL_TIME_FILTER = By.xpath("//div[contains(@class,'ctx-filter-departure-return-time') and contains(@class,'card-header')]");
    public static final By SLIDER_LEFT_HANDLE = By.cssSelector(".rc-slider-handle-1");
    public static final By SLIDER_RIGHT_HANDLE = By.cssSelector(".rc-slider-handle-2");
    public static final By SLIDER_RAIL = By.cssSelector(".rc-slider-rail");
    public static final By FLIGHT_ITEM_DEPARTURE_TIME = By.xpath("//div[@data-testid='departureTime']");
    public static final By LOADER_TEXT = By.xpath("//span[contains(text(),'Uçuşlarınız hazırlanıyor')]");
    public static final By FLIGHT_ITEM_CARD = By.xpath("//div[contains(@class,'flight-item')]");
    public static final By AIRPORT_SUMMARY_STEPS = By.cssSelector("div[data-testid='airportSummarySteps']");
    public static final By ITEM_AIRPORT_SPAN = By.cssSelector("span.itemAirport");
    public static final By FLIGHT_SUMMARY_AIRLINE = By.xpath(".//*[contains(@class, 'flight-summary-airline')]");
    public static final By FLIGHT_INFO_PRICE = By.xpath("//div[@data-testid='flightInfoPrice']");
    public static final By AIRLINES_FILTER = By.cssSelector("div.ctx-filter-airline.card-header");
    public static final By TURKISH_AIRLINES_CHECKBOX = By.cssSelector("label.filter-label.search__filter_airlines-TK");
    public static final By AIRLINE_NAME_IN_CARD = By.xpath(".//div[contains(@class,'summary-marketing-airlines')]");
    public static final By AJET_AIRLINE_ELEMENT = By.xpath("//div[contains(@class,'summary-marketing-airlines')][contains(translate(text(), 'AJET', 'ajet'), 'ajet')]");
    public static final By FLIGHT_ARRIVAL_TIME = By.xpath("//div[@data-testid='arrivalTime']");
    public static final By DEPARTURE_DATE = By.xpath("//div[@data-testid='flight__list-departureDate']");
}

