package hw2;

/**
 * Represents a hotel that manages a collection of rooms of various types.
 * Allows for reserving and canceling rooms based on room type. Each room is
 * represented by a {@link Room} object.
 * 
 * @author Panagiotis Tsembekis
 * @version 1.0
 * @since 13/03/2024
 */
public class Hotel {

	private String name;
	private Room rooms[];

	/**
	 * Constructs a Hotel with a name and an array of Room objects. Performs a deep
	 * copy of the array to maintain encapsulation.
	 * 
	 * @param name  The name of the hotel.
	 * @param rooms An array of Room objects representing the hotel's rooms.
	 */
	public Hotel(String name, Room rooms[]) {
		this.name = name;
		this.rooms = new Room[rooms.length];
		for (int i = 0; i < rooms.length; i++)
			this.rooms[i] = new Room(rooms[i]);
	}

	/**
	 * Attempts to reserve a room of the specified type. If an available room is
	 * found, it is marked as unavailable and its price is returned.
	 * 
	 * @param roomType The type of room to reserve ("Double", "Queen", "King").
	 * @return The price of the reserved room.
	 * @throws IllegalArgumentException If no available room of the specified type
	 *                                  is found.
	 */
	public int reserveRoom(String roomType) {
		Room availableRoom = Room.findAvailableRoom(rooms, roomType); // find an available room
		if (availableRoom != null) // make sure that an available room is found and return its price
			return availableRoom.getPrice();
		else // if no room of specified type is available throw an exception
			throw new IllegalArgumentException("Couldn't find available room with specified type!");
	}

	/**
	 * Attempts to make a room of the specified type available again. If a
	 * previously reserved room of the specified type is found, its availability is
	 * toggled back to true.
	 * 
	 * @param roomType The type of room to cancel the reservation for.
	 * @return true if a room reservation was successfully canceled, false
	 *         otherwise.
	 */
	public boolean cancelRoom(String roomType) {
		return Room.makeRoomAvailable(rooms, roomType);
	}

}
