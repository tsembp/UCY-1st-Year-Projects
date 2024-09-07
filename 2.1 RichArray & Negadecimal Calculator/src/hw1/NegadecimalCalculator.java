package hw1;

/**
 * The NegadecimalCalculator class represents a calculator for performing
 * arithmetic operations on Negadecimal numbers. It includes methods to
 * calculate expressions, convert Negadecimal numbers to decimal base, and
 * convert integers to Negadecimal base.
 * 
 * @author Panagiotis Tsembekis
 * @version 1.0
 * @since 14/02/24
 */

public class NegadecimalCalculator {

	private NegadecimalNumber num1, num2;
	private char op;

	/**
	 * Constructs a NegadecimalCalculator with two Negadecimal numbers and an
	 * operator.
	 *
	 * @param num1 The first Negadecimal number.
	 * @param num2 The second Negadecimal number.
	 * @param op   The operator for the arithmetic operation.
	 */
	public NegadecimalCalculator(int num1, int num2, char op) {
		this.num1 = new NegadecimalNumber(num1);
		this.num2 = new NegadecimalNumber(num2);
		this.op = op;
	}

	/**
	 * Calculates the result of the arithmetic expression based on the provided
	 * Negadecimal numbers and operator.
	 *
	 * @return The result of the arithmetic expression in Negadecimal
	 *         representation.
	 */
	public String calculateExpression() {
		int dec1 = toDecimalBase(num1);
		int dec2 = toDecimalBase(num2);
		int result = 0;
		switch (op) {
		case '+':
			result = dec1 + dec2;
			break;
		case '-':
			result = dec1 - dec2;
			break;
		case '*':
			result = dec1 * dec2;
			break;
		case '/':
			result = dec1 / dec2;
			break;
		}

		return toNegativeBase(result, -10);
	}

	/**
	 * Converts a NegadecimalNumber to its decimal base representation.
	 *
	 * @param num The NegadecimalNumber to be converted.
	 * @return The decimal base representation of the NegadecimalNumber.
	 */
	public int toDecimalBase(NegadecimalNumber num) {
		int sum = 0;
		int number = num.getNum();
		int position = 0;
		while (number != 0) {
			int digit = number % 10;
			sum += digit * Math.pow(-10, position);
			position++;
			number /= 10;
		}

		return sum;
	}

	/**
	 * Converts an decimal integer to its Negadecimal base representation.
	 *
	 * @param integer The integer to be converted.
	 * @param base    The base for the Negadecimal representation (usually -10).
	 * @return The Negadecimal base representation of the integer.
	 */
	public String toNegativeBase(int integer, int base) {
		String result = "";
		int number = integer;
		while (number != 0) {
			int i = number % base;
			number /= base;
			if (i < 0) {
				i += Math.abs(base);
				number++;
			}
			result = i + result;
		}
		return result;
	}

}
