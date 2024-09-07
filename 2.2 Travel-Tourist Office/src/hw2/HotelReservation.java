package hw2;

/**
 * Represents a hotel reservation, extending {@link Reservation} class.
 * HotelReservation encapsulates properties such as the hotel, room type, and
 * number of nights. It calculates the cost of the reservation based on the
 * price per night and the total number of nights.
 * 
 * @author Panagiotis Tsembekis
 * @version 1.0
 * @since 13/03/2024
 */
public class HotelReservation extends Reservation {

	private Hotel hotel;
	private String roomType;
	private int numOfNights;
	private int pricePerNight;

	/**
	 * Constructs a HotelReservation with the specified name, hotel, room type, and
	 * number of nights.
	 *
	 * @param name        The name associated with the reservation.
	 * @param hotel       The hotel for the reservation.
	 * @param roomType    The type of room for the reservation.
	 * @param numOfNights The number of nights for the reservation.
	 */
	public HotelReservation(String name, Hotel hotel, String roomType, int numOfNights) {
		super(name); // call the constructor of the superclass
		this.hotel = hotel;
		this.roomType = roomType;
		this.pricePerNight = hotel.reserveRoom(roomType);
		this.numOfNights = numOfNights;
	}

	/**
	 * Gets the number of nights for this hotel reservation.
	 *
	 * @return The number of nights.
	 */
	public int getNumOfNights() {
		return this.numOfNights;
	}

	/**
	 * Calculates and returns the cost associated with this hotel reservation. The
	 * cost is calculated based on the price per night and the total number of
	 * nights.
	 *
	 * @return The cost of the hotel reservation.
	 */
	@Override
	public int getCost() {
		return pricePerNight * numOfNights;
	}

	/**
	 * Compares this hotel reservation to another object for equality. Two hotel
	 * reservations are considered equal if they have the same name, hotel, room
	 * type, number of nights, and total cost.
	 *
	 * @param o The object to compare with this hotel reservation.
	 * @return true if the objects are considered equal, false otherwise.
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		HotelReservation other = (HotelReservation) o;
		return this.reservationName().equals(other.reservationName()) && this.hotel.equals(other.hotel)
				&& this.roomType.equals(other.roomType) && this.numOfNights == other.numOfNights
				&& this.getCost() == other.getCost();
	}

}
