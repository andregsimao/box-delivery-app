package com.box.delivery.app.menu;

import com.box.delivery.app.Repository.FlightRepository;
import com.box.delivery.app.entity.Airport;
import com.box.delivery.app.entity.Flight;

public class FlightCreatorMenu {
    private static FlightCreatorMenu instance;
    private final FlightRepository flightRepository;

    private FlightCreatorMenu () {
         this.flightRepository = FlightRepository.getInstance();
    }

    public static FlightCreatorMenu getInstance() {
        if (instance == null) {
            instance = new FlightCreatorMenu();
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
