package hw2;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;

class BnBReservationTest {

	private Hotel hotel = new Hotel("BnB Hotel",
			new Room[] { new Room("Double"), new Room("Queen"), new Room("King") });
	// ^ you can also remove some rooms from here ^^^^^^^ to check the exceptio
	// throw for no available room for that type
	private BnBReservation bnbReservation1 = new BnBReservation("Alice Wonderland", hotel, "Double", 3);
	private BnBReservation bnbReservation2 = new BnBReservation("Alice Wonderland", hotel, "Queen", 2);
	private BnBReservation bnbReservation3 = new BnBReservation("Alice Wonderland", hotel, "King", 5);

	@Test
	public void testGetCost() {
		// consider 90/110/150 * 100 cents per night + 10 * 100 per night for breakfast
		assertEquals("BnB reservation cost should include room cost plus BnB charge", 30000, bnbReservation1.getCost());
		assertEquals("BnB reservation cost should include room cost plus BnB charge", 24000, bnbReservation2.getCost());
		assertEquals("BnB reservation cost should include room cost plus BnB charge", 80000, bnbReservation3.getCost());
	}

}
