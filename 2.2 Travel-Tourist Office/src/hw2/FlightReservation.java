package hw2;

/**
 * Represents a flight reservation, extending the {@link Reservation} class.
 * FlightReservation encapsulates specific properties such as departure and
 * arrival airports. It calculates the cost of the reservation based on airport
 * fees and airplane-related costs.
 * 
 * @author Panagiotis Tsembekis
 * @version 1.0
 * @since 13/03/2024
 */
public class FlightReservation extends Reservation {

	private Airport departure, arrival;

	/**
	 * Constructs a FlightReservation with the specified name, departure, and
	 * arrival airports.
	 *
	 * @param name      The name associated with the reservation.
	 * @param departure The airport of departure.
	 * @param arrival   The airport of arrival.
	 * @throws IllegalArgumentException If departure and arrival airports are the
	 *                                  same.
	 */
	public FlightReservation(String name, Airport departure, Airport arrival) {
		super(name); // call the constructor of the superclass
		if (departure.equals(arrival))
			// if departure airport is the same as the arrival airport throw an exception
			throw new IllegalArgumentException("Departure and arrival Airport is the same!");
		else {
			this.departure = departure;
			this.arrival = arrival;
		}
	}

	/**
	 * Calculates and returns the cost associated with this flight reservation. The
	 * cost includes fees from both departure and arrival airports, as well as
	 * airplane-related costs.
	 *
	 * @return The cost of the flight reservation.
	 */
	@Override
	public int getCost() {
		// Calculate total of fees of both airports and airplane related costs
		double fees = departure.getFees() + arrival.getFees() + 53.75;

		// Calculate distance for fuel cost calculation
		int d = Airport.getDistance(departure, arrival);
		double fuelCost = (double) d / 167.52 * 1.24;

		return (int) Math.ceil(fees + fuelCost);
	}

	/**
	 * Compares this flight reservation to another object for equality. Two flight
	 * reservations are considered equal if they have the same name, departure
	 * airport, and arrival airport.
	 *
	 * @param o The object to compare with this flight reservation.
	 * @return true if the objects are considered equal, false otherwise.
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		FlightReservation other = (FlightReservation) o;

		return this.reservationName().equals(other.reservationName()) && this.departure.equals(other.departure)
				&& this.arrival.equals(other.arrival);
	}

}
