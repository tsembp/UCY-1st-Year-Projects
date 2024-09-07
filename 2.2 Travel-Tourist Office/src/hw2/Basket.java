package hw2;

/**
 * Represents a basket for holding reservations. It allows adding, removing, and
 * retrieving reservations, as well as calculating the total cost of all
 * reservations in the basket.
 * 
 * @author Panagiotis Tsembekis
 * @version 1.0
 * @since 13/03/2024
 */
public class Basket {

	private Reservation reservations[];

	/**
	 * Constructs an empty Basket.
	 */
	public Basket() {
		this.reservations = new Reservation[0];
	}

	/**
	 * Returns an array containing all reservations in the basket.
	 *
	 * @return An array of reservations.
	 */
	public Reservation[] getProducts() {
		Reservation tempArr[] = reservations.clone(); // clone the current array of reservations
		return tempArr;
	}

	/**
	 * Adds a reservation to the basket.
	 *
	 * @param newReservation The reservation to add.
	 * @return The number of reservations in the basket after adding.
	 */
	public int add(Reservation newReservation) {
		// Create array with the length of the original array but incremented by one
		Reservation newArr[] = new Reservation[this.reservations.length + 1];

		// Copy elements of original table to the new array of reservations
		for (int i = 0; i < reservations.length; i++) {
			newArr[i] = reservations[i];
		}

		// Add the new reservation at the end of the array
		newArr[newArr.length - 1] = newReservation;

		// Replace the old array with the new
		reservations = newArr;

		return getNumOfReservations();
	}

	/**
	 * Helper method for {@link #remove(Reservation)} to find the index of a
	 * specific reservation in the basket.
	 *
	 * @param res The reservation to find.
	 * @return The index of the reservation if found, otherwise -1.
	 */
	private int findIndexOfRes(Reservation res) {
		for (int i = 0; i < reservations.length; i++) { // iterate through the array
			if (res.equals(reservations[i]))
				return i; // if we found the exact reservation, we return its index
		}

		// if we didn't manage to locate the exact reservation, we return -1
		return -1;
	}

	/**
	 * Removes a reservation from the basket.
	 *
	 * @param res The reservation to remove.
	 * @return true if the reservation was successfully removed, false otherwise.
	 */
	public boolean remove(Reservation res) {
		int indexOfRes = findIndexOfRes(res);
		if (indexOfRes == -1)
			return false;
		else {
			// Create a new array of reservations with one less reservation
			Reservation newArr[] = new Reservation[this.reservations.length - 1];

			// Copy elements starting from the beginning until the indexOfRes (exclusive)
			for (int i = 0; i < indexOfRes; i++)
				newArr[i] = reservations[i];

			// Copy elements starting from the reservation after "res" until the last one
			for (int i = indexOfRes + 1; i < reservations.length; i++)
				newArr[i - 1] = reservations[i];

			// Replace the current array with the updated one
			reservations = newArr;

			return true;
		}

	}

	/**
	 * Clears all reservations from the basket.
	 */
	public void clear() {
		this.reservations = new Reservation[0];
	}

	/**
	 * Returns the number of reservations in the basket.
	 *
	 * @return The number of reservations.
	 */
	public int getNumOfReservations() {
		return this.reservations.length;
	}

	/**
	 * Calculates and returns the total cost of all reservations in the basket.
	 *
	 * @return The total cost of all reservations.
	 */
	public int getTotalCost() {
		int total = 0;
		for (Reservation res : reservations) { // iterate through every reservation
			total += res.getCost(); // get its cost and add it to the total cost
		}

		return total;
	}

}
