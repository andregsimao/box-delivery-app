package manager;

import com.box.delivery.app.entity.Airport;
import com.box.delivery.app.entity.Flight;
import com.box.delivery.app.manager.FlightEnquirer;
import com.box.delivery.app.repository.FlightRepository;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FlightEnquirerTest {
    static FlightEnquirer flightEnquirer;
    static FlightRepository flightRepository;

    @BeforeAll
    public static void setup() {
        flightRepository = mock(FlightRepository.class);

        try (MockedStatic<FlightRepository> mockedStatic = Mockito.mockStatic(FlightRepository.class)) {
            mockedStatic.when(FlightRepository::getInstance).thenReturn(flightRepository);

            flightEnquirer = FlightEnquirer.getInstance();
        }
    }

    @Test
    public void Should_ReturnSameInstance_When_GettingInstance() {
        FlightEnquirer flightEnquirer1 = FlightEnquirer.getInstance();
        FlightEnquirer flightEnquirer2 = FlightEnquirer.getInstance();
        assertEquals(flightEnquirer1, flightEnquirer2);
    }

    @Test
    public void Should_PrintAllFlightsInFlightSchedule() throws IOException {
        List<Flight> flights = new ArrayList<>();

        int flightId1 = 10, flightDay1 = 23;
        Airport departureAirport1 = new Airport("YUL");
        Airport arrivalAirport1 = new Airport("GIG");
        flights.add(new Flight(flightId1, flightDay1, departureAirport1, arrivalAirport1));

        int flightId2 = 11, flightDay2 = 33;
        Airport departureAirport2 = new Airport("YYC");
        Airport arrivalAirport2 = new Airport("GRU");
        flights.add(new Flight(flightId2, flightDay2, departureAirport2, arrivalAirport2));

        String expectedFlight1Description = "Flight: 10, departure: YUL, arrival: GIG, day: 23";
        String expectedFlight2Description = "Flight: 11, departure: YYC, arrival: GRU, day: 33";

        when(flightRepository.getFlights()).thenReturn(flights);

        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bo));

        flightEnquirer.printAllFlights();

        bo.flush();
        String allWrittenLines = bo.toString();

        assertTrue(allWrittenLines.contains(expectedFlight1Description));
        assertTrue(allWrittenLines.contains(expectedFlight2Description));
        assertEquals(flights.size(), StringUtils.countMatches(allWrittenLines, "Flight: "));
    }
}
