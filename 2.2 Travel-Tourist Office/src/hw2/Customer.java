package hw2;

/**
 * Represents a customer who can make reservations and manage their balance.
 * Customers can add funds, add reservations to their basket, remove
 * reservations from their basket, and check out their reservations.
 * 
 * Customers have a name, a balance, and a basket to hold their reservations.
 * 
 * @author Panagiotis Tsembekis
 * @version 1.0
 * @since 13/03/2024
 */
public class Customer {

	private String name;
	private int balance;
	private Basket order;

	/**
	 * Constructs a Customer with the specified name and initial balance.
	 *
	 * @param name    The name of the customer.
	 * @param balance The initial balance of the customer in cents.
	 */
	public Customer(String name, int balance) {
		this.name = name;
		this.balance = balance;
		this.order = new Basket();
	}

	/**
	 * Retrieves the name of the customer.
	 *
	 * @return The name of the customer.
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Retrieves the balance of the customer.
	 *
	 * @return The balance of the customer in cents.
	 */
	public int getBalance() {
		return this.balance;
	}

	/**
	 * Retrieves the basket of reservations associated with the customer.
	 *
	 * @return The basket containing the customer's reservations.
	 */
	public Basket getBasket() {
		return this.order;
	}

	/**
	 * Adds funds to the customer's balance.
	 *
	 * @param amount The amount of funds to add to the balance in cents.
	 * @return The new balance after adding funds.
	 * @throws IllegalArgumentException If the amount of funds is negative.
	 */
	public int addFunds(int amount) {
		if (amount < 0) // if amount is a negative number, throw an exception
			throw new IllegalArgumentException("Funds value must be a positive integer!");
		else
			this.balance += amount;

		return this.balance;
	}

	/**
	 * Adds a reservation to the customer's basket.
	 *
	 * @param newReservation The reservation to add to the basket.
	 * @return The number of reservations in the basket after adding.
	 * @throws IllegalArgumentException If the reservation's name does not
	 *                                  correspond to the customer's name.
	 */
	public int addToBasket(Reservation newReservation) {
		if (newReservation.reservationName().equalsIgnoreCase(name)) { // if reservation's name matches the customer's,
																		// add it to the basket
			order.add(newReservation);

			return order.getNumOfReservations(); // return the current number of reservations
		} else
			// If the given reservation's name doesn't match the customer's name, throw an
			// exception
			throw new IllegalArgumentException(
					"Couldn't add reservation: The name to the new reservation does not match the customer's name!");
	}

	/**
	 * Adds a hotel or bnb reservation to the customer's basket.
	 *
	 * @param hotel            The hotel where the reservation is made.
	 * @param roomType         The type of room reserved.
	 * @param numOfNights      The number of nights for the reservation.
	 * @param includeBreakfast Whether breakfast is included in the reservation.
	 * @return The number of reservations in the basket after adding.
	 */
	public int addToBasket(Hotel hotel, String roomType, int numOfNights, boolean includeBreakfast) {
		if (includeBreakfast) { // if user wants breakfast, create and add a new BnBReservation
			order.add(new BnBReservation(name, hotel, roomType, numOfNights));
		} else { // if user wants breakfast, create and add a new HotelReservation
			order.add(new HotelReservation(name, hotel, roomType, numOfNights));
		}

		return order.getNumOfReservations(); // return current number of reservations
	}

	/**
	 * Adds a flight reservation to the customer's basket.
	 *
	 * @param departure The airport of departure.
	 * @param arrival   The airport of arrival.
	 * @return The number of reservations in the basket after adding.
	 */
	public int addToBasket(Airport departure, Airport arrival) {
		FlightReservation newReservation = new FlightReservation(name, departure, arrival);

		// Add new reservation to the basket
		order.add(newReservation);

		// Return the current number of reservations
		return order.getNumOfReservations();
	}

	/**
	 * Removes a reservation from the customer's basket.
	 *
	 * @param res The reservation to remove from the basket.
	 * @return true if the reservation was successfully removed, false otherwise.
	 */
	public boolean removeFromBasket(Reservation res) {
		return order.remove(res);
	}

	/**
	 * Proceeds to check out the reservations in the customer's basket. Deducts the
	 * total cost of reservations from the customer's balance.
	 *
	 * @return The remaining balance after checking out.
	 * @throws IllegalStateException If the basket is empty or if the balance is
	 *                               insufficient.
	 */
	public int checkOut() {
		int totalCost = 0;
		Reservation[] reservations = order.getProducts();

		if (reservations.length == 0)
			throw new IllegalStateException("Can't checkout: Basket is empty! Add reservations to checkout.");

		// Iterate through all the reservations and get the total cost of all the
		// reservations
		for (Reservation res : reservations) {
			totalCost += res.getCost();
		}

		// Check if customer's balance can cover the whole cost
		if (this.balance < totalCost)
			throw new IllegalStateException(
					"Can't check out: Not enough balance to cover total costs! Add funds to proceed to checkout.");
		else {
			order.clear(); // clear the customer's basket
			this.balance -= totalCost; // charge the total cost from the customer
			return this.balance; // return his remaining balance
		}
	}

}
