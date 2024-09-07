package hw3.chess;

/**
 * Represents an abstract chess piece on a chessboard. This class serves as a
 * base for all specific types of chess pieces.
 * 
 * @author Panagiotis Tsembekis
 * @version 1.0
 * @since 30/03/24
 */
public abstract class Piece {

	/** The color of the chess piece. */
	private final Color color;

	/**
	 * Constructs a Piece with a specified color.
	 * 
	 * @param c The color of the piece, either BLACK or WHITE.
	 */
	public Piece(Color c) {
		this.color = c;
	}

	/**
	 * Returns the color of the chess piece.
	 * 
	 * @return The color of this piece.
	 */
	public Color getColor() {
		return this.color;
	}

	/**
	 * Returns the algebraic name of the chess piece.
	 * 
	 * @return The algebraic name of this piece.
	 */
	public abstract String algebraicName();

	/**
	 * Calculates and returns all possible moves for this piece from a given square.
	 * The method does not consider the current game state; it only calculates moves
	 * based on how the piece moves in general, not accounting for other pieces on
	 * the board.
	 * 
	 * @param square The starting square from which to calculate possible moves.
	 * @return An array of Squares representing possible moves from the given
	 *         square.
	 */
	public abstract Square[] movesFrom(Square square);

	/**
	 * Returns the FEN name of the chess piece.
	 * 
	 * @return The FEN name of this piece.
	 */
	public abstract String fenName();

}
