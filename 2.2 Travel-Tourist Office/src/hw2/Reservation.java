package hw2;

/**
 * Abstract class representing a general reservation. This class serves as a
 * base for specific types of reservations, such as hotel or flight
 * reservations. It encapsulates common properties like the name of the person
 * or entity making the reservation. Specific cost calculation and equality
 * checks are deferred to concrete subclasses.
 * 
 * @author Panagiotis Tsembekis
 * @version 1.0
 * @since 13/03/2024
 */
public abstract class Reservation {

	private String name;

	/**
	 * Constructs a Reservation with the specified name.
	 *
	 * @param name The name associated with the reservation.
	 */
	public Reservation(String name) {
		this.name = name;
	}

	/**
	 * Gets the name associated with this reservation.
	 *
	 * @return The reservation name.
	 */
	public final String reservationName() {
		return this.name;
	}

	/**
	 * Calculates and returns the cost associated with this reservation. The
	 * specific calculation is implemented in subclasses.
	 *
	 * @return The cost of the reservation.
	 */
	public abstract int getCost();

	/**
	 * Compares this reservation to another object for equality. The specific
	 * criteria for equality are implemented in subclasses.
	 *
	 * @param o The object to compare with this reservation.
	 * @return true if the objects are considered equal, false otherwise.
	 */
	public abstract boolean equals(Object o);

}
