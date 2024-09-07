package hw3.chess;

import java.util.ArrayList;

/**
 * Represents a Rook piece in a chess game.
 * The Rook can move any number of squares along a rank or file, but cannot leap over other pieces.
 * 
 * @author Panagiotis Tsembekis
 * @version 1.0
 * @since 30/03/24
 */
public class Rook extends Piece {

    /**
     * Constructs a Rook with the specified color.
     *
     * @param c The color of the Rook, either BLACK or WHITE.
     */
	public Rook(Color c) {
		super(c);
	}

    /**
     * Returns the algebraic name of the Rook, which is "R".
     *
     * @return The algebraic name of the Rook.
     */
	@Override
	public String algebraicName() {
		return "R";
	}

    /**
     * Calculates all possible moves for the Rook from a given square on the board.
     * Moves are calculated based on the Rook's ability to move horizontally and vertically
     * across the board. This calculation does not account for blocking pieces.
     *
     * @param square The square from which to calculate the Rook's possible moves.
     * @return An array of Squares representing all possible moves for the Rook from the given square.
     */
	@Override
	public Square[] movesFrom(Square square) {
		String pos = square.toString();
		char col = pos.charAt(0);
		char row = pos.charAt(1);
		ArrayList<Square> possibleMoves = new ArrayList<>();

		for(char r = '1'; r <= '8'; r++) {
			if(r == row) continue;
			possibleMoves.add(new Square(col, r));
		}
		
		for(char c = 'a'; c <= 'h'; c++) {
			if(c == col) continue;
			possibleMoves.add(new Square(c, row));
		}

		return possibleMoves.toArray(new Square[0]);
	}

    /**
     * Returns the FEN name of the Rook.
     * Uppercase "R" for White, lowercase "r" for Black.
     *
     * @return The FEN name of the Rook based on its color.
     */
	@Override
	public String fenName() {
		return getColor() == Color.WHITE ? "R" : "r";
	}

}
