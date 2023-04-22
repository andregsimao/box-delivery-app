package com.box.delivery.app.menu;

import com.box.delivery.app.Application;
import com.box.delivery.app.Repository.FlightRepository;
import com.box.delivery.app.config.HibernateUtil;
import com.box.delivery.app.entity.Airport;
import com.box.delivery.app.entity.Flight;
import java.util.List;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BoxDeliveryMenu {
    Scanner scanner;
    private static final Logger logger = LoggerFactory.getLogger(BoxDeliveryMenu.class);

    public BoxDeliveryMenu() {
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        FlightRepository flightRepository = FlightRepository.getInstance();
        Airport departure = new Airport("YYC", "Calgary");
        Airport arrival = new Airport("GIG", "Galeao");
        Flight flight = new Flight(1, 2, departure, arrival);
        try {
            flightRepository.persistFlight(flight);
        } catch (Exception exception) {
            // TODO
        }

        List<Flight> flights = flightRepository.getFlights();
        flights.forEach(System.out::println);

        HibernateUtil.shutdown();
        logger.info("hibernate registry destroyed");

        scanner.close();
        logger.info("scanner closed");
    }

    public void showMainMenu() {

    }
}
