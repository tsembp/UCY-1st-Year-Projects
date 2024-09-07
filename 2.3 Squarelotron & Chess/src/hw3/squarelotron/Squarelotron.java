package hw3.squarelotron;

/**
 * This class represents a squarelotron, a square matrix with certain rotation
 * and flipping operations. The class contains common variables and methods
 * shared between SmallSquarelotron and LargeSquarelotron.
 * 
 * @author Panagiotis Tsembekis
 * @version 1.2
 * @since 31/04/24
 */
public abstract class Squarelotron implements SquarelotronMethods {

	/** The 2D array representing the squarelotron. */
	private int[][] squarelotron;

	/**
	 * Constructs a squarelotron object from an array of integers.
	 *
	 * @param array An array of integers representing the elements of the
	 *              squarelotron.
	 */
	public Squarelotron(int array[]) {
		// Hangle checks for length and array's contents
		if (array.length != 16 && array.length != 25)
			throw new IllegalArgumentException("Invalid length of array: Length must be 16 or 25.");
		else if (!checkArray(array))
			throw new IllegalArgumentException(
					"Invalid array: Array must only contain positive integers in the range (0,99].");
		else {
			// Create a new array and copy the elements inside
			squarelotron = new int[(int) Math.sqrt(array.length)][(int) Math.sqrt(array.length)];
			int index = 0;
			
			for (int i = 0; i < squarelotron.length; i++) {
				for (int j = 0; j < squarelotron[0].length; j++) {
					squarelotron[i][j] = array[index];
					index++;
				}
			}
		}
	}

	/**
	 * Returns a direct reference to the squarelotron's matrix.
	 * 
	 * @return The 2D array representing the square matrix of the squarelotron.
	 */
	public int[][] getSquarelotron() {
		return this.squarelotron;
	}

	/**
	 * Helper method for {@link #makeSquarelotron(int[])}.Checks if the elements of an array
	 * are within the allowed range.
	 *
	 * @param array The array to be checked.
	 * @return true if any element is less than or equal to 0 or greater than 99,
	 *         false otherwise.
	 */
	private static boolean checkArray(int[] array) {
		for (int num : array) {
			if (num <= 0 || num > 99) // if negative or >99 number is found return false
				return false;
		}

		return true;
	}

	/**
	 * Creates a Squarelotron object from an array of integers.
	 *
	 * @param array An array of integers representing the elements of the
	 *              squarelotron.
	 * @return A Squarelotron object.
	 * @throws IllegalArgumentException if the length of the array is not 16 or 25,
	 *                                  or if any element of the array is not within
	 *                                  the range (0, 99].
	 */
	public static Squarelotron makeSquarelotron(int[] array) throws IllegalArgumentException{
		if(array.length != 16 && array.length != 25)
			throw new IllegalArgumentException ("Invalid array size: " + array.length);
		else if(!checkArray(array))
				throw new IllegalArgumentException ("Invalid array contents. Numbers must be in [1,99]");
		else
			if(array.length == 16)
				return new SmallSquarelotron(array);
			else
				return new LargeSquarelotron(array);

	}

	/**
	 * Returns the numbers of the squarelotron in a 1D array.
	 *
	 * @return An array containing the elements of the squarelotron in row-major
	 *         order.
	 */
	@Override
	public int[] numbers() {
		// Unravel the 2D array into a 1D array
		int[] tempArr = new int[(int) Math.pow(squarelotron.length, 2)];
		int index = 0;
		for (int[] row : squarelotron) {
			for (int element : row)
				tempArr[index++] = element;
		}

		return tempArr;
	}

	/**
	 * Helper method for {@link #upsideDownFlip(String)}, {@link #leftRightFlip(String)},
	 * {@link #inverseDiagonalFlip(String)}, {@link #mainDiagonalFlip(String)} and {@link #sideFlip(String)}.
	 * 
	 * Checks if a given row and column index is within the specified ring of the
	 * squarelotron.
	 *
	 * @param row  The row index.
	 * @param col  The column index.
	 * @param size The size of the squarelotron.
	 * @param ring The ring type ("outer" or "inner").
	 * @return true if the index is within the specified ring, false otherwise.
	 */
	private static boolean isInRing(int row, int col, int size, String ring) {
		int minRowCol = 0; // minimum row and column value
		int maxRowCol = size - 1; // maximum row and column value

		if (ring.equals("outer")) { // for outer ring, indexes are the max values
			return row == minRowCol || row == maxRowCol || col == minRowCol || col == maxRowCol;
		} else if (ring.equals("inner")) { // for inner ring, indexes must be smaller than max values
			return row > minRowCol && row < maxRowCol && col > minRowCol && col < maxRowCol;
		}

		return false;
	}

	/**
	 * Performs an upside-down flip of the specified ring of the squarelotron.
	 *
	 * @param ring The ring type to be flipped ("outer" or "inner").
	 * @return A new Squarelotron object after the flip operation.
	 */
	@Override
	public Squarelotron upsideDownFlip(String ring) {
		int size = squarelotron.length;
		Squarelotron sq = makeSquarelotron(this.numbers()); // make a new squarelotron
		if (ring.equals("outer")) { // handle outside ring
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					if (isInRing(i, j, size, ring)) { // if element is inside the ring, swap it
						sq.squarelotron[i][j] = squarelotron[size - i - 1][j];
					}
				}
			}
		} else if (ring.equals("inner")) { // handle inner ring
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					if (isInRing(i, j, size, ring)) { // if element is inside the ring, swap it
						sq.squarelotron[i][j] = squarelotron[size - i - 1][j];
					}
				}
			}
		}

		return sq;
	}

	/**
	 * Performs a left-right flip of the specified ring of the squarelotron.
	 *
	 * @param ring The ring type to be flipped ("outer" or "inner").
	 * @return A new Squarelotron object after the flip operation.
	 */
	@Override
	public Squarelotron leftRightFlip(String ring) {
		int size = squarelotron.length;
		Squarelotron sq = makeSquarelotron(this.numbers()); // make a new squarelotron
		if (ring.equals("outer")) { // handle outside ring
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					if (isInRing(i, j, size, ring)) { // if element is inside the ring, swap it
						sq.squarelotron[i][j] = squarelotron[i][size - 1 - j];
					}
				}
			}
		} else if (ring.equals("inner")) { // handle inner ring
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					if (isInRing(i, j, size, ring)) { // if element is inside the ring, swap it
						sq.squarelotron[i][j] = squarelotron[i][size - 1 - j];
					}
				}
			}
		}

		return sq;
	}

	/**
	 * Performs an inverse diagonal flip of the specified ring of the squarelotron.
	 *
	 * @param ring The ring type to be flipped ("outer" or "inner").
	 * @return A new Squarelotron object after the flip operation.
	 */
	@Override
	public Squarelotron inverseDiagonalFlip(String ring) {
		int size = squarelotron.length;
		Squarelotron sq = makeSquarelotron(this.numbers()); // make a new squarelotron
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (isInRing(i, j, size, ring) && i + j < size - 1) { // handle appropriate ring and inv diag
					// swap the elements of sq.sqarelotron
					int temp = sq.squarelotron[i][j];
					sq.squarelotron[i][j] = sq.squarelotron[size - 1 - j][size - 1 - i];
					sq.squarelotron[size - 1 - j][size - 1 - i] = temp;
				}
			}
		}

		return sq;
	}

	/**
	 * Performs a main diagonal flip of the specified ring of the squarelotron.
	 *
	 * @param ring The ring type to be flipped ("outer" or "inner").
	 * @return A new Squarelotron object after the flip operation.
	 */
	@Override
	public Squarelotron mainDiagonalFlip(String ring) {
		int size = squarelotron.length;
		Squarelotron sq = makeSquarelotron(this.numbers()); // make a new squarelotron
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (isInRing(i, j, size, ring) && i < j) { // handle appropriate ring and main diag
					// swap the elements of sq.sqarelotron
					int temp = sq.squarelotron[i][j];
					sq.squarelotron[i][j] = sq.squarelotron[j][i];
					sq.squarelotron[j][i] = temp;
				}
			}
		}

		return sq;
	}

	@Override
	public Squarelotron sideFlip(String side) {
		int size = squarelotron.length;
		Squarelotron sq = makeSquarelotron(this.numbers()); // make new squarelotron

		switch (side) { // handle each side
		case "left":
			// two first columns, swap their elements
			for (int i = 0; i < size; i++) {
				int temp = sq.squarelotron[i][0];
				sq.squarelotron[i][0] = sq.squarelotron[i][1];
				sq.squarelotron[i][1] = temp;
			}
			break;
		case "right":
			// two last columns, swap their elements
			for (int i = 0; i < size; i++) {
				int temp = sq.squarelotron[i][size - 2];
				sq.squarelotron[i][size - 2] = sq.squarelotron[i][size - 1];
				sq.squarelotron[i][size - 1] = temp;
			}
			break;
		case "bottom":
			// two last columns, swap their elements
			for (int j = 0; j < size; j++) {
				int temp = sq.squarelotron[size - 2][j];
				sq.squarelotron[size - 2][j] = sq.squarelotron[size - 1][j];
				sq.squarelotron[size - 1][j] = temp;
			}
			break;
		case "top":
			// two first columns, swap their elements
			for (int j = 0; j < size; j++) {
				int temp = sq.squarelotron[0][j];
				sq.squarelotron[0][j] = sq.squarelotron[1][j];
				sq.squarelotron[1][j] = temp;
			}
			break;
		default:
			// for other side entered, throw an exception
			throw new IllegalArgumentException("Invalid side: " + side);
		}

		return sq;
	}

	/**
	 * Rotates the squarelotron clockwise (or counter-clockwise) for the specified
	 * number of turns.
	 * 
	 * @param numberOfTurns the number of clockwise turns to perform
	 */
	@Override
	public void rotateRight(int numberOfTurns) {
		int size = squarelotron.length;
		int effectiveTurns = numberOfTurns % 4; // calculate number of effective turns
		int[][] tempArr = new int[size][size];

		if (effectiveTurns == 0) // 0 turns -> array remains the same
			return;

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (Math.abs(effectiveTurns) == 2) // 180 degree turn
					tempArr[size - 1 - i][size - 1 - j] = squarelotron[i][j];
				else if (effectiveTurns == 1 || effectiveTurns == -3) // clockwise rotation
					tempArr[j][size - 1 - i] = squarelotron[i][j];
				else // counter-clockwise rotation
					tempArr[size - 1 - j][i] = squarelotron[i][j];
			}
		}

		squarelotron = tempArr; // update the array
	}
	
	/**
	 * Helper method for {@link #equals(Object)}. Checks if two object arrays have any
	 * different elements.
	 * 
	 * @param s1 The first squarelotron to compare.
	 * @param s2 The second squarelotron to compare.
	 * @return true if both squarelotrons have the same size and identical content;
	 *         false otherwise.
	 */
	private boolean areEqual(Squarelotron s1, Squarelotron s2) {
		if (s1.squarelotron.length != s2.squarelotron.length)
			return false;
		for (int i = 0; i < s1.squarelotron.length; i++) {
			for (int j = 0; j < s1.squarelotron[i].length; j++) {
				if (s1.squarelotron[i][j] != s2.squarelotron[i][j])
					return false;
			}
		}

		return true;
	}
	
	/**
	 * Determines if this squarelotron is equal to another object.
	 * Equality is defined based on the structure and content of the squarelotron.
	 * A squarelotron is considered equal to another squarelotron if they have the
	 * same size and all corresponding elements are equal. Additionally, this
	 * implementation considers a squarelotron equal to any of its rotated versions
	 * (90°, 180°, 270° clockwise).
	 * 
	 * @param o The object to be compared with this squarelotron for equality.
	 * @return true if the specified object is equal to this squarelotron or any of its
	 * rotated versions; false otherwise.
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Squarelotron other = (Squarelotron) o;

		// Check for equality without rotation
		if(areEqual(this,other)) return true;

		// Temporarily clone this Squarelotron for rotation comparison
		Squarelotron tempSquarelotron = makeSquarelotron(this.numbers());
		for (int i = 1; i <= 3; i++) { // Rotate three times (90°, 180°, 270°)
			tempSquarelotron.rotateRight(1);
			if (areEqual(tempSquarelotron, other))
				return true;
		}

		return false;
	}

	/**
	 * Returns a string representation of the squarelotron.
	 * 
	 * The returned string consists of each row of the squarelotron's matrix on a
	 * new line, with each element formatted to a fixed width for alignment. This
	 * provides a clear and aligned representation of the squarelotron's current
	 * state when printed.
	 * 
	 * @return A string representation of the squarelotron's matrix.
	 */
	@Override
	public String toString() {
		String output = "";
		for (int i = 0; i < squarelotron.length; i++) {
			for (int j = 0; j < squarelotron.length; j++) {
				output += String.format("%4d", squarelotron[i][j]);
			}
			output += "\n";
		}

		return output;
	}

}
