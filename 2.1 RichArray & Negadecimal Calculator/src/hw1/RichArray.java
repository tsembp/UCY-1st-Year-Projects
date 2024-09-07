package hw1;

/**
 * This class provides functionality for manipulating and transforming 2D
 * arrays. It has constructors for both 1D and 2D arrays, allowing the creating
 * of RichArray objects from existing normal arrays. The class includes methods
 * for reversing, rotating, transposing, raveling and unraveling. Additionally,
 * there are methods for joining and stacking arrays, slicing a portion of the
 * array, and replacing a portion of the array with another array.
 *
 * @author Panagiotis Tsembekis
 * @version 1.0
 * @since 15/02/24
 * 
 */

public class RichArray {

	private int[][] array;

	/**
	 * Constructs a RichArray object from a 1D array.
	 * 
	 * @param array The 1D array to be used in the RichArray object.
	 */
	public RichArray(int[] array) { // object constructor for 1D array
		this(new int[1][array.length]);
		for (int i = 0; i < array.length; i++) { // copy the given array elements to the object's array
			this.array[0][i] = array[i];

		}
	}

	/**
	 * Constructs a RichArray object from a 2D array.
	 * 
	 * @param array The 2D array to be used in the RichArray object.
	 */
	public RichArray(int[][] array) { // object constructor for 2D array
		this.array = new int[array.length][array[0].length];
		for (int i = 0; i < array.length; i++) { // copy the given array elements to the object's array
			for (int j = 0; j < array[0].length; j++) {
				this.array[i][j] = array[i][j];
			}
		}
	}

	/**
	 * Reverses the elements of the RichArray horizontally.
	 *
	 * This method creates and returns new RichArray with the same dimensions as the
	 * original, where the elements in each row are reversed.
	 *
	 * @return A new RichArray with reversed elements.
	 */
	public RichArray reverse() {
		// Create a copy of the array to avoid modifying the original array
		int[][] tempArr = array;

		// Cycle through each row and reverse the elements horizontally
		for (int i = 0; i < tempArr.length; i++) {
			// Cycle up to half the columns, swapping elements symmetrically
			for (int j = 0; j < tempArr[0].length / 2; j++) {
				int temp = tempArr[i][j];
				tempArr[i][j] = tempArr[i][tempArr[0].length - 1 - j];
				tempArr[i][tempArr[0].length - 1 - j] = temp;
			}
		}

		// Create and return a new RichArray with the reversed elements
		return new RichArray(tempArr);
	}

	/**
	 * Rotates the RichArray 90 degrees to the right.
	 *
	 * This method creates and returns a new RichArray object with dimensions
	 * swapped, where each element is rotated 90 degrees clockwise from its original
	 * position.
	 *
	 * @return A new RichArray rotated 90 degrees to the right.
	 */
	public RichArray rotateRight() {
		// Create a new array with swapped dimensions for rotation
		int[][] tempArr = new int[array[0].length][array.length];

		// Cycle through the original array and rotate elements to the right
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[0].length; j++) {
				tempArr[j][array.length - 1 - i] = array[i][j];
			}
		}

		// Create and return a new RichArray with the new array with rotated elements
		return new RichArray(tempArr);
	}

	/**
	 * Rotates the RichArray 90 degrees to the left.
	 *
	 * This method creates and returns a new RichArray with dimensions swapped,
	 * where each element is rotated 90 degrees counterclockwise from its original
	 * position.
	 *
	 * @return A new RichArray rotated 90 degrees to the left.
	 */
	public RichArray rotateLeft() {
		// Create a new array with swapped dimensions for rotation
		int[][] tempArr = new int[array[0].length][array.length];

		// Cycle through the original array and rotate elements to the left
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[0].length; j++) {
				tempArr[array[0].length - 1 - j][i] = array[i][j];
			}
		}

		// Create and return a new RichArray with elements rotated to the left
		return new RichArray(tempArr);
	}

	/**
	 * Transposes the RichArray.
	 *
	 * This method creates and returns a new RichArray where the rows become columns
	 * and vice versa.
	 *
	 * @return A new RichArray transposed.
	 */
	public RichArray transpose() {
		// Create a new array with swapped dimensions for transposition
		int[][] tempArr = new int[array[0].length][array.length];

		// Cycle through the original array and swap the [i][j] element with the
		// [j][i] element
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[0].length; j++) {
				tempArr[j][i] = array[i][j];
			}
		}

		// Create and return a new RichArray with elements transposed
		return new RichArray(tempArr);
	}

	/**
	 * Ravel the RichArray into a new array with a specified width.
	 *
	 * This method creates and returns a new RichArray by flattening the original
	 * array into a 1D array with a specified width.
	 *
	 * @param n The width of the new array.
	 * @return A new RichArray raveled with the specified width.
	 * @throws IllegalArgumentException If the array's column value is not exactly
	 *                                  divisible by the given n value or if n is
	 *                                  not greater than 0.
	 */
	public RichArray ravel(int n) {
		try {
			// Check if the original array's length is exactly divisible by the given n
			// value
			if (array[0].length % n != 0) {
				throw new IllegalArgumentException(
						"Can't ravel array! " + array[0].length + " is not exactly divisible by " + n);
			} else if (n <= 0) {
				throw new IllegalArgumentException("Can't ravel array! The value of n must be greater than 0");
			} else {
				// Create a new array for raveling with the specified width
				int[][] tempArr = new int[array[0].length / n][n];

				// Index to keep track of the position in the original array
				int index = 0;

				// Cycle through the new array and fill it with elements from the original
				// array
				for (int i = 0; i < tempArr.length; i++) {
					for (int j = 0; j < n; j++) {
						tempArr[i][j] = array[0][index];
						index++;
					}
				}

				// Create and return a new RichArray with elements raveled
				return new RichArray(tempArr);
			}
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			return new RichArray(new int[][] { { 0 } });
		}

	}

	/**
	 * Unravel the RichArray into a flat 1D array.
	 *
	 * This method creates and returns a new RichArray by converting the original 2D
	 * array into a single-row 1D array.
	 *
	 * @return A new RichArray unraveled into a flat 1D array.
	 */
	public RichArray unravel() {
		// Create a new array for unraveling with a single row
		int[][] tempArr = new int[1][array.length * array[0].length];

		// Index to keep track of the position in the original array
		int index = 0;

		// Cycle through the original array and fill the 1D array
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[0].length; j++) {
				tempArr[0][index] = array[i][j];
				index++;
			}
		}

		// Create and return a new RichArray with elements unraveled
		return new RichArray(tempArr);
	}

	/**
	 * Reshapes the 2D array into a new RichArray with the specified number of
	 * columns (n), adjusting the number of rows accordingly. If the total number of
	 * elements in the original array (r*c) is not exactly divisible by n, or if n
	 * is 0, an IllegalArgumentException is thrown.
	 *
	 * @param n The number of columns for the reshaped array.
	 * @return A new RichArray representing the reshaped array.
	 * @throws IllegalArgumentException If r*c is not exactly divisible by n, or if
	 *                                  n is not greater than 0.
	 */
	public RichArray reshape(int n) {
		try {
			if (n <= 0) {
				// Handle case where n is 0 or a negative number
				throw new IllegalArgumentException(
						"Can't reshape array! (r*c)/n must be divisible by n, and n must be greater than 0.");
			} else if ((array.length * array[0].length) % n != 0) {
				// Handle case where r*c is not exactly divisible by n
				throw new IllegalArgumentException("Can't reshape array! r*c is not exactly divisible by n.");
			} else {
				// Unravel the original array into a 1D array
				RichArray unraveled = unravel();

				// Ravel the unraveled array to get the reshaped array
				RichArray reshaped = unraveled.ravel(n);

				return reshaped;
			}
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			return new RichArray(new int[][] { { 0 } });
		}

	}

	/**
	 * Join the original RichArray with given RichArray horizontally.
	 *
	 * This method creates and returns a new RichArray by horizontally concatenating
	 * the elements of the original array with the elements of another RichArray.
	 * The arrays must have the same number of rows for successful joining.
	 *
	 * @param array The RichArray to join with.
	 * @return A new RichArray resulting from the horizontal concatenation.
	 * @throws IllegalArgumentException If the arrays have a different number of
	 *                                  rows
	 */
	public RichArray join(RichArray array) {
		try {
			// Handle case where the arrays don't have the same number of rows or if given
			// RichArray is null
			if (array == null) {
				throw new IllegalArgumentException("Can't join arrays! The given RichArray is null.");
			} else if (this.array.length != array.array.length) {
				throw new IllegalArgumentException(
						"Can't join arrays! The arrays must have the same number of rows to be joined.");
			} else {
				// Create a new array for joining with increased width
				int[][] tempArr = new int[this.array.length][this.array[0].length + array.array[0].length];

				// Cycle through the new array and fill it with elements from both arrays
				for (int i = 0; i < tempArr.length; i++) {
					for (int j = 0; j < tempArr[0].length; j++) {
						if (j < this.array[0].length) {
							tempArr[i][j] = this.array[i][j];
						} else {
							tempArr[i][j] = array.array[i][j - this.array[0].length];
						}
					}
				}

				// Create and return a new RichArray with elements joined horizontally
				return new RichArray(tempArr);
			}
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			return new RichArray(new int[][] { { 0 } });
		}

	}

	/**
	 * Stack the RichArray on top of another RichArray vertically.
	 *
	 * This method creates and returns a new RichArray by vertically stacking the
	 * elements of the original array on top of the elements of another RichArray.
	 * The arrays must have the same number of columns for successful stacking.
	 *
	 * @param array The RichArray to stack on top.
	 * @return A new RichArray resulting from the vertical stacking.
	 * @throws IllegalArgumentException If the arrays have a different number of
	 *                                  columns or if given RichArray is null
	 */
	public RichArray stack(RichArray array) {
		try {
			// Check if the arrays have the same number of columns or given RichArray is
			// null
			if (array == null) {
				throw new IllegalArgumentException("Can't stack arrays! Given RichArray is null.");
			} else if (this.array[0].length != array.array[0].length) {
				throw new IllegalArgumentException(
						"Can't stack arrays! The arrays must have the same number of columns.");
			} else {
				// Create a new array for stacking with increased height
				int[][] tempArr = new int[this.array.length + array.array.length][this.array[0].length];

				// Cycle through the new array and fill it with elements from both arrays
				for (int i = 0; i < tempArr.length; i++) {
					for (int j = 0; j < tempArr[0].length; j++) {
						if (i < this.array.length) {
							tempArr[i][j] = this.array[i][j];
						} else {
							tempArr[i][j] = array.array[i - this.array.length][j];
						}
					}
				}

				// Create and return a new RichArray with elements stacked vertically
				return new RichArray(tempArr);
			}

		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			return new RichArray(new int[][] { { 0 } });
		}

	}

	/**
	 * Create a new RichArray by slicing a portion of the original array.
	 *
	 * This method creates and returns a new RichArray by selecting a rectangular
	 * portion of the original array specified by the provided row and column
	 * indices.
	 *
	 * @param firstRow    The index of the first row in the slice (inclusive).
	 * @param lastRow     The index of the last row in the slice (inclusive).
	 * @param firstColumn The index of the first column in the slice (inclusive).
	 * @param lastColumn  The index of the last column in the slice (inclusive).
	 * @return A new RichArray representing the sliced portion of the original
	 *         array.
	 * @throws IllegalArgumentException If the given row/column values are out of
	 *                                  array's bounds.
	 */
	public RichArray slice(int firstRow, int lastRow, int firstColumn, int lastColumn) {
		try {
			// Check if row/column indexes are out of bounds
			if (!checkValuesSlice(firstRow, lastRow, firstColumn, lastColumn)) {
				throw new IllegalArgumentException(
						"Can't slide portion of array! Value(s) given are out of array's bounds.");
			} else {
				// Create a new array for the slice with the specified dimensions
				int[][] tempArr = new int[lastRow - firstRow + 1][lastColumn - firstColumn + 1];

				// Indices to keep track of the position in the original array and the slice
				int rowIndex = 0;
				int colIndex = 0;

				// Cycle through the original array and fill the slice with selected elements
				for (int i = firstRow; i <= lastRow; i++) {
					for (int j = firstColumn; j <= lastColumn; j++) {
						tempArr[rowIndex][colIndex] = array[i][j];
						colIndex++;
					}
					rowIndex++;
					colIndex = 0;
				}

				// Create and return a new RichArray representing the sliced portion
				return new RichArray(tempArr);
			}

		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			return new RichArray(new int[][] { { 0 } });
		}

	}

	/**
	 * Helper method for {@link slice}.
	 * 
	 * Checks if the specified values are valid for array indices.
	 * 
	 * @param firstRow    The index of the first row in the slice (inclusive).
	 * @param lastRow     The index of the last row in the slice (inclusive).
	 * @param firstColumn The index of the first column in the slice (inclusive).
	 * @param lastColumn  The index of the last column in the slice (inclusive).
	 * @return True if values are non-negative and within the bounds of the array,
	 *         false otherwise.
	 */
	public boolean checkValuesSlice(int firstRow, int lastRow, int firstColumn, int lastColumn) {
		if (firstRow < 0 || lastRow < 0 || firstColumn < 0 || lastColumn < 0) {
			return false;
		} else if ((firstRow > array.length || lastRow > array.length)
				|| (firstColumn > array[0].length || lastColumn > array[0].length)) {
			return false;
		}

		return true;
	}

	/**
	 * Create a new RichArray by replacing a portion of the original array with
	 * another array.
	 *
	 * This method creates and returns a new RichArray by replacing a rectangular
	 * portion of the original array with the elements of another RichArray. The
	 * replacement starts at the specified row and column indices.
	 *
	 * @param array  The RichArray to use for replacement.
	 * @param row    The starting row index for replacement.
	 * @param column The starting column index for replacement.
	 * @return A new RichArray with the specified portion replaced by the given
	 *         array.
	 * @throws IllegalArgumentException If the replacement goes out of bounds and if
	 *                                  given RichArray is null
	 */
	public RichArray replace(RichArray array, int row, int column) {
		try {
			// Check if the replacement goes out of bounds or if given RichArray is null
			if (array == null) {
				throw new IllegalArgumentException("Can't replace array! Given RichArray is null.");
			} else if (row < 0 || column < 0 || row + array.array.length > this.array.length
					|| column + array.array[0].length > this.array[0].length) {
				throw new IllegalArgumentException("Can't replace array! Out of bounds.");
			} else {
				// Indices to keep track of the position in the original array and the
				// replacement array
				int rowIndex = 0;
				int colIndex = 0;

				// Create a copy of the original array for modification
				int[][] tempArr = this.array;

				// Cycle through the replacement array and update the original array
				for (int i = row; i < row + array.array.length; i++) {
					for (int j = column; j < column + array.array[0].length; j++) {
						tempArr[i][j] = array.array[rowIndex][colIndex];
						colIndex++;
					}
					rowIndex++;
					colIndex = 0;
				}

				// Create and return a new RichArray with the specified portion replaced
				return new RichArray(tempArr);
			}

		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			return new RichArray(new int[][] { { 0 } });
		}

	}

	/**
	 * Converts the 2D array into a formatted string representation.
	 *
	 * This method iterates through each element in the 2D array and constructs a
	 * string representation by concatenating the elements with spaces between them
	 * and newline characters at the end of each row.
	 *
	 * @return A formatted string representing the 2D array.
	 */
	public String toString() {
		// Initialize the output string
		String output = "";

		// Iterate through each row
		for (int i = 0; i < array.length; i++) {
			// Iterate through each column in the current row
			for (int j = 0; j < array[0].length; j++) {
				// Add the element with a blank space after it
				output += array[i][j] + " ";
			}
			// After done adding elements of row i, enter a newline character to prin the
			// next row
			output += "\n";
		}

		// Return the formatted string representation of the 2D array
		return output;
	}

}
