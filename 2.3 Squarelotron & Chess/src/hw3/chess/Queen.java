package hw3.chess;

import java.util.ArrayList;

/**
 * Represents a Queen piece in a chess game. The Queen combines the power of a
 * Rook and a Bishop and can move any number of squares along a rank, file, or
 * diagonal, but cannot leap over other pieces.
 * 
 * @author Panagiotis Tsembekis
 * @version 1.2
 * @since 31/03/24
 */
public class Queen extends Piece {

    /**
     * Constructs a Queen with the specified color.
     * 
     * @param c The color of the Queen, either Color.BLACK or Color.WHITE.
     */
	public Queen(Color c) {
		super(c);
	}
	
    /**
     * Returns the algebraic name of the Queen.
     * 
     * @return The algebraic name of the Queen, which is "Q".
     */
	@Override
	public String algebraicName() {
		return "Q";
	}
	
    /**
     * Calculates all possible moves for the Queen from the given square,
     * considering the Queen's ability to move any number of squares along a rank, file, or diagonal.
     * 
     * @param square The square from which to calculate the Queen's possible moves.
     * @return An array of Squares representing all possible moves for the Queen from the given square.
     */
	@Override
	public Square[] movesFrom(Square square) {
		String pos = square.toString();
		char col = pos.charAt(0);
		char row = pos.charAt(1);
		ArrayList<Square> possibleMoves = new ArrayList<>();
		
		// Calculate diagonal moves similarly to Bishop
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
		
		// Calculate possible horizontal and vertical moves, same as Rook
		for(char r = '1'; r <= '8'; r++) {
			Square newSq = new Square(col, r); // create the new square, if it's invalid the constructor will
												// throw an exception
			// if we encounter the same row as the current or same square is already contained in possibleMoves, ignore it
			if(r == row || possibleMoves.contains(newSq)) continue;
			possibleMoves.add(newSq);
		}
		
		for(char c = 'a'; c <= 'h'; c++) {
			Square newSq = new Square(c, row); // create the new square, if it's invalid the constructor will
												// throw an exception
			// if we encounter the same col as the current or same square is already contained in possibleMoves, ignore it
			if(c == col || possibleMoves.contains(newSq)) continue;
			possibleMoves.add(newSq);
		}
		
		return possibleMoves.toArray(new Square[0]);
	}
	
    /**
     * Returns the FEN name of the Queen.
     * Uppercase "Q" for White, lowercase "q" for Black.
     * 
     * @return The FEN name of the Queen based on its color.
     */
	@Override
	public String fenName() {
		return getColor() == Color.WHITE ? "Q" : "q";
	}
	
}
