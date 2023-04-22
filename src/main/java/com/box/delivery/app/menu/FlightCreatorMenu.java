package com.box.delivery.app.menu;

import com.box.delivery.app.entity.Airport;
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

        for(int day = 1; day <= numberOfDays; day++) {
            int numberOfFlights = getPositiveIntUserInput(scanner, "number of flights in day " + day);
            for(int flight = 1; flight <= numberOfFlights; flight++) {
                Airport departure = new Airport("YYC", "Calgary");
                Airport arrival = new Airport("GIG", "Galeao");
                flightCreator.persistFlight(day, flight, departure, arrival);
            }
        }
    }
}
