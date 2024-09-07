package hw2;

/**
 * Represents a room within a hotel, characterized by its type, price, and
 * availability. The room type can be one of "Double", "Queen", or "King", each
 * with a predefined price. Availability indicates whether the room is currently
 * available for reservation.
 * 
 * @author Panagiotis Tsembekis
 * @version 1.0
 * @since 13/03/2024
 */
public class Room {

	private String roomType;
	private int price;
	private boolean isAvailable;

	/**
	 * Constructs a Room with the specified type. Initializes the room as available
	 * and sets its price based on the type.
	 * 
	 * @param roomType The type of the room, which can be "Double", "Queen", or
	 *                 "King".
	 * @throws IllegalArgumentException if the roomType is not one of the specified
	 *                                  types.
	 */

	public Room(String roomType) {
		// Check if given room type is one of the accepted types
		if (!(roomType.equalsIgnoreCase("Double") || roomType.equalsIgnoreCase("Queen")
				|| roomType.equalsIgnoreCase("King"))) {
			throw new IllegalArgumentException( // if not throw an exception
					"Can't create room with given type value. Room types available: Double, Queen, King.");
		} else {
			this.roomType = roomType;
			this.isAvailable = true; // set the new room as available
			// Assign the room's price according to its type
			if (roomType.equalsIgnoreCase("Double"))
				this.price = 90 * 100;
			else if (roomType.equalsIgnoreCase("Queen"))
				this.price = 110 * 100;
			else
				this.price = 150 * 100;
		}
	}

	/**
	 * Copy constructor. Creates a new Room object with the same attributes as the
	 * provided room.
	 * 
	 * @param r The Room object to be copied.
	 */
	public Room(Room r) {
		this.roomType = r.roomType;
		this.price = r.price;
		this.isAvailable = r.isAvailable;
	}

	/**
	 * Returns the type of the room.
	 * 
	 * @return The room type.
	 */
	public String getType() {
		return this.roomType;
	}

	/**
	 * Returns the price of the room in cents.
	 * 
	 * @return The room price.
	 */
	public int getPrice() {
		return this.price;
	}

	/**
	 * Toggles the availability status of the room.
	 */
	public void changeAvailability() {
		this.isAvailable = !this.isAvailable;
	}

	/**
	 * Searches for the first available room of the specified type in the provided
	 * array of rooms and marks it as unavailable.
	 * 
	 * @param rooms    An array of Room objects.
	 * @param roomType The type of room to find.
	 * @return The first available Room of the specified type, or null if no
	 *         available room is found.
	 */
	public static Room findAvailableRoom(Room rooms[], String roomType) {
		for (Room room : rooms) { // iterate through all the rooms
			// find the first in order available room of the specific type
			if (room.isAvailable && room.roomType.equalsIgnoreCase(roomType)) {
				room.changeAvailability();
				return room;
			}
		}
		
		// if no room of that type is available return a null pointer
		return null;
	}

	/**
	 * Makes the first unavailable room of the specified type in the provided array
	 * available again.
	 * 
	 * @param rooms An array of Room objects.
	 * @param type  The type of room to make available.
	 * @return true if a room was made available, false otherwise.
	 */
	public static boolean makeRoomAvailable(Room rooms[], String type) {
		for (Room room : rooms) { // iterate through all the rooms
			// find the first in order unavailable room of the specific type
			if (!room.isAvailable && room.roomType.equals(type)) {
				room.changeAvailability();
				return true;
			}
		}

		return false;
	}

}
