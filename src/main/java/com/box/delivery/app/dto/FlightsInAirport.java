package com.box.delivery.app.dto;

import com.box.delivery.app.entity.Airport;
import com.box.delivery.app.entity.Flight;
import java.util.ArrayList;
import java.util.List;

public class FlightsInAirport {
    Airport airport;
    int currentIndex;
    List<Flight> flights;
    List<Integer> remainingCapacity;

    public FlightsInAirport(Flight flight) {
        this.airport = flight.getArrivalAirport();
        currentIndex = 0;
        flights = new ArrayList<>();
        remainingCapacity = new ArrayList<>();
    }

    public void addFlight(Flight flight) {
        flights.add(flight);
        remainingCapacity.add(flight.getCapacity());
    }

    public Flight loadOrderInFlight() {
        if(currentIndex >= remainingCapacity.size()) {
            return null;
        }
        int currentFlightCapacity = remainingCapacity.get(currentIndex);
        if(currentFlightCapacity > 0) {
            remainingCapacity.set(currentIndex, currentFlightCapacity - 1);
            return flights.get(currentIndex);
        } else {
            currentIndex++;
            return loadOrderInFlight();
        }
    }
}
