/*
* Author: Panagiotis Tsembekis
* Written: 07/11/2023
* Last updated: 25/11/2023
*
* Compilation: javac -cp your\library\path\stdlib.jar Board.java UserChoice.java Sudoku.java
* Execution: java -cp .;your\library\path\stdlib.jar Sudoku <N> <game-file>
*
* This class is responsible for handiling the user's input. The Sudoku class uses an UserChoice object that has 3 characteristics, that is row,
* col and val. These are used by the Sudoku Class to locate where the in the Sudoku tableau user wishes to enter the number "val". It does that by
* using a setter method and three getter methods.
*
*/

public class UserChoice {
	
	private int row;
	private int col;
	private int val;
	
	public UserChoice() { // default constructor sets all values to 0
		row = 0;
		col = 0;
		val = 0;
	}
		
	public void setChoice(int row, int col, int val) { // setter method that sets the values given to it, to the characteristics of the object
		this.row = row;
		this.col = col;
		this.val = val;
	}
	
	public int getRow() { // getter method to return the object's row
		return row;
	}
	
	public int getColumn() { // getter method to return the object's column
		return col;
	}
	
	public int getValue() { // getter method to return the object's box
		return val;
	}
	
}
