package hw1;

/**
 * The NegadecimalNumber class represents a Negadecimal number and provides
 * methods for accessing and converting the Negadecimal number.
 * 
 * @author Panagiotis Tsembekis
 * @version 1.0
 * @since 14/02/24
 */

public class NegadecimalNumber {

	private int negadecimalNum;

	/**
	 * Constructs a NegadecimalNumber with the specified integer value.
	 *
	 * @param negadecimalNum The integer value representing the Negadecimal number.
	 */
	public NegadecimalNumber(int negadecimalNum) {
		this.negadecimalNum = negadecimalNum;
	}

	/**
	 * Gets the integer value of the Negadecimal number.
	 *
	 * @return The integer value of the Negadecimal number.
	 */
	public int getNum() {
		return negadecimalNum;
	}

	/**
	 * Returns the string representation of the Negadecimal number.
	 *
	 * @return The string representation of the Negadecimal number.
	 */
	public String toString() {
		return Integer.toString(this.negadecimalNum);
	}

}
