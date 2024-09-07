package hw2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;

class FlightReservationTest {

	private Airport departure = new Airport(0, 0, 100), arrival = new Airport(100, 100, 200);
	private FlightReservation flightReservation = new FlightReservation("Bob Traveller", departure, arrival);

	@Test
	public void testGetCost() {
		// Cost calculation based on provided formula. This needs to be adjusted
		// according to the actual implementation.
		assertTrue("Flight reservation cost should be 355", flightReservation.getCost() == 355);
	}

	@Test
	public void testSameDepartureArrival() {
		assertEquals("Code should throw exception", IllegalArgumentException.class,
				new FlightReservation("Error Case", departure, departure));
	}

	@Test
	public void testEquals() {
		FlightReservation sameFlightReservation = new FlightReservation("Bob Traveller", departure, arrival);
		assertTrue("Flight reservations should be equal", flightReservation.equals(sameFlightReservation));
	}

}
