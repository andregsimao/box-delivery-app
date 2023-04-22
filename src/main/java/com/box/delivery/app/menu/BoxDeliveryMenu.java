package com.box.delivery.app.menu;

import com.box.delivery.app.Repository.FlightRepository;
import com.box.delivery.app.config.HibernateUtil;
import com.box.delivery.app.entity.Airport;
import com.box.delivery.app.entity.Flight;
import java.util.List;
import java.util.Optional;
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
        showMainMenu();
        CommandOption commandOption = getChosenOption();
        while (commandOption != CommandOption.EXIT) {
            switch (commandOption) {
                case EXIT:
                    return;
                case INSERT_FLIGHTS:
                    break;
                case CHECK_FLIGHTS:
                    break;
                case GENERATE_ITINERARIES:
                    break;
            }
            commandOption = getChosenOption();
        }

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

    private void showMainMenu() {
        printMessage("Option between 0 and 3");
    }

    private CommandOption getChosenOption() {
        while(scanner.hasNext()){
            if(scanner.hasNextInt(10)){
                int optionValue = scanner.nextInt();
                Optional<CommandOption> commandOption = CommandOption.getOptionByValue(optionValue);
                if(commandOption.isPresent()) {
                    return commandOption.get();
                } else {
                    printMessage("Invalid option " + optionValue + ". Choose between the options in the menu");
                }
            } else{
                printMessage("The option needs to be an Integer number");
                scanner.next();
            }
            skipSpecialChars();
        }
        return CommandOption.EXIT;
    }

    private void printMessage(String message) {
        System.out.println(message);
    }

    private void skipSpecialChars() {
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
    }
}
