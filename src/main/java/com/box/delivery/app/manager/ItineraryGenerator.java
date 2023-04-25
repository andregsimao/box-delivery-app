package com.box.delivery.app.manager;

import com.box.delivery.app.dto.FlightsInAirport;
import com.box.delivery.app.dto.Order;
import com.box.delivery.app.entity.Flight;
import com.box.delivery.app.menu.Printer;
import com.box.delivery.app.repository.FlightRepository;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class ItineraryGenerator {
    private static ItineraryGenerator instance;
    private final FlightRepository flightRepository;

    private ItineraryGenerator() {
        flightRepository = FlightRepository.getInstance();
    }

    public static ItineraryGenerator getInstance() {
        if (instance == null) {
            instance = new ItineraryGenerator();
        }
        return instance;
    }

    public void generateItinerary(String fileName) {
        Printer.printBlankLine();
        Printer.printSeparationLines(2);

        try {
            List<Order> orders = getOrdersFromFile(fileName);
            orders.sort(Comparator.comparingInt(Order::getId));
            List<Flight> flights = flightRepository.getFlightsOrdered();
            HashMap<String, FlightsInAirport> flightsInAirportsMap = getFlightsInAirports(flights);
            if(orders.isEmpty()) {
                Printer.printLine("No orders were given in the file '" + fileName + "'");
            }

            for(Order order: orders) {
                FlightsInAirport flightsInAirport = flightsInAirportsMap.get(order.getDestinationCode());
                Flight flight = flightsInAirport != null ? flightsInAirport.loadOrderInFlight() : null;
                printOrderLoadedInFlight(order, flight);
            }
        } catch (Exception ex) {
            Printer.printLine("Error when reading from the input file '" + fileName + "' as Json");
            ex.printStackTrace();
        }
    }

    private HashMap<String, FlightsInAirport> getFlightsInAirports(List<Flight> flights) {
        HashMap<String, FlightsInAirport> flightsInAirportsMap = new HashMap<>();
        for(Flight flight: flights) {
            String arrivalCode = flight.getArrivalAirport().getCode();
            FlightsInAirport flightsInAirport = flightsInAirportsMap.get(arrivalCode);
            if(flightsInAirport == null) {
                flightsInAirport = new FlightsInAirport(flight);
                flightsInAirportsMap.put(arrivalCode, flightsInAirport);
            }
            flightsInAirport.addFlight(flight);
        }
        return flightsInAirportsMap;
    }

    private void printOrderLoadedInFlight(Order order, Flight flight) {
        if(flight == null) {
            Printer.printLine("order: " + order.getOrderName() + ", flightNumber: not scheduled");
        } else {
            String sb = "order: " + order.getOrderName() +
                ", flightNumber: " + flight.getId() +
                ", departure: " + flight.getDepartureAirport().getCode() +
                ", arrival: " + flight.getArrivalAirport().getCode() +
                ", day: " + flight.getFlightDay();
            Printer.printLine(sb);
        }
    }

    private List<Order> getOrdersFromFile(String fileName) throws IOException {
        Gson gson = new Gson();

        Reader reader = Files.newBufferedReader(Paths.get(fileName));

        Map<String, LinkedTreeMap> map = gson.fromJson(reader, Map.class);

        List<Order> orders = new ArrayList<>();
        for (Map.Entry<String, LinkedTreeMap> entry : map.entrySet()) {
            int id = extractId(entry.getKey());
            String destinationCode = extractDestinationCode(entry.getValue());
            Order order = new Order(id, destinationCode, entry.getKey());
            orders.add(order);
        }
        reader.close();

        return orders;
    }

    private String extractDestinationCode(LinkedTreeMap map) {
        return map.get("destination").toString();
    }

    private int extractId(String orderKey) {
        String orderIdString = orderKey.split("-")[1];
        return Integer.parseInt(orderIdString);
    }
}
