/**
* Author: Panagiotis Tsembekis
* Written: 12/10/2023
* Last updated: 30/10/2023
*
* Compilation: javac -cp your/library/path/stdlib.jar NPuzzle.java NPuzzleLib.java
* Execution: java -cp .;your/library/path/stdlib.jar NPuzzle <input for N> <input for d>
*
* This program is a helper class that stores all the methods that the Client class calls
* to play the puzzle game. It holds the methods to print out text-menus, receive the user's
* next moves, check the validity of them and also check if the puzzle is solved or not. Moreover,
* it holds methods that are used to play the game automatically by selecting randomly a
* move that can be implemented (in bounds move). Lastly, the class has multiple
* methods that are used to display the game with graphics using StdDraw.
*
*/

import java.awt.Color;
import java.awt.Font;

public class NPuzzleLib {

	private static int cntMoves = 0;

	// Method that prints the menu for the play method
	public static int playTypeMenu() {
		System.out.println("+-------------------------+");
		System.out.println("|1. Interactive play      |");
		System.out.println("|2. Automatic play        |");
		System.out.println("|3. Exit                  |");
		System.out.println("+-------------------------+");
		System.out.print("Choose option:");
		int playMethodIndex = StdIn.readInt(); // read an integer the corresponds to the play method (interactive/automatic/exit)
		System.out.println("*******************************");

		return playMethodIndex;
	}

	// Method that prints the menu for the difficulty (Interactive Play)
	public static int difficultyMenu() {
		System.out.print("Give level of difficulty:");
		int diff = StdIn.readInt(); // read an integer that corresponds to the difficulty level for the interactive play method
		System.out.println("*******************************");

		return diff;
	}

	// Method that reads the User's next move
	public static String getUserCommand(int cnt) {
		String command; // We use the cnt variable to keep track of how many moves the user did to present the correct message
		String prefix;
		if (cnt == 0) {
			prefix = "st";
		} else if (cnt == 1) {
			prefix = "nd";
		} else if (cnt == 2) {
			prefix = "rd";
		} else {
			prefix = "th";
		}
		System.out.print("Give " + (cnt + 1) + prefix + " Move:");
		command = StdIn.readString();

		return command;
	}

	// Method that checks if the given move by the user is valid (l,r,u,d)
	public static boolean isValidMove(String move) {
		if (move.charAt(0) == 'l' || move.charAt(0) == 'r' || move.charAt(0) == 'u' || move.charAt(0) == 'd') {
			return true; 	// We check whether the user gave a correct input [l,r,u,d]
		} 					// the case where input = "e" is handled by the play method
		else {
			System.out.println("Wrong input, expected input is [l,u,r,d]"); // if the user gave an invalid move we present an error message
			return false;
		}
	}

	// Method that prints the correct messages when the puzzle is solved
	public static void solvedMessage(int moves, int[][] table) {
		displayPuzzle(table);
		System.out.println("Congratulations, puzzle solved!!");
		System.out.println("Stats:" + "\n- Moves: " + moves);
	}

	// Method that prints the correct messages when the puzzle is not solved
	public static void notSolvedMessage(int moves) {
		if (moves > 0) { // if the user did some moved but exited before the puzzle was solved
			System.out.println("Thanks for playing!");
			System.out.println(
					"Stats:\n- Times solved: 0\n- Moves: " + moves);
		} else {
			System.out.println("Not tried.");
		}
	}

	// Method that checks whether the given puzzle is solved or not
	public static boolean isSolution(int table[][]) {
		int expValue = 1, col = table[0].length;
		for (int i = 0; i < table.length; i++) {
			for (int j = 0; j < col; j++) {
				if (table[i][j] != expValue) { 	// if we find at least one number that is not correctly placed
					return false; 				// inside the table, we return false
				}
				expValue++;
			}
			if (i == table.length - 2) {	// if the loop reaches the last row we check until the
				col--; 						// 2nd-to-last column and handle 0 separately
			} 
		}
		if (table[table.length - 1][table[0].length - 1] != 0) {
			return false; // if the number at the bottom right corner is not 0 then the puzzle is not solved
		}
		return true;
	}

	// Method that prints the correct out of bounds message
	public static void outOfBoundsMessage(String cmd) {
		if (cmd.equals("l")) { // if the user's move is out of bounds we present the correct message that corresponds to the move 
			System.out.println("You are not allowed to go left");
		} else if (cmd.equals("r")) {
			System.out.println("You are not allowed to go right");
		} else if (cmd.equals("u")) {
			System.out.println("You are not allowed to go up");
		} else if (cmd.equals("d")) {
			System.out.println("You are not allowed to go down");
		}
	}

	// Method that checks whether a move can be implemented
	public static boolean checkBounds(int size, String command, int row, int col) {
		int index;
		if (command.equals("l")) {
			index = col - 1;
		} else if (command.equals("r")) {
			index = col + 1;
		} else if (command.equals("u")) {
			index = row - 1;
		} else {
			index = row + 1;
		}

		if (index < 0 || index >= size) { // if our row or column is a negative number or greater than the table's
			return false;				  // number or rows/columns , then the move is out of bounds and can't be implemented
		}

		return true;
	}

	// Method that initializes the puzzle as solved
	public static int[][] initializePuzzle(int n) {
		int value = 1;
		int table[][] = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n && value < Math.pow(n, 2); j++) { // Ensure that the number entered is lower than N^2
				table[i][j] = value;
				value++;
			}
		}
		return table;
	}

	// Method that shuffles the puzzle
	public static int[][] shufflePuzzle(int table[][], int dif) {
		int size = table.length;
		int zeroPos[] = new int[2];
		zeroPos[0] = size - 1; // because the puzzle is in its solved form we can
		zeroPos[1] = size - 1; // manually set 0's position manually [0]=row [1]=column
		for (int i = 0; i < dif; i++) {
			String posMoves = "";
			if (zeroPos[1] - 1 >= 0) { // move to the left is possible
				posMoves += "l";
			}
			if (zeroPos[0] - 1 >= 0) { // move upwards is possible
				posMoves += "u";
			}
			if (zeroPos[1] + 1 < size) { // move to the right is possible
				posMoves += "r";
			}
			if (zeroPos[0] + 1 < size) { // move downwards is possible
				posMoves += "d"; // I've added the possible moves in a string in priority order
			}
			double rdm = Math.random();
			int space = autoMoveSelection(rdm, posMoves); // call moveSelection to determine which move the 0 will execute
			String cmd = "" + posMoves.charAt(space);
			table = swapPositions(table, zeroPos[0], zeroPos[1], cmd); // call swapPositions execute the move of 0
			zeroPos = findZeroPos(table); // find 0's position to see what moves are available
		}
		while (isSolution(table)) { // if the shuffled table ends up in a solved state, we shuffle it again until it is not solved
			table = shufflePuzzle(table, dif);
		}
		
		return table;
	}

	// Method that locates the zero and returns its row and column
	public static int[] findZeroPos(int table[][]) {
		int pos[] = new int[2]; // pos[0] = Line of 0 | pos[1] = Column of 0
		for (int i = 0; i < table.length; i++) {
			for (int j = 0; j < table[0].length; j++) {
				if (table[i][j] == 0) {
					pos[0] = i;
					pos[1] = j;
				}
			}
		}
		return pos;
	}

	// Method that is responsible for changing the position of the 0 according to the command given
	public static int[][] swapPositions(int table[][], int rowPos, int colPos, String command) {
		int temp; // we use a temp variable to save the 0s initial position
		if (command.equals("l")) { // swap the 0 with the number on its left
			temp = table[rowPos][colPos];
			table[rowPos][colPos] = table[rowPos][colPos - 1];
			table[rowPos][colPos - 1] = temp;
		} else if (command.equals("r")) { // swap the 0 with the number on its right
			temp = table[rowPos][colPos];
			table[rowPos][colPos] = table[rowPos][colPos + 1];
			table[rowPos][colPos + 1] = temp;
		} else if (command.equals("u")) { // swap the 0 with the number above it
			temp = table[rowPos][colPos];
			table[rowPos][colPos] = table[rowPos - 1][colPos];
			table[rowPos - 1][colPos] = temp;
		} else if (command.equals("d")) { // swap the 0 with the number below it
			temp = table[rowPos][colPos];
			table[rowPos][colPos] = table[rowPos + 1][colPos];
			table[rowPos + 1][colPos] = temp;
		}

		return table;
	}

	// Method that is responsible for the interactive play method
	public static void play(int table[][], int k, int N, int d) {
		int cntMoves = 0;
		int zeroPos[] = new int[2];
		boolean solved = false;
		String command = "";
		table = initializePuzzle(N);
		table = shufflePuzzle(table, k);
		if(d!=0) { // if the user wishes not to present the table, this if statements handles it
			displayPuzzle(table);
			System.out.print("\n");
		}
		command = getUserCommand(0); // get the user's first command
		while (!solved) {
			if (command.equals("e")) { // if the user wishes to exit the game, we break out of the while loop
				break;
			} else {
				zeroPos = findZeroPos(table); // locate 0
				if (isValidMove(command)) { // check if the command is one of the 4 valid characters [l,u,d,r]
					if (!checkBounds(N, command, zeroPos[0], zeroPos[1])) { // check whether the move can be implemented
						outOfBoundsMessage(command); // if not, present the corresponding message
					} else {
						table = swapPositions(table, zeroPos[0], zeroPos[1], command); // execute the move
						cntMoves++; // increase move count by one since the move is valid, in bounds and has been done
					}
				}
				if (isSolution(table)) { // check if the updated puzzle is solved
					solved = true;
					break; // if yes, break out of the loop
				}
				System.out.println("*******************************");
				if(d!=0) { // if no output is selected, don't print the updated puzzle
					displayPuzzle(table);
				}
				System.out.print("\n");
				command = getUserCommand(cntMoves); // get the user's next move
			}
		}
		if (solved) {
			solvedMessage(cntMoves, table); // print a message if the puzzle is solved
		} else {
			notSolvedMessage(cntMoves); // if the user exits the game while playing and the puzzle is not solved print a different message
		}
	}

	// Method that shuffles the puzzle every time the k value changes
	public static int[][] initializePuzzleK(int table[][], int diff) {
		table = shufflePuzzle(table, diff);
		return table;
	}

	// Method that is responsible for the automatic play method
	public static void automaticPlay(int table[][], int kmin, int kmax, int p, int q, int N, int d) {
		int zeroPos[] = new int[2];
		int tempTableK[][] = new int[table.length][table.length];
		double avg, perc;
		int index = 1;
		for (int i = kmin; i <= kmax; i++) {
			int sumMoves = 0;
			int cntSolved = 0;
			table = initializePuzzle(N);
			tempTableK = initializePuzzleK(table, i);
			for (int j = 0; j < p; j++) {
				int arr[][] = tempTableK; // use the initial table for the same difficulty
				for (int y = 0; y < q; y++) {
					zeroPos = findZeroPos(arr); // locate 0
					arr = automaticMoveBounds(arr, zeroPos[0], zeroPos[1]);
					if (isSolution(arr)) { // if the puzzle has been solved
						cntSolved++;
						sumMoves += y + 1;
						break;
					}
				}
			}
			if (d == 1) { // text -  based output
				if (cntSolved == 0) {
					System.out.println("Gia k = " + i + " ekane meso oro kiniseon NaN kai nikise 0.00 % fores");
				} else {
					avg = sumMoves / cntSolved;
					perc = ((double) cntSolved / p) * 100;
					System.out.println("Gia k = " + i + " ekane meso oro kiniseon " + String.format("%.2f", avg) + " kai nikise " + String.format("%.2f", perc) + " % fores");
				}
			} else if(d == 2){
				if (cntSolved == 0) {
					String output = "Gia k = " + i + " ekane meso oro kiniseon NaN kai nikise 0.00 % fores";
					StdDraw.text(300, 637 - (index * 35), output);
					index++;
				} else {
					avg = sumMoves / cntSolved;
					perc = ((double) cntSolved / p) * 100;
					String output = "Gia k = " + i + " ekane meso oro kiniseon " + String.format("%.2f", avg) + " kai nikise " + String.format("%.2f", perc) + " % fores";
					StdDraw.text(300, 637 - (index * 35), output);
					index++;
				}
			}
		}
	}

	// Method that checks what moves are available and does them
	public static int[][] automaticMoveBounds(int tableK[][], int row0, int col0) {
		// This method checks for all the available moves according to 0's location and puts them in a string variable
		// in the correct priority order, just like a part of the shufflePuzzle method
		int sz = tableK.length;
		String availableMoves = "";
		if (col0 - 1 >= 0) { // check for a possible move to the left
			availableMoves += "l";
		}
		if (row0 - 1 >= 0) { // check for a possible move upwards
			availableMoves += "u";
		}
		if (col0 + 1 < sz) { // check for a possible move to the right
			availableMoves += "r";
		}
		if (row0 + 1 < sz) { // check for a possible move downwards
			availableMoves += "d";
		}
		double num = Math.random(); // generate a random number that will determine 0's move 
		int moveIndex = autoMoveSelection(num, availableMoves); // call moveSelection to find where the 0 will go
		String command = "" + availableMoves.charAt(moveIndex);
		tableK = swapPositions(tableK, row0, col0, command);

		return tableK;
	}


	// Method that divides the space [0,1) into smaller spaces according to the available moves that 0 can make 
	public static int autoMoveSelection(double num, String st) {
		// With this method we return an integer that corresponds to the move that the 0 will make
		int size = st.length();
		double arr[] = new double[size - 1];
		if (size == 4) { // if 0 can move to all directions
			for (int i = 0; i < size - 1; i++) {
				arr[i] = ((int) ((1.0 / size) * 100) / 100.0) * (i + 1);
			}
		} else { // if 0 can move to 2 or 3 directions
			for (int i = 0; i < size - 1; i++) {
				arr[i] = ((int) ((1.0 / size) * 10) / 10.0) * (i + 1);
			}
		}
		for (int i = 0; i < arr.length; i++) { // find in which space the random number is in
			if (num <= arr[i]) {
				return i;
			}
		}

		return size - 1; // if we couldn't find a space, then the number was on the last space that we didn't check
	}

	// Method that displays the puzzle (text-based output)
	public static void displayPuzzle(int[][] table) {
		int sz = table.length;
		String stFormat = "";
		for (int i = 0; i < sz; i++) {
			for (int k = 0; k < sz; k++) {
				System.out.print("+-----"); // print the top part of the cells size-times (according to the table's length)
			}
			System.out.println("+"); // print the last "+" when all cells have been printed
			for (int j = 0; j < sz; j++) {
				if (table[i][j] > 99) { // if the number has 3,4 or more digits
					stFormat = String.format("%4d", table[i][j]);
					System.out.print("!" + stFormat + " ");
				} else { // if the number has 1 or 2 digits
					stFormat = String.format("%3d", table[i][j]);
					System.out.print("!" + stFormat + "  ");
				}
			}
			System.out.print("!"); // print the last side barrier
			System.out.print("\n"); // change line to print the next row of numbers
		}
		for (int p = 0; p < sz; p++) { // print the last row of the barrier
			System.out.print("+-----");
		}
		System.out.println("+"); // print the last "+"
	}
	
	// === STD DRAW PART METHODS ===
	
	// Helper method to see if the user clicked inside a button
	private static boolean isInsideCircle(double mouseX, double mouseY, double circleX, double circleY, double circleRadius) {
		double dx = mouseX - circleX;
		double dy = mouseY - circleY;
		return (dx * dx + dy * dy) <= (circleRadius * circleRadius);
	}
	
	// Home screen for the puzzle
	public static int mainHub(int N) {
		StdDraw.setCanvasSize(350, 450);
		StdDraw.setXscale(0, 350);
		StdDraw.setYscale(0, 450);
		Color mpez = new Color(254, 250, 224);
		Color anoixtoPortkali = new Color(221, 161, 94);
		Color skouroPrasino = new Color(40, 54, 24);
		Color anoixtoPrasino = new Color(96, 108, 56);
		Color portokali = new Color(188, 108, 37);
		Font fontHead = new Font("Arial", Font.BOLD, 55);
		Font fontExit = new Font("Arial", Font.BOLD, 30);
		Font info = new Font("Arial", Font.BOLD, 20);
		StdDraw.clear(skouroPrasino);
		StdDraw.setFont(fontHead);
		StdDraw.setPenColor(Color.black); // Draw the shadow of the title
		StdDraw.text(180, 393, "N-Puzzle");
		StdDraw.text(180, 333, "THE GAME");
		StdDraw.setPenColor(mpez);
		StdDraw.text(175, 400, "N-Puzzle"); // Draw the title
		StdDraw.setPenColor(anoixtoPortkali);
		StdDraw.text(175, 340, "THE GAME");
		StdDraw.setFont(fontExit);
		StdDraw.setPenColor(anoixtoPortkali); // Exit button
		StdDraw.text(310, 15, "EXIT");
		StdDraw.setFont(info);
		StdDraw.setPenColor(anoixtoPortkali); // Game info (puzzle size)
		String gameInfo = "Puzzle size N = " + N;
		StdDraw.text(85, 15, gameInfo);
		StdDraw.setPenColor(Color.black); // Draw the play button
		StdDraw.filledCircle(175, 160, 105);
		StdDraw.filledRectangle(300, 160, 120, 105);
		StdDraw.setPenColor(anoixtoPrasino);
		StdDraw.filledCircle(175, 160, 100);
		StdDraw.setPenColor(mpez);
		StdDraw.filledCircle(175, 160, 80);
		StdDraw.setPenColor(portokali); // Draw the play triangle
		double xValues[] = { 150, 150, 220 };
		double yValues[] = { 200, 120, 160 };
		StdDraw.filledPolygon(xValues, yValues);
		
		while (true) {
			if (StdDraw.isMousePressed()) { // check if the user presses on the play or exit button
				StdDraw.pause(100);
				double xPos = StdDraw.mouseX();
				double yPos = StdDraw.mouseY();
				if (isInsideCircle(xPos, yPos, 175, 160, 80)) {
					return 1;
				} else if ((xPos >= 260 && xPos <= 360) && (yPos >= 0 && yPos <= 60)) {
					return 0;
				}

			}
		}
	}
	
	// Play method menu (Interactive/Automatic/Exit)
	public static int playMenuGraph() {
		Color mpez = new Color(254, 250, 224);
		Color anoixtoPortokali = new Color(221, 161, 94);
		Color skouroPrasino = new Color(40, 54, 24);
		Color anoixtoPrasino = new Color(96, 108, 56);
		Color portokali = new Color(188, 108, 37);
		Font buttonText = new Font("Arial", Font.BOLD, 25);
		Font headline = new Font("Arial", Font.BOLD, 55);
		StdDraw.pause(200);
		StdDraw.setCanvasSize(350, 450);
		StdDraw.clear(skouroPrasino);
		StdDraw.setXscale(0, 350);
		StdDraw.setYscale(0, 450);
		// Play method selection
		// Interactive Play Button
		StdDraw.setPenColor(anoixtoPrasino);
		StdDraw.filledRectangle(175, 250, 110, 40);
		StdDraw.filledCircle(75, 250, 40);
		StdDraw.filledCircle(275, 250, 40);
		StdDraw.setPenColor(mpez);
		StdDraw.filledRectangle(175, 250, 100, 30);
		StdDraw.filledCircle(75, 250, 30);
		StdDraw.filledCircle(275, 250, 30);
		// Automatic Play Button
		StdDraw.setPenColor(anoixtoPrasino);
		StdDraw.filledRectangle(175, 160, 110, 40);
		StdDraw.filledCircle(75, 160, 40);
		StdDraw.filledCircle(275, 160, 40);
		StdDraw.setPenColor(mpez);
		StdDraw.filledRectangle(175, 160, 100, 30);
		StdDraw.filledCircle(75, 160, 30);
		StdDraw.filledCircle(275, 160, 30);
		// Exit Button
		StdDraw.setPenColor(anoixtoPrasino);
		StdDraw.filledRectangle(175, 70, 110, 40);
		StdDraw.filledCircle(75, 70, 40);
		StdDraw.filledCircle(275, 70, 40);
		StdDraw.setPenColor(mpez);
		StdDraw.filledRectangle(175, 70, 100, 30);
		StdDraw.filledCircle(75, 70, 30);
		StdDraw.filledCircle(275, 70, 30);
		// Play Buttons
		StdDraw.setFont(buttonText);
		StdDraw.setPenColor(portokali);
		StdDraw.text(175, 250, "Interactive Play");
		StdDraw.text(175, 160, "Automatic Play");
		StdDraw.text(175, 70, "Exit");
		StdDraw.setPenColor(Color.black);
		StdDraw.filledRectangle(230, 380, 160, 60);
		StdDraw.filledCircle(80, 380, 60);
		StdDraw.setPenColor(anoixtoPrasino);
		StdDraw.filledRectangle(230, 380, 150, 55);
		StdDraw.filledCircle(80, 380, 55);
		StdDraw.setFont(headline);
		StdDraw.setPenColor(mpez);
		StdDraw.text(175, 400, "PLAY");
		StdDraw.setPenColor(anoixtoPortokali);
		StdDraw.text(175, 350, "METHOD");
		while (true) {
			if (StdDraw.isMousePressed()) { // check which button the user has clicked on and return a value (1: Inter/ 2. Auto/ 3.Exit)
				StdDraw.pause(100);
				if (StdDraw.mouseX() >= 75 && StdDraw.mouseX() <= 275) {
					if (StdDraw.mouseY() >= 220 && StdDraw.mouseY() <= 280) {
						return 1;
					}
					if (StdDraw.mouseY() >= 130 && StdDraw.mouseY() <= 180) {
						return 2;
					}
					if (StdDraw.mouseY() >= 40 && StdDraw.mouseY() <= 100) {
						return 3;
					}
				}
			}
		}
	}
	
	// Difficulty Selection Screen (Interactive Play)
	public static int difficultySelection() {
		int k = 0;
		Color mpez = new Color(254, 250, 224);
		Color anoixtoPortokali = new Color(221, 161, 94);
		Color skouroPrasino = new Color(40, 54, 24);
		Color portokali = new Color(188, 108, 37);
		Color anoixtoPrasino = new Color(96, 108, 56);
		Font fontHead = new Font("Arial", Font.BOLD, 55);
		Font diff = new Font("Arial", Font.BOLD, 20);
		Font info = new Font("Arial", Font.PLAIN, 17);
		Font indexes = new Font("Arial", Font.BOLD, 25);
		Font kNum = new Font("Arial", Font.BOLD, 100);
		StdDraw.pause(100);
		StdDraw.setCanvasSize(350, 450);
		StdDraw.clear(skouroPrasino);
		StdDraw.setXscale(0, 350);
		StdDraw.setYscale(0, 450);
		// Headline Text & Shapes
		StdDraw.setPenColor(Color.black);
		StdDraw.filledRectangle(230, 380, 170, 60);
		StdDraw.filledCircle(70, 380, 60);
		StdDraw.setPenColor(anoixtoPrasino);
		StdDraw.filledRectangle(230, 380, 160, 55);
		StdDraw.filledCircle(70, 380, 55);
		StdDraw.setFont(fontHead);
		StdDraw.setPenColor(mpez);
		StdDraw.text(175, 400, "PUZZLE");
		StdDraw.setPenColor(anoixtoPortokali);
		StdDraw.text(175, 350, "SETTINGS");
		// Note on bottom of screen
		StdDraw.setFont(info);
		StdDraw.setPenColor(mpez);
		StdDraw.text(175, 30, "*The value entered represents how many times");
		StdDraw.text(130, 12, "the puzzled is randomly shuffled.");
		// Diffuculty Menu
		StdDraw.setFont(diff);
		StdDraw.text(75, 255, "> DIFFICULTY:");
		StdDraw.setPenRadius(0.01);
		StdDraw.rectangle(175, 110, 90, 50);
		StdDraw.setPenColor(Color.black);
		StdDraw.filledCircle(70, 200, 25);
		StdDraw.filledCircle(145, 200, 20);
		StdDraw.filledCircle(215, 200, 20);
		StdDraw.filledCircle(290, 200, 25);
		StdDraw.setPenColor(mpez);
		StdDraw.filledCircle(65, 205, 25);
		StdDraw.filledCircle(140, 205, 20);
		StdDraw.filledCircle(210, 205, 20);
		StdDraw.filledCircle(285, 205, 25);
		StdDraw.setFont(indexes);
		StdDraw.setPenColor(portokali);
		StdDraw.text(65, 202, "-5");
		StdDraw.text(140, 202, "-1");
		StdDraw.text(210, 202, "+1");
		StdDraw.text(285, 202, "+5");
		double X[] = { 320, 320, 345 }; // Draw the continue arrow
		double Y[] = { 130, 90, 110 };
		StdDraw.filledPolygon(X, Y);
		StdDraw.filledRectangle(310, 110, 20, 10);
		StdDraw.setFont(kNum);
		while (true) {
			StdDraw.setPenColor(mpez);
			// String textK = k + "";
			StdDraw.text(175, 96, Integer.toString(k));
			StdDraw.show();
			if (StdDraw.isMousePressed()) { // check which adjuster button the user has pressed or if he presses the continue arrow
				StdDraw.pause(100);
				double xPos = StdDraw.mouseX();
				double yPos = StdDraw.mouseY();
				if (isInsideCircle(xPos, yPos, 65, 205, 25) && (k - 5 > 0)) {
					k -= 5;
					StdDraw.setPenColor(skouroPrasino);
					StdDraw.filledRectangle(175, 110, 83, 40);
				}
				if (isInsideCircle(xPos, yPos, 285, 205, 25) && (k + 5 <= 999)) {
					k += 5;
					StdDraw.setPenColor(skouroPrasino);
					StdDraw.filledRectangle(175, 110, 83, 40);
				}
				if (isInsideCircle(xPos, yPos, 210, 205, 20) && (k + 1 <= 999)) {
					k++;
					StdDraw.setPenColor(skouroPrasino);
					StdDraw.filledRectangle(175, 110, 83, 40);
				}
				if (isInsideCircle(xPos, yPos, 140, 205, 20) && (k - 1 > 0)) {
					k--;
					StdDraw.setPenColor(skouroPrasino);
					StdDraw.filledRectangle(175, 110, 83, 40);
				}
				if ((xPos >= 320 - 30 && xPos <= 320 + 30) && (yPos >= 110 - 20 && yPos <= 110 + 20) && (k > 0)) {
					return k;
				}
			}
		}

	}
		
	// Settings Screen (Automatic Play)
	public static int[] automaticSettingsMenu(int table[][], int N) {
		int kmin = 0, kmax = 0, p = 0, q = 0;
		int values[] = new int[4];
		Color mpez = new Color(254, 250, 224);
		Color anoixtoPortokali = new Color(221, 161, 94);
		Color skouroPrasino = new Color(40, 54, 24);
		Color portokali = new Color(188, 108, 37);
		Color anoixtoPrasino = new Color(96, 108, 56);
		Font fontHead = new Font("Arial", Font.BOLD, 55);
		Font text = new Font("Arial", Font.BOLD, 25);
		Font indexes = new Font("Arial", Font.BOLD, 25);
		StdDraw.pause(100);
		StdDraw.setCanvasSize(700, 500);
		StdDraw.clear(skouroPrasino);
		StdDraw.setXscale(0, 600);
		StdDraw.setYscale(0, 500);
		StdDraw.setPenColor(Color.black); // Draw the top bar notch
		StdDraw.filledRectangle(550, 410, 350, 60);
		StdDraw.filledCircle(200, 410, 60);
		StdDraw.setPenColor(anoixtoPrasino);
		StdDraw.filledRectangle(550, 410, 350, 55);
		StdDraw.filledCircle(200, 410, 55);
		StdDraw.setFont(fontHead);
		StdDraw.setPenColor(mpez);
		StdDraw.text(300, 430, "PUZZLE"); // Draw the title
		StdDraw.setPenColor(anoixtoPortokali);
		StdDraw.text(300, 380, "SETTINGS");
		StdDraw.setFont(text);
		StdDraw.setPenColor(mpez); // Draw the menu for the options
		StdDraw.text(135, 260, "> MINIMUM DIFFICULTY:");
		StdDraw.text(138, 190, "> MAXIMUM DIFFICULTY:");
		StdDraw.text(147, 120, "> PLAYS PER DIFFICULTY:");
		StdDraw.text(113, 50, "> MAXIMUM MOVES:");
		StdDraw.setPenColor(portokali);
		double X[] = { 550, 550, 575 }; // Draw the continue arrow
		double Y[] = { 340, 300, 320 };
		StdDraw.filledPolygon(X, Y);
		StdDraw.filledRectangle(540, 320, 20, 10);
		StdDraw.setPenColor(Color.black); // Draw the shadows of the buttons
		StdDraw.filledCircle(335, 255, 20);
		StdDraw.filledCircle(335, 185, 20);
		StdDraw.filledCircle(335, 115, 20);
		StdDraw.filledCircle(335, 45, 20);
		StdDraw.filledCircle(385, 255, 20);
		StdDraw.filledCircle(385, 185, 20);
		StdDraw.filledCircle(385, 115, 20);
		StdDraw.filledCircle(385, 45, 20);
		StdDraw.filledCircle(505, 255, 20);
		StdDraw.filledCircle(505, 185, 20);
		StdDraw.filledCircle(505, 115, 20);
		StdDraw.filledCircle(505, 45, 20);
		StdDraw.filledCircle(555, 255, 20);
		StdDraw.filledCircle(555, 185, 20);
		StdDraw.filledCircle(555, 115, 20);
		StdDraw.filledCircle(555, 45, 20);
		StdDraw.setPenColor(mpez); // Draw the buttons
		StdDraw.filledCircle(330, 260, 20);
		StdDraw.filledCircle(330, 190, 20);
		StdDraw.filledCircle(330, 120, 20);
		StdDraw.filledCircle(330, 50, 20);
		StdDraw.filledCircle(380, 260, 20);
		StdDraw.filledCircle(380, 190, 20);
		StdDraw.filledCircle(380, 120, 20);
		StdDraw.filledCircle(380, 50, 20);
		StdDraw.filledCircle(500, 260, 20);
		StdDraw.filledCircle(500, 190, 20);
		StdDraw.filledCircle(500, 120, 20);
		StdDraw.filledCircle(500, 50, 20);
		StdDraw.filledCircle(550, 260, 20);
		StdDraw.filledCircle(550, 190, 20);
		StdDraw.filledCircle(550, 120, 20);
		StdDraw.filledCircle(550, 50, 20);
		StdDraw.setFont(indexes); // Draw the text inside the buttons
		StdDraw.setPenColor(portokali);
		StdDraw.text(330, 260, "-5");
		StdDraw.text(330, 190, "-5");
		StdDraw.text(330, 120, "-5");
		StdDraw.text(330, 50, "-5");
		StdDraw.text(380, 260, "-1");
		StdDraw.text(380, 190, "-1");
		StdDraw.text(380, 120, "-1");
		StdDraw.text(380, 50, "-1");
		StdDraw.text(500, 260, "+1");
		StdDraw.text(500, 190, "+1");
		StdDraw.text(500, 120, "+1");
		StdDraw.text(500, 50, "+1");
		StdDraw.text(550, 260, "+5");
		StdDraw.text(550, 190, "+5");
		StdDraw.text(550, 120, "+5");
		StdDraw.text(550, 50, "+5");
		StdDraw.setPenColor(mpez);
		StdDraw.text(440, 260, "0");
		StdDraw.text(440, 190, "0");
		StdDraw.text(440, 120, "0");
		StdDraw.text(440, 50, "0");

		while (true) {
			if (StdDraw.isMousePressed()) { // check which button the user clicked on and increase/decrease the value
				StdDraw.setPenColor(skouroPrasino);
				StdDraw.filledRectangle(300, 320, 170, 30);
				StdDraw.pause(100);
				double xPos = StdDraw.mouseX();
				double yPos = StdDraw.mouseY();
				if (isInsideCircle(xPos, yPos, 330, 260, 20) && (kmin - 5 > 0)) {
					kmin -= 5;
					StdDraw.setPenColor(skouroPrasino);
					StdDraw.filledRectangle(440, 260, 35, 20);
					String minK = kmin + "";
					StdDraw.setPenColor(mpez);
					StdDraw.text(440, 260, minK);
				} else if (isInsideCircle(xPos, yPos, 330, 190, 20) && (kmax - 5 > 0)) {
					kmax -= 5;
					StdDraw.setPenColor(skouroPrasino);
					StdDraw.filledRectangle(440, 190, 35, 20);
					String maxK = kmax + "";
					StdDraw.setPenColor(mpez);
					StdDraw.text(440, 190, maxK);
				} else if (isInsideCircle(xPos, yPos, 330, 120, 20) && (p - 5 > 0)) {
					p -= 5;
					StdDraw.setPenColor(skouroPrasino);
					StdDraw.filledRectangle(440, 120, 35, 20);
					String maxPlays = p + "";
					StdDraw.setPenColor(mpez);
					StdDraw.text(440, 120, maxPlays);
				} else if (isInsideCircle(xPos, yPos, 330, 50, 20) && (q - 5 > 0)) {
					q -= 5;
					StdDraw.setPenColor(skouroPrasino);
					StdDraw.filledRectangle(440, 50, 35, 20);
					String maxMoves = q + "";
					StdDraw.setPenColor(mpez);
					StdDraw.text(440, 50, maxMoves);
				} else if (isInsideCircle(xPos, yPos, 380, 260, 20) && (kmin - 1 > 0)) {
					kmin--;
					StdDraw.setPenColor(skouroPrasino);
					StdDraw.filledRectangle(440, 260, 35, 20);
					String minK = kmin + "";
					StdDraw.setPenColor(mpez);
					StdDraw.text(440, 260, minK);
				} else if (isInsideCircle(xPos, yPos, 380, 190, 20) && (kmax - 1 > 0)) {
					kmax--;
					StdDraw.setPenColor(skouroPrasino);
					StdDraw.filledRectangle(440, 190, 35, 20);
					String maxK = kmax + "";
					StdDraw.setPenColor(mpez);
					StdDraw.text(440, 190, maxK);
				} else if (isInsideCircle(xPos, yPos, 380, 120, 20) && (p - 1 > 0)) {
					p--;
					StdDraw.setPenColor(skouroPrasino);
					StdDraw.filledRectangle(440, 120, 35, 20);
					String maxPlays = p + "";
					StdDraw.setPenColor(mpez);
					StdDraw.text(440, 120, maxPlays);
				} else if (isInsideCircle(xPos, yPos, 380, 50, 20) && (q - 1 > 0)) {
					q--;
					StdDraw.setPenColor(skouroPrasino);
					StdDraw.filledRectangle(440, 50, 35, 20);
					String maxMoves = q + "";
					StdDraw.setPenColor(mpez);
					StdDraw.text(440, 50, maxMoves);
				} else if (isInsideCircle(xPos, yPos, 500, 260, 20) && (kmin + 1 <= 999)) {
					kmin++;
					StdDraw.setPenColor(skouroPrasino);
					StdDraw.filledRectangle(440, 260, 35, 20);
					String minK = kmin + "";
					StdDraw.setPenColor(mpez);
					StdDraw.text(440, 260, minK);
				} else if (isInsideCircle(xPos, yPos, 500, 190, 20) && (kmax + 1 <= 999)) {
					kmax++;
					StdDraw.setPenColor(skouroPrasino);
					StdDraw.filledRectangle(440, 190, 35, 20);
					String maxK = kmax + "";
					StdDraw.setPenColor(mpez);
					StdDraw.text(440, 190, maxK);
				} else if (isInsideCircle(xPos, yPos, 500, 120, 20) && (p + 1 <= 999)) {
					p++;
					StdDraw.setPenColor(skouroPrasino);
					StdDraw.filledRectangle(440, 120, 35, 20);
					String maxPlays = p + "";
					StdDraw.setPenColor(mpez);
					StdDraw.text(440, 120, maxPlays);
				} else if (isInsideCircle(xPos, yPos, 500, 50, 20) && (q + 1 <= 999)) {
					q++;
					StdDraw.setPenColor(skouroPrasino);
					StdDraw.filledRectangle(440, 50, 35, 20);
					String maxMoves = q + "";
					StdDraw.setPenColor(mpez);
					StdDraw.text(440, 50, maxMoves);
				} else if (isInsideCircle(xPos, yPos, 550, 260, 20) && (kmin + 5 <= 999)) {
					kmin += 5;
					StdDraw.setPenColor(skouroPrasino);
					StdDraw.filledRectangle(440, 260, 35, 20);
					String minK = kmin + "";
					StdDraw.setPenColor(mpez);
					StdDraw.text(440, 260, minK);
				} else if (isInsideCircle(xPos, yPos, 550, 190, 20) && (kmax + 5 <= 999)) {
					kmax += 5;
					StdDraw.setPenColor(skouroPrasino);
					StdDraw.filledRectangle(440, 190, 35, 20);
					String maxK = kmax + "";
					StdDraw.setPenColor(mpez);
					StdDraw.text(440, 190, maxK);
				} else if (isInsideCircle(xPos, yPos, 550, 120, 20) && (p + 5 <= 999)) {
					p += 5;
					StdDraw.setPenColor(skouroPrasino);
					StdDraw.filledRectangle(440, 120, 35, 20);
					String maxPlays = p + "";
					StdDraw.setPenColor(mpez);
					StdDraw.text(440, 120, maxPlays);
				} else if (isInsideCircle(xPos, yPos, 550, 50, 20) && (q + 5 <= 999)) {
					q += 5;
					StdDraw.setPenColor(skouroPrasino);
					StdDraw.filledRectangle(440, 50, 35, 20);
					String maxMoves = q + "";
					StdDraw.setPenColor(mpez);
					StdDraw.text(440, 50, maxMoves);
				} else if ((xPos >= 515 && xPos <= 585) && (yPos >= 300 && yPos <= 340)) {
					if (kmin < kmax) {
						values[0] = kmin;
						values[1] = kmax;
						values[2] = p;
						values[3] = q;
						return values;
					} else {
						StdDraw.setPenColor(mpez);
						StdDraw.text(300, 320, "Check for max dif. >= min. dif. !!");
					}
				}

			}
		}
	}

	// Automatic Play Method
	public static void automaticGraphicsPlay(int table[][], int kmin, int kmax, int p, int q, int N) {
		Color mpez = new Color(254, 250, 224);
		Color anoixtoPortokali = new Color(221, 161, 94);
		Color skouroPrasino = new Color(40, 54, 24);
		Font fontHead = new Font("Arial", Font.BOLD, 55);
		Font subHead = new Font("Arial", Font.BOLD, 30);
		Font text = new Font("Arial", Font.BOLD, 20);
		StdDraw.setCanvasSize(700, 800);
		StdDraw.clear(skouroPrasino);
		StdDraw.setXscale(0, 700);
		StdDraw.setYscale(0, 800);
		StdDraw.setFont(fontHead);
		StdDraw.setPenColor(Color.black); // Draw the shadow of the title
		StdDraw.text(215, 743, "PLAY");
		StdDraw.text(435, 743, "RESULTS");
		StdDraw.setPenColor(mpez);
		StdDraw.text(210, 750, "PLAY"); // Draw the title
		StdDraw.setPenColor(anoixtoPortokali);
		StdDraw.text(430, 750, "RESULTS");
		StdDraw.setFont(subHead);
		StdDraw.text(650, 590, "EXIT");
		StdDraw.setPenColor(mpez); // Draw the top bar/table with the input values by the user
		StdDraw.text(70, 690, "Min. Diff ");
		StdDraw.text(230, 690, "Max. Diff ");
		StdDraw.text(410, 690, "Play Times");
		StdDraw.text(605, 690, "Max. Moves");
		StdDraw.setPenColor(anoixtoPortokali);
		StdDraw.setPenRadius(0.006);
		StdDraw.line(0, 670, 700, 670);
		StdDraw.line(0, 715, 700, 715);
		StdDraw.line(0, 620, 700, 620);
		StdDraw.line(150, 715, 150, 620);
		StdDraw.line(320, 715, 320, 620);
		StdDraw.line(507.5, 715, 507.5, 620);
		StdDraw.setPenColor(mpez);
		StdDraw.text(70, 643, Integer.toString(kmin)); // Draw the input values
		StdDraw.text(230, 643, Integer.toString(kmax));
		StdDraw.text(410, 643, Integer.toString(p));
		StdDraw.text(605, 643, Integer.toString(q));
		StdDraw.setFont(text);
		automaticPlay(table, kmin, kmax, p, q, N, 2);
		while (true) {
			if (StdDraw.isMousePressed()) { // check if the user pressed on the exit button
				double xPos = StdDraw.mouseX();
				double yPos = StdDraw.mouseY();
				if ((xPos >= 620 && xPos <= 680) && (yPos <= 610 && yPos >= 570)) {
					exitScreen();
				}
			}
		}
	}
	
	// Method that displays the puzzle using StdDraw
	public static void displayPuzzleGraphics(int table[][], int N) {
		Color mpez = new Color(254, 250, 224);
		Color anoixtoPrasino = new Color(96, 108, 56);
		Color portokali = new Color(188, 108, 37);
		int cSize = N * 150;
		int cellSz = 70;
		Font puzzle = new Font("Verdana", Font.PLAIN, 35);
		Font moves = new Font("Verdana", Font.BOLD, (cSize / 10));
		double xOffset = (cSize - (N * cellSz)) / 2;
		double yOffset = (cSize - (N * 0.5 * cellSz)) / 2;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				double x = xOffset + j * cellSz;
				double y = yOffset + i * cellSz;
				StdDraw.setPenColor(anoixtoPrasino);
				StdDraw.filledRectangle(x + cellSz / 2, y + cellSz / 2, cellSz / 2, cellSz / 2);
				if (table[(N - 1) - i][j] != 0) {
					int cellValue = table[(N - 1) - i][j];
					StdDraw.setPenColor(mpez);
					StdDraw.setFont(puzzle);
					StdDraw.text(x + cellSz / 2, y + cellSz / 2, String.valueOf(cellValue));
				}
			}
		}
		StdDraw.setPenRadius(0.01);
		StdDraw.setPenColor(mpez);
		for (int i = 0; i <= N; i++) { // Draw the lines of the table
			double y = yOffset + i * cellSz;
			StdDraw.line(xOffset, y, xOffset + (N * cellSz), y);
		}
		for (int j = 0; j <= N; j++) {
			double x = xOffset + j * cellSz;
			StdDraw.line(x, yOffset, x, yOffset + (N * cellSz));
		}
		StdDraw.setPenColor(Color.black); // Draw the shadow of the move buttons
		StdDraw.filledCircle((cSize / 5), (cSize / 4), (cSize / 12.5));
		StdDraw.filledCircle((cSize / 5) + (cSize / 5), (cSize / 4), (cSize / 12.5));
		StdDraw.filledCircle((cSize - (cSize / 5) - (cSize / 5)), (cSize / 4), (cSize / 12.5));
		StdDraw.filledCircle((cSize - (cSize / 5)), (cSize / 4), (cSize / 12.5));
		StdDraw.setPenColor(mpez); // Draw the move buttons
		StdDraw.filledCircle((cSize / 5), (cSize / 4), (cSize / 14));
		StdDraw.filledCircle((cSize / 5) + (cSize / 5), (cSize / 4), (cSize / 14));
		StdDraw.filledCircle((cSize - (cSize / 5) - (cSize / 5)), (cSize / 4), (cSize / 14));
		StdDraw.filledCircle((cSize - (cSize / 5)), (cSize / 4), (cSize / 14));
		StdDraw.setPenColor(portokali);
		StdDraw.setFont(moves);
		StdDraw.text((cSize / 5), (cSize / 4) - (cSize / 70), "L");
		StdDraw.text((cSize / 5) + (cSize / 5), (cSize / 4) - (cSize / 70), "U");
		StdDraw.text((cSize - (cSize / 5) - (cSize / 5)), (cSize / 4) - (cSize / 70), "D");
		StdDraw.text((cSize - (cSize / 5)), (cSize / 4) - (cSize / 70), "R");

	}
	
	// Interactive Play Method
	public static void interactiveGraphicsPlay(int[][] table, int N) {
		int cSize = N * 150;
		Color mpez = new Color(254, 250, 224);
		Color skouroPrasino = new Color(40, 54, 24);
		Color portokali = new Color(188, 108, 37);
		Font boundsM = new Font("Arial", Font.PLAIN, 35);
		Font movesText = new Font("Verdana", Font.BOLD, (cSize / 18));
		StdDraw.pause(100);
		StdDraw.setCanvasSize(cSize, cSize);
		StdDraw.setXscale(0, cSize);
		StdDraw.setYscale(0, cSize);
		StdDraw.clear(skouroPrasino);
		displayPuzzleGraphics(table, N);
		StdDraw.setPenColor(mpez);
		StdDraw.setFont(movesText);
		StdDraw.text((cSize / 8), (cSize / 20), "MOVES:");
		StdDraw.setPenColor(portokali);
		StdDraw.text((cSize / 1.1), (cSize / 20), "EXIT");
		StdDraw.setPenColor(mpez);
		int zeroPos[] = new int[2];
		String command = "";
		boolean solved = false;
		boolean validMove = false;
		while (!solved) {
			while (true) {
				if (StdDraw.isMousePressed()) { // check which move button the user clicked on / or check if he pressed EXIT
					StdDraw.pause(100);
					zeroPos = findZeroPos(table);
					double xPos = StdDraw.mouseX();
					double yPos = StdDraw.mouseY();
					if (isInsideCircle(xPos, yPos, ((cSize / 5) + (cSize/5)), (cSize / 4), (cSize / 14))) {
						command = "u";
						cntMoves++;
					}
					if (isInsideCircle(xPos, yPos, (cSize - (cSize / 5) - (cSize/5)), (cSize / 4), (cSize / 14))) {
						command = "d";
						cntMoves++;
					}
					if (isInsideCircle(xPos, yPos, (cSize - (cSize/5)), (cSize / 4), (cSize / 14))) {
						command = "r";
						cntMoves++;
					}
					if (isInsideCircle(xPos, yPos, (cSize / 5), (cSize / 4), (cSize / 14))) {
						command = "l";
						cntMoves++;
					}
					if ((xPos >= ((cSize / 1.1) - 35)) && (xPos <= (cSize / 1.1) + 35) && (yPos >= (cSize / 19) - 20) && (yPos <= (cSize / 19) + 20)) {
						break;
					}
					validMove = checkBounds(N, command, zeroPos[0], zeroPos[1]);
					if (validMove) { // check if the move is in bounds
						table = swapPositions(table, zeroPos[0], zeroPos[1], command);
						String moves = "" + cntMoves;
						StdDraw.setPenColor(skouroPrasino);
						StdDraw.filledRectangle(cSize / 2, (cSize / 2) + (cSize / 2.3), cSize, cSize / 18);
						StdDraw.filledRectangle((cSize / 3.3), (cSize / 20), 20, 25);
						StdDraw.setPenColor(mpez);
						StdDraw.setFont(movesText);
						StdDraw.text((cSize / 3.3), (cSize / 19), moves);
					} else { // if not we present a message and wait for the next move
						cntMoves--;
						StdDraw.setPenColor(skouroPrasino);
						StdDraw.filledRectangle(cSize / 2, (cSize / 1.09), cSize, cSize / 18);
						StdDraw.setPenColor(mpez);
						StdDraw.setFont(boundsM);
						switch (command) {
						case "l":
							StdDraw.text(cSize / 2, (cSize / 1.09), "You can't go left!");
							break;
						case "r":
							StdDraw.text(cSize / 2, (cSize / 1.09), "You can't go right!");
							break;
						case "u":
							StdDraw.text(cSize / 2, (cSize / 1.09), "You can't go up!");
							break;
						case "d":
							StdDraw.text(cSize / 2, (cSize / 1.09), "You can't go down!");
							break;
						}
					}
					if (isSolution(table)) { // if the table is solved we prompt the user with the solved screen
						solved = true;
						displayPuzzleGraphics(table, N);
						StdDraw.pause(200);
						break;
					}
					StdDraw.pause(100);
					displayPuzzleGraphics(table, N); // otherwhise we print the updated puzzle
				}
			}
			if (solved) {
				solvedScreen(table, N, cntMoves);
			} else { // if user presses exit button
				exitScreen();
				break;
			}
		}
	}
	
	// Puzzle Solved Screen
	public static void solvedScreen(int table[][], int N, int moves) {
		int cSize = N * 150;
		Color skouroPrasino = new Color(40, 54, 24);
		Color portokali = new Color(188, 108, 37);
		Font head = new Font("Verdana", Font.BOLD, 35);
		Font exit = new Font("Verdana", Font.BOLD, (cSize / 18));
		String outMoves = "PUZZLE IN " + moves;
		StdDraw.pause(100);
		StdDraw.setCanvasSize(cSize, cSize);
		StdDraw.setXscale(0, cSize);
		StdDraw.setYscale(0, cSize);
		StdDraw.clear(skouroPrasino);
		displayPuzzleGraphics(table, N);
		StdDraw.setPenColor(skouroPrasino);
		StdDraw.filledRectangle(cSize / 2, cSize / 6, cSize / 2, cSize / 5);
		StdDraw.setFont(head);
		StdDraw.setPenColor(Color.black); // draw the shadow
		StdDraw.text((cSize / 2) + (cSize / 70), (cSize / 1.10), "CONGRATULATIONS!!!");
		StdDraw.setPenColor(portokali); // draw the text
		StdDraw.text(cSize / 2, (cSize / 1.09), "CONGRATULATIONS!!!");
		StdDraw.text(cSize / 2, (cSize / 4), "YOU SOLVED THE");
		StdDraw.text(cSize / 2, cSize / 6, outMoves);
		StdDraw.text(cSize / 2, cSize / 12, "MOVES!");
		StdDraw.setPenColor(portokali);
		StdDraw.setFont(exit);
		StdDraw.text((cSize / 1.1), (cSize / 20), "EXIT");
		while (true) {
			if (StdDraw.isMousePressed()) { // check if the user clicked on the exit button
				double xPos = StdDraw.mouseX();
				double yPos = StdDraw.mouseY();
				if ((xPos >= ((cSize / 1.1) - 35)) && (xPos <= (cSize / 1.1) + 35) && (yPos >= (cSize / 19) - 20) && (yPos <= (cSize / 19) + 20)) {
					exitScreen();
				}
			}
		}
	}

	// Exit Screen
	public static void exitScreen() {
		Color mpez = new Color(254, 250, 224);
		Color skouroPrasino = new Color(40, 54, 24);
		Color anoixtoPrasino = new Color(96, 108, 56);
		Color portokali = new Color(188, 108, 37);
		Font fontHead = new Font("Arial", Font.BOLD, 45);
		Font rating = new Font("Arial", Font.BOLD, 30);
		StdDraw.pause(100);
		StdDraw.setCanvasSize(350, 450);
		StdDraw.clear(skouroPrasino);
		StdDraw.setXscale(0, 350);
		StdDraw.setYscale(0, 450);
		StdDraw.setFont(fontHead);
		StdDraw.setPenColor(Color.black); // Draw the shadow of the text
		StdDraw.text(180, 343, "THANK YOU");
		StdDraw.text(180, 293, "FOR PLAYING!!");
		StdDraw.setPenColor(mpez); // Draw the text
		StdDraw.text(175, 350, "THANK YOU");
		StdDraw.setPenColor(portokali);
		StdDraw.text(175, 300, "FOR PLAYING!!");
		StdDraw.setPenColor(Color.black); // Draw the notch at the top
		StdDraw.filledCircle(87.5, 130, 45);
		StdDraw.filledCircle(262.5, 130, 45);
		StdDraw.setPenColor(mpez);
		StdDraw.filledCircle(87.5, 130, 40);
		StdDraw.filledCircle(262.5, 130, 40);
		StdDraw.setPenColor(Color.black);
		StdDraw.filledRectangle(215, 225, 160, 35);
		StdDraw.filledCircle(65, 225, 35);
		StdDraw.setPenColor(anoixtoPrasino);
		StdDraw.filledRectangle(215, 225, 150, 30);
		StdDraw.filledCircle(65, 225, 30);
		StdDraw.setPenColor(portokali);
		StdDraw.setFont(fontHead); // Draw rating buttons and comment
		StdDraw.text(175, 220, "ENJOYED?");
		StdDraw.setFont(rating);
		StdDraw.text(87.5, 125, "YES");
		StdDraw.text(262.5, 125, "NO");
		while (true) {
			if (StdDraw.isMousePressed()) { // check is user pressed Yes or No for rating and call the appropriate method
				StdDraw.pause(100);
				double xPos = StdDraw.mouseX();
				double yPos = StdDraw.mouseY();
				if (isInsideCircle(xPos, yPos, 87.5, 130, 35)) {
					smileyFace();
					break;
				} else if (isInsideCircle(xPos, yPos, 262.5, 130, 35)) {
					sadFace();
					break;
				}
			}
		}
	}
	
	// Smiley Face Rating
	public static void smileyFace() {
		Color skouroPrasino = new Color(40, 54, 24);
		StdDraw.setCanvasSize(350, 450);
		StdDraw.setXscale(0, 350);
		StdDraw.setYscale(0, 450);
		StdDraw.clear(skouroPrasino);
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.filledCircle(175, 225, 135);
		StdDraw.setPenColor(StdDraw.YELLOW);
		StdDraw.filledCircle(175, 225, 130);
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.filledCircle(125, 300, 20);
		StdDraw.filledCircle(225, 300, 20);
		StdDraw.setPenRadius(0.01);
		StdDraw.arc(175, 250, 100, 200, 340);
	}
	
	// Sad Face Rating
	public static void sadFace() {
		Color skouroPrasino = new Color(40, 54, 24);
		StdDraw.setCanvasSize(350, 450);
		StdDraw.setXscale(0, 350);
		StdDraw.setYscale(0, 450);
		StdDraw.clear(skouroPrasino);
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.filledCircle(175, 225, 135);
		StdDraw.setPenColor(StdDraw.YELLOW);
		StdDraw.filledCircle(175, 225, 130);
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.filledCircle(125, 300, 20);
		StdDraw.filledCircle(225, 300, 20);
		StdDraw.setPenRadius(0.01);
		StdDraw.arc(175, 150, 100, 20, 160);
	}

}