package com.box.delivery.app.menu;

import com.box.delivery.app.config.HibernateUtil;
import com.box.delivery.app.manager.FlightEnquirer;
import java.util.Optional;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class BoxDeliveryMenu extends Menu{
    private static final Logger logger = LoggerFactory.getLogger(BoxDeliveryMenu.class);
    private final Scanner scanner;
    private final FlightEnquirer flightEnquirer;
    private final FlightCreatorMenu flightCreatorMenu;

    public BoxDeliveryMenu() {
        this.scanner = new Scanner(System.in);
        this.flightEnquirer = FlightEnquirer.getInstance();
        this.flightCreatorMenu = FlightCreatorMenu.getInstance(scanner);
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
                    Printer.printLine("This feature is not implemented yet");
                    break;
            }
            showMainMenu();
            commandOption = getChosenOption();
        }

        HibernateUtil.destroy();
        logger.info("hibernate registry destroyed");

        scanner.close();
        logger.info("scanner closed");
    }

    private void showMainMenu() {
        Printer.printBlankLine();
        Printer.printSeparationLines(1);
        Printer.printBlankLine();

        Printer.printLine("Choose and type the option number one of the following options:");
        Printer.printBlankLine();
        for(CommandOption commandOption: CommandOption.values()) {
            Printer.printLine(commandOption.value + ": " + commandOption.description);
        }
    }

    private CommandOption getChosenOption() {
        Optional<CommandOption> commandOption;
        int optionNumber;

        do {
            optionNumber = getIntUserInput(scanner, "option");
            commandOption = CommandOption.getOptionByValue(optionNumber);

            if(!commandOption.isPresent()) {
                Printer.printLine("Invalid option '" + optionNumber + "'. Choose among the options in the menu");
            }
        } while(!commandOption.isPresent());

        return commandOption.get();
    }
}
