package com.enuygun.utils;

import com.enuygun.model.FlightData;
import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.style.Styler;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class FlightDataAnalyzer {

    /**
     * Calculates the minimum, maximum, and average prices by airline, prints them to the console, and creates a graph.
     */
    public static void analyzeAndPlotPriceByAirline(List<FlightData> flights, String chartPath) {
        // Group flights by airline
        Map<String, List<FlightData>> flightsByAirline = flights.stream()
                .collect(Collectors.groupingBy(FlightData::getAirline));

        // Data lists for the chart
        List<String> airlineNames = new ArrayList<>();
        List<Double> avgPrices = new ArrayList<>();
        List<Double> minPrices = new ArrayList<>();
        List<Double> maxPrices = new ArrayList<>();

        System.out.println("\n--- Price Analysis by Airline ---");

        // Calculate statistics for each airline
        for (Map.Entry<String, List<FlightData>> entry : flightsByAirline.entrySet()) {
            String airline = entry.getKey();
            List<FlightData> airlineFlights = entry.getValue();

            DoubleSummaryStatistics stats = airlineFlights.stream()
                    .mapToDouble(FlightData::getPrice)
                    .summaryStatistics();

            airlineNames.add(airline);
            avgPrices.add(stats.getAverage());
            minPrices.add(stats.getMin());
            maxPrices.add(stats.getMax());

            System.out.printf("Airline: %-20s | Number of Flights: %-5d | Min Price: %-10.2f | Avg. Price: %-10.2f | Max Price: %-10.2f%n",
                    airline, stats.getCount(), stats.getMin(), stats.getAverage(), stats.getMax());
        }

        // Create chart
        createBarChart(airlineNames, avgPrices, minPrices, maxPrices, chartPath);
    }

    private static void createBarChart(List<String> airlineNames, List<Double> avgPrices, List<Double> minPrices, List<Double> maxPrices, String chartPath) {
        CategoryChart chart = new CategoryChartBuilder()
                .width(1200)
                .height(800)
                .title("Price Statistics by Airline")
                .xAxisTitle("Airline")
                .yAxisTitle("Price (TRY)")
                .build();

        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNW);
        // Enables tooltips that show values on mouse hover.
        // Note: setHasAnnotations(true) is not used in this library version to show
        // fixed values directly on the bars.
        chart.getStyler().setToolTipsEnabled(true);

        chart.addSeries("Average Price", airlineNames, avgPrices);
        chart.addSeries("Minimum Price", airlineNames, minPrices);
        chart.addSeries("Maximum Price", airlineNames, maxPrices);

        try {
            BitmapEncoder.saveBitmap(chart, chartPath, BitmapEncoder.BitmapFormat.PNG);
            System.out.println("Price analysis chart created successfully: " + chartPath);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error occurred while creating the chart file.");
        }
    }

    /**
     * A simple algorithm to determine the best value flights.
     * Sorts by price and returns the top 3 flights with the lowest prices.
     */
    public static void findBestValueFlights(List<FlightData> flights) {
        // List sorted by price (from cheapest to most expensive)
        List<FlightData> sortedByPrice = flights.stream()
                .sorted(Comparator.comparingDouble(FlightData::getPrice))
                .collect(Collectors.toList());

        System.out.println("\n--- Best Value Flights (Top 3) ---");

        // Print the top 3 cheapest flights
        sortedByPrice.stream()
                .limit(3)
                .forEach(flight ->
                        System.out.printf("Airline: %-20s | Departure: %-8s | Transfer: %-10s | Price: %.2f TRY%n",
                                flight.getAirline(),
                                flight.getDepartureTime(),
                                flight.getTransferInfo(),
                                flight.getPrice())
                );
    }
}