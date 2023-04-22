package com.box.delivery.app;

import com.box.delivery.app.Repository.FlightRepository;
import com.box.delivery.app.config.HibernateUtil;
import com.box.delivery.app.entity.Airport;
import com.box.delivery.app.entity.Flight;
import com.box.delivery.app.menu.Menu;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        Menu menu = new Menu();
        FlightRepository flightRepository = new FlightRepository();
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

        menu.close();
        logger.info("scanner closed");
    }
}

