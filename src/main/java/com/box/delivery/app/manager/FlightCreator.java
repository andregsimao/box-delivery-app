package com.box.delivery.app.manager;

import com.box.delivery.app.entity.Airplane;
import com.box.delivery.app.entity.AirplaneType;
import com.box.delivery.app.enums.AirplaneTypeEnum;
import com.box.delivery.app.repository.AirplaneRepository;
import com.box.delivery.app.repository.AirplaneTypeRepository;
import com.box.delivery.app.repository.AirportRepository;
import com.box.delivery.app.repository.FlightRepository;
import com.box.delivery.app.entity.Airport;
import com.box.delivery.app.entity.Flight;

public class FlightCreator {
    private static FlightCreator instance;
    private final FlightRepository flightRepository;
    private final AirportRepository airportRepository;
    private final AirplaneRepository airplaneRepository;
    private final AirplaneTypeRepository airplaneTypeRepository;


    private final static int DEFAULT_AIRPLANE_CAPACITY = 20;

    private FlightCreator() {
        flightRepository = FlightRepository.getInstance();
        airportRepository = AirportRepository.getInstance();
        airplaneRepository = AirplaneRepository.getInstance();
        airplaneTypeRepository = AirplaneTypeRepository.getInstance();
    }

    public static FlightCreator getInstance() {
        if (instance == null) {
            instance = new FlightCreator();
        }
        return instance;
    }

    public Flight persistFlight(int day, long flightId, String departureCode, String arrivalCode) {
        Airport departureAirport = new Airport(departureCode);
        airportRepository.merge(departureAirport);

        Airport arrivalAirport = new Airport(arrivalCode);
        airportRepository.merge(arrivalAirport);

        AirplaneType airplaneType = new AirplaneType(AirplaneTypeEnum.GENERIC.getId(), DEFAULT_AIRPLANE_CAPACITY);
        airplaneTypeRepository.merge(airplaneType);

        Airplane airplane = new Airplane(airplaneType);
        airplaneRepository.merge(airplane);

        Flight flight = new Flight(flightId, day, departureAirport, arrivalAirport, airplane);
        flightRepository.merge(flight);

        return flight;
    }

    public boolean isInvalidAirportCode(String airportCode) {
        if(airportCode == null || airportCode.isEmpty()) {
            return true;
        }
        for (int i = 0; i < airportCode.length(); i++) {
            if (!Character.isLetter(airportCode.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    public long maxFlightId() {
        Long maxId = flightRepository.getMaxFlightId();
        return maxId != null ? maxId : 0;
    }
}
