package com.box.delivery.app.menu;

import com.box.delivery.app.config.HibernateUtil;
import com.box.delivery.app.manager.FlightEnquirer;
import java.util.Optional;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BoxDeliveryMenu {
    private static final Logger logger = LoggerFactory.getLogger(BoxDeliveryMenu.class);
    private final Scanner scanner;
    private final FlightEnquirer flightEnquirer;
    private final FlightCreatorMenu flightCreatorMenu;

    public BoxDeliveryMenu() {
        this.scanner = new Scanner(System.in);
        this.flightEnquirer = FlightEnquirer.getInstance();
        this.flightCreatorMenu = FlightCreatorMenu.getInstance();
    }

    public void run() {
        showMainMenu();
        CommandOption commandOption = getChosenOption();
        while (commandOption != CommandOption.EXIT) {
            switch (commandOption) {
                case INSERT_FLIGHTS:
                    flightCreatorMenu.run();
                    break;
                case CHECK_FLIGHTS:
                    flightEnquirer.printAllFlights();
                    break;
                case GENERATE_ITINERARIES:
                    break;
            }
            commandOption = getChosenOption();
        }

        HibernateUtil.destroy();
        logger.info("hibernate registry destroyed");

        scanner.close();
        logger.info("scanner closed");
    }

    private void showMainMenu() {
        Printer.printMessage("Option between 0 and 3");
    }

    private CommandOption getChosenOption() {
        while(scanner.hasNext()){
            if(scanner.hasNextInt(10)){
                int optionValue = scanner.nextInt();
                Optional<CommandOption> commandOption = CommandOption.getOptionByValue(optionValue);
                if(commandOption.isPresent()) {
                    return commandOption.get();
                } else {
                    Printer.printMessage("Invalid option " + optionValue + ". Choose between the options in the menu");
                }
            } else{
                Printer.printMessage("The option needs to be an Integer number");
                scanner.next();
            }
            skipSpecialChars();
        }
        return CommandOption.EXIT;
    }

    private void skipSpecialChars() {
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
    }
}
