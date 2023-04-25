package manager;

import com.box.delivery.app.entity.Airplane;
import com.box.delivery.app.entity.AirplaneType;
import com.box.delivery.app.entity.Airport;
import com.box.delivery.app.entity.Flight;
import com.box.delivery.app.manager.ItineraryGenerator;
import com.box.delivery.app.repository.FlightRepository;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ItineraryGeneratorTest {
    static ItineraryGenerator itineraryGenerator;
    static FlightRepository flightRepository;

    @BeforeAll
    public static void setup() {
        flightRepository = mock(FlightRepository.class);

        try (MockedStatic<FlightRepository> mockedStatic = Mockito.mockStatic(FlightRepository.class)) {
            mockedStatic.when(FlightRepository::getInstance).thenReturn(flightRepository);

            itineraryGenerator = ItineraryGenerator.getInstance();
        }
    }

    @Test
    public void Should_ReturnSameInstance_When_GettingInstance() {
        ItineraryGenerator itineraryGenerator1 = ItineraryGenerator.getInstance();
        ItineraryGenerator itineraryGenerator2 = ItineraryGenerator.getInstance();
        assertEquals(itineraryGenerator1, itineraryGenerator2);
    }

    @Test
    public void Should_PrintItinerary_When_FlightScheduleIsEmpty() throws IOException {
        String fileName = "src/test/resources/empty-file.json";
        String expectedItinerary = "No orders were given in the file '" + fileName + "'";

        when(flightRepository.getFlightsOrdered()).thenReturn(new ArrayList<>());

        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bo));

        itineraryGenerator.generateItinerary(fileName);

        bo.flush();
        String allWrittenLines = bo.toString();

        assertTrue(allWrittenLines.contains(expectedItinerary));
    }

    @Test
    public void Should_PrintItinerary_When_FlightScheduleIsNotEmpty() throws IOException {
        String fileName = "src/test/resources/test-orders.json";
        int capacity = 2;

        List<Flight> flights = new ArrayList<>();

        Airport departure = new Airport("YUL");

        AirplaneType airplaneType = new AirplaneType(1, capacity);
        Airplane airplane = new Airplane(airplaneType);

        flights.add(new Flight(3, 1, departure, new Airport("YVR"), airplane));
        flights.add(new Flight(1, 1, departure, new Airport("YYZ"), airplane));
        flights.add(new Flight(6, 2, departure, new Airport("YVR"), airplane));
        flights.add(new Flight(5, 2, departure, new Airport("YYC"), airplane));
        flights.add(new Flight(2, 1, departure, new Airport("YYC"), airplane));
        flights.add(new Flight(4, 2, departure, new Airport("YYZ"), airplane));

        when(flightRepository.getFlightsOrdered()).thenReturn(flights);

        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bo));

        itineraryGenerator.generateItinerary(fileName);

        bo.flush();
        String allWrittenLines = bo.toString();

        assertTrue(allWrittenLines.contains("order: order-001, flightNumber: 1, departure: YUL, arrival: YYZ, day: 1"));
        assertTrue(allWrittenLines.contains("order: order-002, flightNumber: 1, departure: YUL, arrival: YYZ, day: 1"));
        assertTrue(allWrittenLines.contains("order: order-003, flightNumber: not scheduled"));
        assertTrue(allWrittenLines.contains("order: order-004, flightNumber: not scheduled"));
        assertTrue(allWrittenLines.contains("order: order-005, flightNumber: 4, departure: YUL, arrival: YYZ, day: 2"));
        assertTrue(allWrittenLines.contains("order: order-006, flightNumber: 4, departure: YUL, arrival: YYZ, day: 2"));
        assertTrue(allWrittenLines.contains("order: order-007, flightNumber: not scheduled"));
        assertTrue(allWrittenLines.contains("order: order-008, flightNumber: not scheduled"));
    }
}
