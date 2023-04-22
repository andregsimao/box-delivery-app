package com.box.delivery.app.manager;

import com.box.delivery.app.Repository.FlightRepository;
import com.box.delivery.app.entity.Flight;
import com.box.delivery.app.menu.Printer;
import java.util.List;

public class FlightEnquirer {
    private static FlightEnquirer instance;
    private final FlightRepository flightRepository;

    private FlightEnquirer() {
        flightRepository = FlightRepository.getInstance();
    }

    public static FlightEnquirer getInstance() {
        if (instance == null) {
            instance = new FlightEnquirer();
        }
        return instance;
    }

    public void printAllFlights() {
        Printer.printBlankLine();
        Printer.printSeparationLines(2);

        Printer.printLine("Flight Schedule:");
        Printer.printBlankLine();

        printFlights();
    }

    private void printFlights() {
        List<Flight> flights = flightRepository.getFlights();
        if(flights.isEmpty()) {
            Printer.printLine("No flights scheduled");
        } else {
            flights.forEach(Printer::printLine);
        }
    }
}
