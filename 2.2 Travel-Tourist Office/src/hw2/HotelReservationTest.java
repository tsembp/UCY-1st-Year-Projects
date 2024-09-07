package hw2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;

class HotelReservationTest {

	private Hotel hotel = new Hotel("Example Hotel", new Room[] { new Room("Double"), new Room("Double") });
	private HotelReservation reservation = new HotelReservation("John Doe", hotel, "Double", 3);

	@Test
	public void testGetCost() {
		assertEquals("Cost should be correctly calculated", 27000, reservation.getCost());
	}

	@Test
	public void testEquals() {
		HotelReservation sameReservation = new HotelReservation("John Doe", hotel, "Double", 3);
		assertTrue("Reservations should be equal", reservation.equals(sameReservation));
	}
}
