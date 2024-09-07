package hw2;

/**
 * Represents an airport with a specific location (x and y coordinates on the
 * map) and associated fee.
 * 
 * @author Panagiotis Tsembekis
 * @version 1.0
 * @since 13/03/2024
 */
public class Airport {

	private int xPos;
	private int yPos;
	private int fee;

	/**
	 * Constructs an Airport object with specified x and y positions and a fee.
	 *
	 * @param xPos The x-coordinate of the airport on a map.
	 * @param yPos The y-coordinate of the airport on a map.
	 * @param fee  The fee associated with the airport.
	 */
	public Airport(int xPos, int yPos, int fee) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.fee = fee;
	}

	/**
	 * Gets the fee associated with the airport.
	 *
	 * @return The airport fee.
	 */
	public int getFees() {
		return this.fee;
	}

	/**
	 * Helper method for {@link #getDistance(Airport, Airport)}
	 *
	 * @param x1 The x-coordinate of the first point.
	 * @param y1 The y-coordinate of the first point.
	 * @param x2 The x-coordinate of the second point.
	 * @param y2 The y-coordinate of the second point.
	 * @return The distance between the two points, rounded up to the nearest whole
	 *         number.
	 */
	private static int calculateDistance(int x1, int y1, int x2, int y2) {
		double d = Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));
		return (int) Math.ceil(d);
	}

	/**
	 * Calculates the distance between two airports with the help of
	 * {@link #calculateDistance(int, int, int, int)}
	 *
	 * @param a1 The first airport.
	 * @param a2 The second airport.
	 * @return The distance between the two airports, rounded up to the nearest
	 *         whole number.
	 */
	public static int getDistance(Airport a1, Airport a2) {
		return calculateDistance(a1.xPos, a1.yPos, a2.xPos, a2.yPos);
	}

}
