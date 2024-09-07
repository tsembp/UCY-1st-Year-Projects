package hw3.chess;

/**
 * This class represents an unchecked exception that is thrown to indicate that
 * a specified square is not valid on a chessboard.
 *
 * Using an unchecked exception here is appropriate because the validity of a
 * square mostly depends on runtime conditions, for example user input. It
 * simplifies error handling by not forcing the calling code to catch it or
 * declare it in the method signature, assuming that such a scenario (invalid
 * square) is either a programmer error or an exceptional condition that the
 * application might not want to explicitly handle.
 *
 * @author Panagiotis Tsembekis
 * @version 1.0
 * @since 30/03/24
 */
public class InvalidSquareException extends RuntimeException {

	/**
	 * Constructs an InvalidSquareException with the specified detail message.
	 *
	 * @param message the detail message. The detail message is saved for later
	 *                retrieval by the {@link #getMessage()} method.
	 */
	public InvalidSquareException(String message) {
		super(message);
	}

}
