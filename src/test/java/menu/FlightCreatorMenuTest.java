package menu;

import com.box.delivery.app.manager.FlightCreator;
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
import static org.mockito.Mockito.times;

public class FlightCreatorMenuTest {
    FlightCreatorMenu flightCreatorMenu;
    Scanner scanner;
    FlightCreator flightCreator;
    ByteArrayOutputStream bo;

    @BeforeEach
    public void setup() {
        flightCreator = mock(FlightCreator.class);

        bo = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bo));
    }

    @Test
    public void Should_ReturnSameInstance_When_GettingInstance() {
        FlightCreatorMenu instance = FlightCreatorMenu.getInstance(scanner);
        assertEquals(flightCreatorMenu, instance);
    }

    @Test
    public void Should_AskFlightParameters_When_Started() throws IOException {
        String input = "2 1 YYC GIG 2 YYT GRU YYC YVT";
        setInputScanner(input);

        when(flightCreator.maxFlightId())
            .thenReturn(0L)
            .thenReturn(1L);

        flightCreatorMenu.run();

        String allWrittenLines = getWrittenLines();

        assertTrue(allWrittenLines.contains("number of days:"));

        assertTrue(allWrittenLines.contains("number of flights in day 1:"));
        assertTrue(allWrittenLines.contains("Day 1, flight 1. Departure Airport Code:"));
        assertTrue(allWrittenLines.contains("Day 1, flight 1. Departure Airport Code:"));

        assertTrue(allWrittenLines.contains("number of flights in day 2:"));
        assertTrue(allWrittenLines.contains("Day 2, flight 2. Departure Airport Code:"));
        assertTrue(allWrittenLines.contains("Day 2, flight 2. Departure Airport Code:"));
        assertTrue(allWrittenLines.contains("Day 2, flight 3. Departure Airport Code:"));
        assertTrue(allWrittenLines.contains("Day 2, flight 3. Departure Airport Code:"));
    }

    @Test
    public void Should_PersistFlights_When_ReadingUserInput() {
        String input = "2 1 YYC GIG 2 YYT GRU YYC YVT";
        setInputScanner(input);

        when(flightCreator.maxFlightId())
                .thenReturn(0L)
                .thenReturn(1L);

        flightCreatorMenu.run();

        verify(flightCreator, times(1))
            .persistFlight(1, 1, "YYC", "GIG");
        verify(flightCreator, times(1))
            .persistFlight(2, 2, "YYT", "GRU");
        verify(flightCreator, times(1))
            .persistFlight(2, 3, "YYC", "YVT");
    }

    @Test
    public void Should_AskInputAgain_When_InputIsNotValid() throws IOException {
        String input = "1 1 YYC 2 GIG ";
        setInputScanner(input);

        when(flightCreator.maxFlightId()).thenReturn(0L);
        when(flightCreator.isInvalidAirportCode("2")).thenReturn(true);
        flightCreatorMenu.run();

        String allWrittenLines = getWrittenLines();

        verify(flightCreator, times(1))
            .persistFlight(1, 1L, "YYC", "GIG");

        String arrivalAirportInputPrompt = "Day 1, flight 1. Arrival Airport Code:";
        assertEquals(2, StringUtils.countMatches(allWrittenLines, arrivalAirportInputPrompt));
    }

    private void setInputScanner(String input) {
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        scanner = new Scanner(System.in);

        try (MockedStatic<FlightCreator> mockedStatic = Mockito.mockStatic(FlightCreator.class)) {
            mockedStatic.when(FlightCreator::getInstance).thenReturn(flightCreator);

            flightCreatorMenu = FlightCreatorMenu.getInstance(scanner);
        }
    }

    private String getWrittenLines() throws IOException {
        bo.flush();
        return bo.toString();
    }
}
