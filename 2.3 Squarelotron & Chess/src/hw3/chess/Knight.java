package hw3.chess;

import java.util.ArrayList;

/**
 * Represents a Knight piece in a chess game.
 * The Knight moves in an L shape: two squares in one direction and then one square perpendicular to that direction.
 * 
 * @author Panagiotis Tsembekis
 * @version 1.2
 * @since 31/03/24
 */
public class Knight extends Piece {

    /**
     * Constructs a Knight with the specified color.
     *
     * @param c The color of the Knight, either BLACK or WHITE.
     */
	public Knight(Color c) {
		super(c);
	}

    /**
     * Returns the algebraic name of the Knight, which is "N".
     *
     * @return The algebraic name of the Knight.
     */
	@Override
	public String algebraicName() {
		return "N";
	}

    /**
     * Calculates all possible moves for the Knight from a given square on the board.
     * Moves are determined based on the Knight's unique movement abilities, taking into account the board's boundaries.
     *
     * @param square The square from which to calculate the Knight's possible moves.
     * @return An array of Squares representing all possible moves for the Knight from the given square.
     */
	@Override
	public Square[] movesFrom(Square square) {
		String pos = square.toString();
		char col = pos.charAt(0);
		char row = pos.charAt(1);
		ArrayList<Square> possibleMoves = new ArrayList<>();
		
		int[][] moves = { // Knight moves in a Γ shape, two/one or one/two
				{2,1},{2,-1},{-2,1},{-2,-1}, // two rows, one column moves
				{1,2},{1,-2},{-1,2},{-1,-2} // one row, two columns moves
		};
		
		// Add the new squares reachable
		for(int[] move : moves) {
			char newRow = (char)(row + move[0]);
			char newCol = (char)(col + move[1]);
			if(newRow >= '1' && newRow <= '8' && newCol >= 'a' && newCol <= 'h')
				possibleMoves.add(new Square(newCol, newRow));
		}
		
		return possibleMoves.toArray(new Square[0]);
	}
	
    /**
     * Returns the FEN name of the Knight.
     * Uppercase "N" for White, lowercase "n" for Black.
     *
     * @return The FEN name of the Knight based on its color.
     */
	@Override
	public String fenName() {
		return getColor() == Color.WHITE ? "N" : "n";
	}
	
}
