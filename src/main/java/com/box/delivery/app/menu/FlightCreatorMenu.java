package com.box.delivery.app.menu;

import com.box.delivery.app.Repository.FlightRepository;
import com.box.delivery.app.entity.Airport;
import com.box.delivery.app.entity.Flight;
import java.util.Scanner;

public class FlightCreatorMenu extends Menu{
    private static FlightCreatorMenu instance;
    private final FlightRepository flightRepository;
    private final Scanner scanner;

    private FlightCreatorMenu (Scanner scanner) {
         this.flightRepository = FlightRepository.getInstance();
         this.scanner = scanner;
    }

    public static FlightCreatorMenu getInstance(Scanner scanner) {
        if (instance == null) {
            instance = new FlightCreatorMenu(scanner);
        }
        return instance;
    }

    public void run() {
        Airport departure = new Airport("YYC", "Calgary");
        Airport arrival = new Airport("GIG", "Galeao");
        Flight flight = new Flight(1, 2, departure, arrival);
        try {
            flightRepository.persistFlight(flight);
        } catch (Exception exception) {
            // TODO
        }
    }
}
