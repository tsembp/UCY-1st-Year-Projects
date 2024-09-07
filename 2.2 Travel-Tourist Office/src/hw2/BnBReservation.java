package hw2;

/**
 * Represents a BnB reservation, extending the {@link HotelReservation} class.
 * BnBReservation inherits properties from HotelReservation and adds a specific
 * cost calculation method that includes an additional fee for BnB services.
 * 
 * @author Panagiotis Tsembekis
 * @version 1.0
 * @since 13/03/2024
 */
public class BnBReservation extends HotelReservation {

	/**
	 * Constructs a BnBReservation with the specified name, hotel, room type, and
	 * number of nights.
	 *
	 * @param name        The name associated with the reservation.
	 * @param hotel       The hotel where the reservation is made.
	 * @param roomType    The type of room reserved.
	 * @param numOfNights The number of nights for the reservation.
	 */
	public BnBReservation(String name, Hotel hotel, String roomType, int numOfNights) {
		super(name, hotel, roomType, numOfNights); // call the constructor of the superclass
	}

	/**
	 * Calculates and returns the total cost of the BnB reservation. This includes
	 * the base cost from the HotelReservation and an additional fee for BnB
	 * services.
	 *
	 * @return The total cost of the BnB reservation.
	 */
	public int getCost() {
		// return total cost consists of the hotel reservation cost + €10 per night for
		// breakfast
		return super.getCost() + ((10 * 100) * super.getNumOfNights());
	}

}
