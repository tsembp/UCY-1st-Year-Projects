package hw1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * This class serves as the main class for testing the
 * {@linkplain NegadecimalCalculator}. It gets an array of expressions read from a
 * file in the ExpressionReader class and proceeds to print the expressions with
 * their result.
 * 
 * @author Panagiotis Tsembekis
 * @version 1.0
 * @since 14/02/24
 */
public class TestNegadecimalCalculator {

	/**
	 * The main method to execute the NegadecimalCalculator testing.
	 *
	 * @param args The command-line arguments (not used in this application).
	 */
	public static void main(String[] args) {

		// Read expressions from the input file
		String[] expressions = readExpressionsFromFile("input.txt");

		// Process each expression and print the result for valid expressions
		for (String expression : expressions) {
			if (!expression.equals("Wrong Expression") && !expression.equals("Division by zero")) {
				// Split the expression into parts and convert them to appropriate types
				String[] parts = expression.split(" ");
				int num1 = Integer.parseInt(parts[0]);
				int num2 = Integer.parseInt(parts[2]);
				char op = parts[1].charAt(0);

				// Create a NegadecimalCalculator object and calculate the expression
				NegadecimalCalculator calculator = new NegadecimalCalculator(num1, num2, op);
				System.out.println(expression + " = " + calculator.calculateExpression());
			} else {
				// Print the error message for invalid expressions
				System.out.println(expression);
			}
		}
	}

	/**
	 * Reads arithmetic expressions from a file and returns an array of expressions.
	 *
	 * @param fileName The name of the file containing arithmetic expressions.
	 * @return An array of strings representing arithmetic expressions.
	 */
	public static String[] readExpressionsFromFile(String fileName) {
		try (BufferedReader reader1 = new BufferedReader(new FileReader(fileName))) {
			String line;
			int cnt = 0;

			// Count the number of lines in the file
			while ((line = reader1.readLine()) != null) {
				cnt++;
			}

			reader1.close();
			String[] expressions = new String[cnt]; // String array to store all the expressions inside

			// Read expressions and process them
			BufferedReader reader2 = new BufferedReader(new FileReader(fileName));
			int i = 0;
			while ((line = reader2.readLine()) != null) {
				expressions[i++] = processExpression(line);
			}

			reader2.close();
			return expressions;

		} catch (IOException e) {
			e.printStackTrace();
			return new String[0];
		}
	}

	/**
	 * Processes each arithmetic expression to ensure it is a valid expression.
	 *
	 * @param expression The arithmetic expression to be processed.
	 * @return The processed expression if valid, otherwise returns "Wrong
	 *         Expression!".
	 */
	public static String processExpression(String expression) {
		String[] parts = expression.split(" ");
		if (parts.length != 3) {
			return "Wrong Expression";
		} else if (!(checkDigits(parts[0]) && checkDigits(parts[2]) && checkOp(parts[1]))) {
			return "Wrong Expression";
		} else if (parts[1].equals("/") && Integer.parseInt(parts[2]) == 0) {
			return "Division by zero";
		}

		return expression;
	}

	/**
	 * Checks if the provided string represents a valid arithmetic operator.
	 *
	 * @param op The string representing the arithmetic operator.
	 * @return true if the operator is valid, false otherwise.
	 */
	public static boolean checkOp(String op) {
		if (!(op.equals("+") || op.equals("-") || op.equals("*") || op.equals("/"))) {
			return false;
		}
		return true;
	}

	/**
	 * Checks if the provided string consists only of digits.
	 *
	 * @param exp The string to be checked for digits.
	 * @return true if the string consists only of digits, false otherwise.
	 */
	public static boolean checkDigits(String exp) {
		for (int i = 0; i < exp.length(); i++) {
			if (!Character.isDigit(exp.charAt(i))) {
				return false;
			}
		}
		return true;
	}
}
