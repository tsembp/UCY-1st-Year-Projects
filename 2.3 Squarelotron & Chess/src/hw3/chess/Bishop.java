package hw3.chess;

import java.util.ArrayList;

/**
 * Represents a Bishop chess piece. The Bishop can move diagonally any number of squares.
 * It cannot leap over other pieces.
 * 
 * @author Panagiotis Tsembekis
 * @version 1.1
 * @since 30/03/24
 */
public class Bishop extends Piece {
	
    /**
     * Constructs a Bishop piece with the specified color.
     *
     * @param c The color of the Bishop, either Color.BLACK or Color.WHITE.
     */
	public Bishop(Color c) {
		super(c);
	}
	
    /**
     * Returns the algebraic name of the Bishop, which is "B".
     *
     * @return The algebraic name of the Bishop.
     */
	@Override
	public String algebraicName() {
		return "B";
	}
	
    /**
     * Calculates all possible moves for the Bishop from a given square on the board.
     * Moves are calculated based on the Bishop's ability to move diagonally in any direction.
     * This calculation does not take into account other pieces that may block its path.
     *
     * @param square The square from which to calculate the Bishop's possible moves.
     * @return An array of Squares representing all possible moves for the Bishop from the given square.
     */
	@Override
	public Square[] movesFrom(Square square) {
		String pos = square.toString();
		char col = pos.charAt(0);
		char row = pos.charAt(1);
		ArrayList<Square> possibleMoves = new ArrayList<>();

		int[] offsets = { -1, 1 };

		for (int rowOffset : offsets) {
			for (int colOffset : offsets) {
				int tempRowOffset = rowOffset;
				int tempColOffset = colOffset;
				while (true) {
					char newRow = (char) (row + tempRowOffset);
					char newCol = (char) (col + tempColOffset);
					if (newRow >= '1' && newRow <= '8' && newCol >= 'a' && newCol <= 'h') {
						// if new row and new column are valid, create a new Square object and add it to
						// the array list
						possibleMoves.add(new Square(newCol, newRow));
					} else { // if new square's row and (or) column is invalid, break the loop
						break;
					}
					// move further along the diagonal
					tempRowOffset += rowOffset;
					tempColOffset += colOffset;
				}
			}
		}

		return possibleMoves.toArray(new Square[0]);
	}
	
    /**
     * Returns the FEN name of the Bishop.
     * Uppercase "B" for White, lowercase "b" for Black.
     *
     * @return The FEN name of the Bishop based on its color.
     */
	@Override
	public String fenName() {
		return getColor() == Color.WHITE ? "B" : "b";
	}
	
}
