package com.box.delivery.app.manager;

import com.box.delivery.app.Repository.FlightRepository;
import com.box.delivery.app.entity.Airport;
import com.box.delivery.app.entity.Flight;

public class FlightCreator {
    private static FlightCreator instance;
    private final FlightRepository flightRepository;

    private FlightCreator() {
        flightRepository = FlightRepository.getInstance();
    }

    public static FlightCreator getInstance() {
        if (instance == null) {
            instance = new FlightCreator();
        }
        return instance;
    }

    public void persistFlight(int day, int flightId, Airport departure, Airport arrival) {
        Flight flight = new Flight(flightId, day, departure, arrival);
        flightRepository.persistFlight(flight);
    }
}
