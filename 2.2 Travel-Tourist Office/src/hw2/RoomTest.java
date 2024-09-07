package hw2;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;

class RoomTest {

	private Room roomDouble = new Room("Double"), roomQueen = new Room("Queen"), roomKing = new Room("kiNg");

	@Test
	public void testGetType() {
		assertEquals("Room type should be Double", "Double", roomDouble.getType());
		assertEquals("Room type should be Queen", "Queen", roomQueen.getType());
		assertEquals("Room type should be King", "kiNg", roomKing.getType());
	}

	@Test
	public void testGetPrice() {
		assertEquals("Price for Double should be 9000 cents", 9000, roomDouble.getPrice());
		assertEquals("Price for Queen should be 11000 cents", 11000, roomQueen.getPrice());
		assertEquals("Price for Double should be 15000 cents", 15000, roomKing.getPrice());
	}

	@Test
	public void testChangeAvailability() {
		roomDouble.changeAvailability();
		// add getter for isAvailable for testing
		//assertEquals("Room should not be available after change", false, roomDouble.isAvailable());
	}

}
