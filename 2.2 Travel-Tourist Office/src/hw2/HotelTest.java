package hw2;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

class HotelTest {

	private Room[] rooms = new Room[] { new Room("Double"), new Room("Queen") };
	private Hotel hotel = new Hotel("Test Hotel", rooms);

	@Test
	public void testReserveRoom() {
		int price = hotel.reserveRoom("Queen");
		assertEquals("Price for reserved Double room should be correct", 11000, price);
	}

	@Test
	public void testReserveRoomFail() {
		assertEquals("Code should throw exception", IllegalArgumentException.class, hotel.reserveRoom("King"));
	}

	@Test
	public void testCancelRoom() {
		hotel.reserveRoom("Double");
		assertTrue("Cancellation should succeed", hotel.cancelRoom("Double"));
	}

}
