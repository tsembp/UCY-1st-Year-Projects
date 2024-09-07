package hw3.squarelotron;

/**
 * Interface defining operations applicable to a Squarelotron.
 * 
 * @author Pyrros Bratskas
 * @version 1.0
 * @since 22/03/24
 */
public interface SquarelotronMethods {

	/**
	 * Returns the numbers of the Squarelotron in a 1D array format.
	 *
	 * @return An array containing the elements of the Squarelotron.
	 */
	public int[] numbers();

	/**
	 * Performs an upside-down flip of the specified ring.
	 *
	 * @param ring Specifies the ring to flip ("outer" or "inner").
	 * @return A new Squarelotron instance reflecting the flip.
	 */
	public Squarelotron upsideDownFlip(String ring);

	/**
	 * Performs a left-right flip of the specified ring.
	 *
	 * @param ring Specifies the ring to flip ("outer" or "inner").
	 * @return A new Squarelotron instance reflecting the flip.
	 */
	public Squarelotron leftRightFlip(String ring);

	/**
	 * Performs an inverse diagonal flip of the specified ring.
	 *
	 * @param ring Specifies the ring to flip ("outer" or "inner").
	 * @return A new Squarelotron instance reflecting the flip.
	 */
	public Squarelotron inverseDiagonalFlip(String ring);

	/**
	 * Performs a main diagonal flip of the specified ring.
	 *
	 * @param ring Specifies the ring to flip ("outer" or "inner").
	 * @return A new Squarelotron instance reflecting the flip.
	 */
	public Squarelotron mainDiagonalFlip(String ring);

	/**
	 * Performs a side flip based on the specified side.
	 *
	 * @param side Specifies the side to flip ("top", "bottom", "left", "right").
	 * @return A new Squarelotron instance reflecting the flip.
	 */
	public Squarelotron sideFlip(String side);

	/**
	 * Rotates the Squarelotron 90 degrees to the right for the specified number of
	 * turns. A negative value indicates a rotation to the left.
	 *
	 * @param numberOfTurns The number of times the Squarelotron should be rotated.
	 */
	public void rotateRight(int numberOfTurns);

	/**
	 * Checks if the current Squarelotron is equal to another object.
	 * 
	 * @param object The object to compare against.
	 * @return true if the objects are equal, false otherwise.
	 */
	@Override
	public boolean equals(Object object);

	/**
	 * Returns a string representation of the Squarelotron.
	 * 
	 * @return The string representation.
	 */
	@Override
	public String toString();

}
