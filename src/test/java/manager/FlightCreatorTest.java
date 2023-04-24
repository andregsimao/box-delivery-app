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
import org.mockito.ArgumentCaptor;
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

        try (MockedStatic<FlightRepository> mockedFlightRepository = Mockito.mockStatic(FlightRepository.class)) {
            try (MockedStatic<AirportRepository> mockedAirportRepository = Mockito.mockStatic(AirportRepository.class)) {
                mockedAirportRepository
                    .when(AirportRepository::getInstance).thenReturn(airportRepository);
                mockedFlightRepository
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
        String departureCode = "YYL";
        String arrivalCode = "GIG";

        Flight flight = flightCreator.persistFlight(day, flightId, departureCode, arrivalCode);

        assertEquals(flightId, flight.getId());
        assertEquals(day, flight.getFlightDay());
        assertEquals(departureCode, flight.getDepartureAirport().getCode());
        assertEquals(arrivalCode, flight.getArrivalAirport().getCode());

        ArgumentCaptor<Airport> mergedAirports = ArgumentCaptor.forClass(Airport.class);
        verify(airportRepository, times(2))
            .merge(mergedAirports.capture());

        assertEquals(2, mergedAirports.getAllValues().size());
        assertEquals(departureCode, mergedAirports.getAllValues().get(0).getCode());
        assertEquals(arrivalCode, mergedAirports.getAllValues().get(1).getCode());

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
