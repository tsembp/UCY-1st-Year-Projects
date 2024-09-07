package hw3.chess;

import java.util.ArrayList;

/**
 * Represents a King piece in a game of chess.
 * The King can move exactly one square horizontally, vertically, or diagonally.
 * 
 * @author Panagiotis Tsembekis
 * @version 1.2
 * @since 31/03/24
 */
public class King extends Piece {
	
    /**
     * Constructs a King with the specified color.
     * 
     * @param c The color of the King, either BLACK or WHITE.
     */
	public King(Color c) {
		super(c);
	}
	
    /**
     * Returns the algebraic name of the King, which is "K".
     * 
     * @return A string representing the algebraic name of the King.
     */
	@Override
	public String algebraicName() {
		return "K";
	}
	
    /**
     * Calculates all possible moves for the King from a given square,
     * considering only the board limits and not other pieces.
     * 
     * @param square The starting square to calculate the King's possible moves.
     * @return An array of Squares representing all possible moves for the King.
     */
	@Override
	public Square[] movesFrom(Square square) {
		char col = square.getCol();
		int row = square.getRow();
		ArrayList<Square> possibleMoves = new ArrayList<>();
		
		for(int r = -1; r <= 1; r++) { // King can move one place in all directions
			for(int c = -1; c <= 1; c++) {
				if(r == 0 && c == 0) continue;
				int newRow = row + r;
				char newCol = (char)(col + c);
				if(newRow >= 1 && newRow <= 8 && newCol >= 'a' && newCol <= 'h')
					possibleMoves.add(new Square(newCol + "" + newRow));
			}
		}
		
		return possibleMoves.toArray(new Square[0]);
	}
	
    /**
     * Returns the FEN name of the King.
     * Uppercase "K" for a white king, lowercase "k" for a black king.
     * 
     * @return A string representing the King's FEN name based on its color.
     */
	@Override
	public String fenName() {
		return getColor() == Color.WHITE ? "K" : "k";
	}
	
}
