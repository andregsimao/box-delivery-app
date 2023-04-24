package menu;

import com.box.delivery.app.manager.FlightEnquirer;
import com.box.delivery.app.menu.BoxDeliveryMenu;
import com.box.delivery.app.menu.CommandOption;
import com.box.delivery.app.menu.FlightCreatorMenu;
import java.io.*;
import java.util.Scanner;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class BoxDeliveryMenuTest {
    BoxDeliveryMenu boxDeliveryMenu;
    Scanner scanner;
    FlightCreatorMenu flightCreatorMenu;
    FlightEnquirer flightEnquirer;
    ByteArrayOutputStream bo;

    @BeforeEach
    public void setup() {
        flightCreatorMenu = mock(FlightCreatorMenu.class);
        flightEnquirer = mock(FlightEnquirer.class);

        bo = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bo));
    }

    @Test
    public void Should_AskOptionNumber_When_Started() throws IOException {
        String exitInput = Integer.toString(CommandOption.EXIT.getValue());
        setInputScanner(exitInput);

        boxDeliveryMenu.run();

        String allWrittenLines = getWrittenLines();

        assertTrue(allWrittenLines.contains("Choose and type the option number one of the following options:"));
        for(CommandOption commandOption: CommandOption.values()) {
            assertTrue(allWrittenLines.contains(commandOption.getValue() + ": " + commandOption.getDescription()));
        }
    }

    @Test
    public void Should_AskOptionNumberAgain_When_InputIsInvalid() throws IOException {
        String exitInput = Integer.toString(CommandOption.EXIT.getValue());
        String invalidInput = "s";
        setInputScanner(invalidInput + " " + exitInput);

        boxDeliveryMenu.run();

        String allWrittenLines = getWrittenLines();

        assertTrue(allWrittenLines.contains("The option needs to be an Integer number"));
        assertEquals(2, StringUtils.countMatches(allWrittenLines, "option: "));
    }

    @Test
    public void Should_InsertFlights_When_Requested() {
        String exitInput = Integer.toString(CommandOption.EXIT.getValue());
        String option = Integer.toString(CommandOption.INSERT_FLIGHTS.getValue());
        setInputScanner(option + " " + exitInput);

        boxDeliveryMenu.run();

        verify(flightCreatorMenu, times(1)).run();
        verify(flightEnquirer, times(0)).printAllFlights();
    }

    @Test
    public void Should_PrintFlights_When_Requested() {
        String exitInput = Integer.toString(CommandOption.EXIT.getValue());
        String option = Integer.toString(CommandOption.CHECK_FLIGHTS.getValue());
        setInputScanner(option + " " + exitInput);

        boxDeliveryMenu.run();

        verify(flightEnquirer, times(1)).printAllFlights();
        verify(flightCreatorMenu, times(0)).run();
    }

    @Test
    public void Should_AskOptionNumberAgain_When_OptionIsInvalid() throws IOException {
        String exitInput = Integer.toString(CommandOption.EXIT.getValue());
        String invalidInput = "95";
        String option = "1";
        setInputScanner(invalidInput + " " + option + " " + exitInput);

        boxDeliveryMenu.run();

        verify(flightCreatorMenu, times(1)).run();

        String allWrittenLines = getWrittenLines();

        assertTrue(allWrittenLines.contains("Invalid option '" + invalidInput + "'. Choose among the options in the menu"));
        assertEquals(3, StringUtils.countMatches(allWrittenLines, "option: "));
    }

    private void setInputScanner(String input) {
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        scanner = new Scanner(System.in);

        try (MockedStatic<FlightCreatorMenu> mockedFlightCreatorMenu = Mockito.mockStatic(FlightCreatorMenu.class)) {
            try (MockedStatic<FlightEnquirer> mockedFlightEnquirer = Mockito.mockStatic(FlightEnquirer.class)) {
                mockedFlightEnquirer.when(FlightEnquirer::getInstance).thenReturn(flightEnquirer);
                mockedFlightCreatorMenu
                    .when(() -> FlightCreatorMenu.getInstance(any()))
                    .thenReturn(flightCreatorMenu);

                boxDeliveryMenu = new BoxDeliveryMenu();
            }
        }
    }

    private String getWrittenLines() throws IOException {
        bo.flush();
        return bo.toString();
    }
}

