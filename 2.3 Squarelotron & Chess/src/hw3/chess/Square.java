package hw3.chess;

import java.util.Objects;

/**
 * Represents a square on a chessboard with specific row and column.
 * 
 * @author Panagiotis Tsembekis
 * @version 1.1
 * @since 30/03/24
 */
public class Square {
	
	 /** The row of the square ('1' through '8'). */
	private final char row;
	
	/** The column of the square ('a' through 'h'). */
	private final char col;
	
	 /**
     * Constructs a Square with specified column and row.
     * 
     * @param col The column of the square ('a' through 'h').
     * @param row The row of the square ('1' through '8').
     */
	public Square(char col, char row) {
		this.row = row; // number
		this.col = col; // letter
		validateSquare();
	}
	
    /**
     * Constructs a Square from a string representation (e.g., "e4").
     * 
     * @param name The string representation of the square.
     */
	public Square(String name) {
		this(name.charAt(0), name.charAt(1));
	}
	
    /**
     * Returns the numeric value of the square's row.
     * 
     * @return The numeric row value.
     */
	public int getRow() {
		return Character.getNumericValue(row);
	}
	
    /**
     * Returns the character representing the square's column.
     * 
     * @return The column character.
     */
	public char getCol() {
		return col;
	}
	
    /**
     * Validates the square to ensure it's within a chessboard's bounds.
     * @throws InvalidSquareException if the square is outside the valid range.
     */
	private void validateSquare() throws InvalidSquareException {
		if(row < '1' || row > '8' || col < 'a' || col > 'h')
			throw new InvalidSquareException("Invalid square: " + toString());
	}
	
    /**
     * Returns a string representation of the square in algebraic notation.
     * 
     * @return The square in algebraic notation (e.g., "e4").
     */
	@Override
	public String toString() {
		return "" + col + row;
	}
	
    /**
     * Checks if this square is equal to another object.
     * 
     * @param o The object to compare with this square.
     * @return true if the given object is also a Square with the same row and column.
     */
	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;
		Square other = (Square) o;
		return this.row == other.row && this.col == other.col;
	}
	
    /**
     * Returns a hash code value for the square.
     * (was used for JUnit testing)
     * 
     * @return A hash code value for this square.
     */
    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }
	
}
