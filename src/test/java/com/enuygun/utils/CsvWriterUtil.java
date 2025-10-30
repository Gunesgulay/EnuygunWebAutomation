package com.enuygun.utils;

import com.enuygun.model.FlightData;
import com.opencsv.CSVWriter;
import org.testng.Assert;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CsvWriterUtil {

    public static void writeFlightsToCsv(List<FlightData> flights, String filePath) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
            
            writer.writeNext(FlightData.getCsvHeader());
            
            for (FlightData flight : flights) {
                writer.writeNext(flight.toCsvRow());
            }
            System.out.println("Data successfully written to " + filePath + " file.");
        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail("An error occurred while writing to the CSV file: " + e.getMessage());
        }
    }
}