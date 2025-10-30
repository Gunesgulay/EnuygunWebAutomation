package com.enuygun.element;

import org.openqa.selenium.By;

public class HomePageElements {

    public static final By ACCEPT_COOKIES = By.id("onetrust-accept-btn-handler");
    public static final By ROUND_TRIP_BUTTON = By.xpath("//label[@data-testid='search-round-trip-label']");
    public static final By ONE_WAY_TRIP_BUTTON = By.cssSelector("div[data-testid='search-one-way-text']");
    public static final By ENTER_DEPARTURE_CITY = By.cssSelector("label[data-testid='flight-origin-input-comp']");
    public static final By SELECT_ISTANBUL_DEPARTURE = By.xpath("//*[@data-testid='autosuggestion-custom-item-istanbul']");
    public static final By ENTER_DESTINATION_CITY = By.cssSelector("[data-testid='flight-destination-input-comp']");
    public static final By SELECT_ANKARA_DESTINATION = By.cssSelector("div[data-testid='autosuggestion-custom-item-ankara-esenboga-havalimani']");
    public static final By SELECT_LEFKOSA_DESTINATION = By.cssSelector("div[data-testid='autosuggestion-custom-item-ecn-ercan-intl-havalimani-city-country']");
    public static final By SUBMIT_BUTTON = By.cssSelector("[data-testid='enuygun-homepage-flight-submitButton']");
    public static final By CLICK_DEPARTURE_DATE = By.xpath("//label[@data-testid='enuygun-homepage-flight-departureDate-input-comp']");
    public static final By MONTH_FORWARD_BUTTON = By.xpath("//button[@data-testid='enuygun-homepage-flight-departureDate-month-forward-button']");
}
