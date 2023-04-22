package com.box.delivery.app.manager;

import com.box.delivery.app.repository.AirportRepository;
import com.box.delivery.app.repository.FlightRepository;
import com.box.delivery.app.entity.Airport;
import com.box.delivery.app.entity.Flight;

public class FlightCreator {
    private static FlightCreator instance;
    private final FlightRepository flightRepository;
    private final AirportRepository airportRepository;

    private FlightCreator() {
        flightRepository = FlightRepository.getInstance();
        airportRepository = AirportRepository.getInstance();
    }

    public static FlightCreator getInstance() {
        if (instance == null) {
            instance = new FlightCreator();
        }
        return instance;
    }

    public Flight persistFlight(int day, int flightId, Airport departure, Airport arrival) {
        airportRepository.merge(departure);
        airportRepository.merge(arrival);
        Flight flight = new Flight(flightId, day, departure, arrival);
        flightRepository.persist(flight);
        return flight;
    }

    public boolean isInvalidAirportCode(String airportCode) {
        if(airportCode == null || airportCode.length() != 3) {
            return true;
        }
        for (int i = 0; i < airportCode.length(); i++) {
            if (!Character.isLetter(airportCode.charAt(i))) {
                return true;
            }
        }
        return false;
    }
}
