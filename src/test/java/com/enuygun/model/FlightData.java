package com.enuygun.model;

import java.time.LocalTime;

public class FlightData {
    private String airline;
    private LocalTime departureTime;
    private LocalTime arrivalTime;
    private String duration;
    private String transferInfo;
    private double price;

    public FlightData(String airline, LocalTime departureTime, LocalTime arrivalTime, String duration, String transferInfo, double price) {
        this.airline = airline;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.duration = duration;
        this.transferInfo = transferInfo;
        this.price = price;
    }

    public String getAirline() {
        return airline;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public String getDuration() {
        return duration;
    }

    public String getTransferInfo() {
        return transferInfo;
    }

    public double getPrice() {
        return price;
    }

    public String[] toCsvRow() {
        return new String[]{
                airline,
                departureTime.toString(),
                arrivalTime.toString(),
                duration,
                transferInfo,
                String.valueOf(price)
        };
    }

    public static String[] getCsvHeader() {
        return new String[]{"Airline", "DepartureTime", "ArrivalTime", "Duration", "TransferInfo", "Price"};
    }
}