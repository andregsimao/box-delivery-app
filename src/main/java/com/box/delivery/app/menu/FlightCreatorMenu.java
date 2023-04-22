package com.box.delivery.app.menu;

import com.box.delivery.app.entity.Airport;
import com.box.delivery.app.entity.Flight;
import com.box.delivery.app.manager.FlightCreator;
import java.util.Scanner;

public class FlightCreatorMenu extends Menu{
    private static FlightCreatorMenu instance;
    private final FlightCreator flightCreator;
    private final Scanner scanner;

    private FlightCreatorMenu (Scanner scanner) {
         this.flightCreator = FlightCreator.getInstance();
         this.scanner = scanner;
    }

    public static FlightCreatorMenu getInstance(Scanner scanner) {
        if (instance == null) {
            instance = new FlightCreatorMenu(scanner);
        }
        return instance;
    }

    public void run() {
        int numberOfDays = getPositiveIntUserInput(scanner, "number of days");
        int flightId = 1;
        for(int day = 1; day <= numberOfDays; day++) {
            int numberOfFlights = getPositiveIntUserInput(scanner, "number of flights in day " + day);
            for(; flightId <= numberOfFlights; flightId++) {
                String departureParameterName = "Day "+ day + ", flight " + flightId + ". Departure Airport Code";
                Airport departure = getAirportInput(departureParameterName);

                String arrivalParameterName = "Day "+ day + ", flight " + flightId + ". Arrival Airport Code";
                Airport arrival = getAirportInput(arrivalParameterName);

                Flight flight = flightCreator.persistFlight(day, flightId, departure, arrival);

                Printer.printLine("Success adding flight: " + flight);
            }
        }
    }

    private Airport getAirportInput(String parameterName) {
        String airportCode;
        do {
            airportCode = getUserInput(scanner, parameterName);

            if(flightCreator.isInvalidAirportCode(airportCode)) {
                Printer.printLine("Invalid Airport Code " + airportCode + ". It needs to have exactly 3 letters");
            }
        } while(flightCreator.isInvalidAirportCode(airportCode));

        return new Airport(airportCode.toUpperCase());
    }
}
