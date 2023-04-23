package manager;

import com.box.delivery.app.entity.Airport;
import com.box.delivery.app.entity.Flight;
import com.box.delivery.app.manager.FlightCreator;
import com.box.delivery.app.repository.AirportRepository;
import com.box.delivery.app.repository.FlightRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FlightCreatorTest {
    static FlightCreator flightCreator;
    static FlightRepository flightRepository;
    static AirportRepository airportRepository;


    @BeforeAll
    public static void setup() {
        flightRepository = mock(FlightRepository.class);
        airportRepository = mock(AirportRepository.class);

        try (MockedStatic<FlightRepository> mockedStaticFlightRepository = Mockito.mockStatic(FlightRepository.class)) {
            try (MockedStatic<AirportRepository> mockedStaticAirportRepository = Mockito.mockStatic(AirportRepository.class)) {
                mockedStaticAirportRepository
                        .when(AirportRepository::getInstance).thenReturn(airportRepository);
                mockedStaticFlightRepository
                        .when(FlightRepository::getInstance).thenReturn(flightRepository);

                flightCreator = FlightCreator.getInstance();
            }
        }
    }

    @Test
    public void Should_ReturnSameInstance_When_GettingInstance() {
        FlightCreator flightCreator1 = FlightCreator.getInstance();
        FlightCreator flightCreator2 = FlightCreator.getInstance();
        assertEquals(flightCreator1, flightCreator2);
    }

    @Test
    public void Should_PersistAirportsAndFlights_When_AddingNewFlight() {
        int day = 1;
        int flightId = 2;
        Airport departure = new Airport("YYL");
        Airport arrival = new Airport("GIG");

        Flight flight = flightCreator.persistFlight(day, flightId, departure, arrival);

        assertEquals(flightId, flight.getId());
        assertEquals(day, flight.getFlightDay());
        assertEquals(departure, flight.getDepartureAirport());
        assertEquals(arrival, flight.getArrivalAirport());

        verify(airportRepository, times(1)).merge(departure);
        verify(airportRepository, times(1)).merge(arrival);
        verify(flightRepository, times(1)).persist(flight);
    }

    @Test
    public void Should_AssertAirportCodeAsInvalid_When_CodeIsNull() {
        boolean isValid = flightCreator.isInvalidAirportCode(null);
        assertTrue(isValid);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "   "})
    public void Should_AssertAirportCodeAsInvalid_When_CodeIsEmpty(String code) {
        boolean isValid = flightCreator.isInvalidAirportCode(code);
        assertTrue(isValid);
    }

    @ParameterizedTest
    @ValueSource(strings = {"2", "s3", "43A ", "ABC ", "s'a"})
    public void Should_AssertAirportCodeAsInvalid_When_CodeIsNotOnlyLetters(String code) {
        boolean isValid = flightCreator.isInvalidAirportCode(code);
        assertTrue(isValid);
    }

    @ParameterizedTest
    @ValueSource(strings = {"GIG", "YYC", "YUL"})
    public void Should_AssertAirportCodeAsValid_When_CodeIsOnlyLetters(String code) {
        boolean isValid = flightCreator.isInvalidAirportCode(code);
        assertFalse(isValid);
    }

    @Test
    public void Should_ReturnZeroAsMaxFlightId_When_FlightScheduleIsEmpty() {
        when(flightRepository.getMaxFlightId()).thenReturn(null);
        long maxFlightId = flightCreator.maxFlightId();
        assertEquals(0, maxFlightId);
    }

    @Test
    public void Should_ReturnMaxFlightId_When_FlightScheduleIsNotEmpty() {
        long expectedMaxId = 2;
        when(flightRepository.getMaxFlightId()).thenReturn(expectedMaxId);
        long maxFlightId = flightCreator.maxFlightId();
        assertEquals(expectedMaxId, maxFlightId);
    }
}
