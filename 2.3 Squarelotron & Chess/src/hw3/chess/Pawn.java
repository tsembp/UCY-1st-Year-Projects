package hw3.chess;

import java.util.ArrayList;

/**
 * Represents a Pawn piece in a chess game.
 * Pawns move forward one square, but on their first move, they have the option to move two squares.
 * They capture diagonally and cannot move backward.
 * 
 * @author Panagiotis Tsembekis
 * @version 1.0
 * @since 30/03/24
 */
public class Pawn extends Piece {

    /**
     * Constructs a Pawn with the specified color.
     *
     * @param c The color of the Pawn, either BLACK or WHITE.
     */
	public Pawn(Color c) {
		super(c);
	}

    /**
     * Returns the algebraic name of the Pawn, which is "P".
     *
     * @return The algebraic name of the Pawn.
     */
	@Override
	public String algebraicName() {
		return "P";
	}

    /**
     * Calculates all possible moves for the Pawn from a given square on the board.
     * This method considers the Pawn's movement rules, including its unique initial two-square move.
     * The calculation does not account for captures, en passant, or promotion.
     *
     * @param square The square from which to calculate the Pawn's possible moves.
     * @return An array of Squares representing all possible moves for the Pawn from the given square.
     */
	@Override
	public Square[] movesFrom(Square square) {
		char col = square.getCol();
		int row = square.getRow();
		ArrayList<Square> possibleMoves = new ArrayList<>();
		
		// For the pawn we have to determine its color to see if it can move forward 
		// (white pawn) or backwards (black pawn), direction = 1 means the pawn
		// can move forward (white) and -1 means backwards (black)
		int direction = (this.getColor() == Color.WHITE) ? 1 : -1;
		
		// Add possible moves for one move forwards/backwards
		int newRow = row + direction;
		if(newRow >= 1 && newRow <= 8) {
			possibleMoves.add(new Square(col + "" + newRow));
		}
		
		// If pawn is on starting position it can do 2 steps forwards/backwards
		if((this.getColor() == Color.WHITE && row == 2) || (this.getColor() == Color.BLACK && row == 7)) {
			newRow += direction;
			possibleMoves.add(new Square(col + "" + newRow));
		}
		
		return possibleMoves.toArray(new Square[0]);
	}
	
    /**
     * Returns the Forsyth-Edwards Notation (FEN) name of the Pawn.
     * Uppercase "P" for White, lowercase "p" for Black.
     *
     * @return The FEN name of the Pawn based on its color.
     */
	@Override
	public String fenName() {
		return getColor() == Color.WHITE ? "P" : "p";
	}
	
}
