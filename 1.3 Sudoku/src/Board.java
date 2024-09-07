/*
* Author: Panagiotis Tsembekis
* Written: 07/11/2023
* Last updated: 25/11/2023
*
* Compilation: javac -cp your\library\path\stdlib.jar Board.java UserChoice.java Sudoku.java
* Execution: java -cp .;your\library\path\stdlib.jar Sudoku <N> <game-file>
*
* This class performs the initialization and validation of the Sudoku tableau contained in the game file. It receives the file's name from the 
* Sudoku class and it proceeds to fill a 2D NxN array. When the fill process is done, it checks if there are missing values from the file, or if 
* the file contains more values than expected, if so the user is promted a message and the game ends. If the file contains the right amount of 
* values, the program checks if the Sudoku read from the file is a valid game, that means checking every row, column and box so that it contains 
* each number once. In the case where the Sudoku doesn't meet the game's rules the user is prompted a message and the program ends, otherwise 
* the code returns to the Sudoku class the array.
*
*/

public class Board {

	private int[][] tableau;
	private int N;

	public Board() { // default constructor initializes N=9
		N = 9;
		tableau = new int[N][N];
	}

	public Board(int N) { // constructor sets the tableau's size according to the value of N entered
		this.N = N;
		this.tableau = new int[N][N];
	}
	
	public int[][] getBoard() { // getter method to return the array to the play method in order to print it
		return tableau;
	}

	public void readBoard(String fileName) {
		In infile = new In(fileName);
		int cntLines = 0;
		while (infile.hasNextLine() && cntLines < N) {
			String line = infile.readLine();
			String[] nums = line.split("\\s+"); // split the line read from the file

			if (nums.length != N) { // if the multitude of numbers at the row read is not equals to N, then the Sudoku in the file is invalid
				if (nums.length < N) { // if it contains less numbers
					System.out.println("Error: Missing values from file!");
					System.exit(0);
				} else { // if it contains more numbers
					System.out.println("Error: Illegal number in input file!");
					System.exit(0);
				}
			}

			for (int i = 0; i < nums.length; i++) { // check every number to be withing the [0,N] range
				if (Math.abs(Integer.parseInt(nums[i])) > N) {
					System.out.println("Error: Illegal number in input file!");
					System.exit(0);
				}
			}

			for (int j = 0; j < N && j < nums.length; j++) { // fill the array
				tableau[cntLines][j] = Integer.parseInt(nums[j]);
			}
			cntLines++;
		}
		infile.close(); // close the file
		if (!checkValidity(tableau)) { // after reading the whole file, check its validity for the game rules
			System.exit(0);
		}

	}

	public boolean checkValidity(int[][] tableau) {
		// check theg game's rules (row/column/box)
		if (!isValidRow(tableau, N)) { // row check
			System.out.println("Error: This is not a valid sudoku! Same row rule not met!");
			return false;
		}

		else if (!isValidColumn(tableau, N)) { // column check
			System.out.println("Error: This is not a valid sudoku! Same column rule not met! ");
			return false;
		}

		else if (!isValidBox(tableau, N)) { // box check
			System.out.println("Error: This is not a valid sudoku! Same box rule not met! ");
			return false;
		}

		return true;
	}

	public static boolean isValidRow(int[][] tableau, int N) { // method to check row rule
		int cnt;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int num = Math.abs(tableau[i][j]);
				cnt = 0;
				for (int k = 0; k < N; k++) {
					if (num != 0 && num == Math.abs(tableau[i][k])) { // check if number appears in the row
						cnt++;
					}
				}
				if (cnt > 1) { // if we encounter the number twice or more, then the sudokou is invalid
					return false;
				}
			}
		}

		return true;
	}

	public static boolean isValidColumn(int[][] tableau, int N) { // method to check column rule
		int cnt;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int num = Math.abs(tableau[i][j]);
				cnt = 0;
				for (int k = 0; k < N; k++) {
					if (num != 0 && num == Math.abs(tableau[k][j])) { // check if number appears in the column
						cnt++;
					}
				}
				if (cnt > 1) { // if we encounter the same number twice or more, then the sudokou is invalid
					return false;
				}
			}
		}

		return true;
	}

	public static boolean isValidBox(int[][] tableau, int N) { // method to check box rule
		boolean[] seen = new boolean[10];
		for (int x = 0; x < N; x += Math.sqrt(N)) { // for every sub-box
			for (int y = 0; y < N; y += Math.sqrt(N)) {
				for (int k = 0; k < 10; k++) { // say that every number exists once in every box
					seen[k] = false;
				}
				for (int i = x; i < x + Math.sqrt(N); i++) {
					for (int j = y; j < y + Math.sqrt(N); j++) {
						int num = tableau[i][j];
						if (num != 0 && seen[Math.abs(num)]) { // if we ecounter a number that is not 0 and has been found before the game is invalid
							return false;
						}
						seen[Math.abs(num)] = true; // every time we pass through a number, we say that we have seen it
					}
				}
			}
		}

		return true;
	}

	public void displayBoard(int[][] tableau) {
		// call the correct method according to the Sudoku's size
		if (N == 4) {
			printSudoku4(tableau);
		} else if (N == 9) {
			printSudoku9(tableau);
		}
	}

	public static void printSudoku4(int[][] tableau) {
		int N = 4; // size of the Sudoku board
		int cnt = 0; // counter for formatting
		for (int i = 0; i < N; i++) {
			cnt = 0;
			// print horizontal lines for each block (every 2 rows)
			if (i == 0 || i == 2) {
				for (int k = 0; k < Math.sqrt(N); k++) {
					System.out.print("+------");
				}
				System.out.println("+");
				System.out.print("|");
			}
			for (int j = 0; j < N; j++) {
				// print values or placeholders for each cell
				if (tableau[i][j] < 0) {
					System.out.print("(" + Math.abs(tableau[i][j]) + ")");
				} else if (tableau[i][j] > 0) {
					System.out.print(" " + tableau[i][j] + " ");
				} else if (tableau[i][j] == 0) {
					System.out.print(" . ");
				}
				cnt++;
				// print vertical lines based on the Sudoku size
				if ((cnt == 2 && N == 4) || ((cnt == 3 || cnt == 6) && N == 9)) {
					System.out.print("|");
				} else if (cnt == N && (i == 0 || i == 2)) {
					System.out.println("|");
					System.out.print("|");
				} else if (cnt == N && (i == 1 || i == 3)) {
					System.out.println("|");
				}
			}
		}
		// print the final horizontal line for the Sudoku board
		for (int k = 0; k < Math.sqrt(N); k++) {
			System.out.print("+------");
		}
		System.out.println("+");
	}

	public static void printSudoku9(int[][] tableau) {
		int N = 9; // Size of the Sudoku board
		int cnt = 0; // Counter for formatting
		for (int i = 0; i < N; i++) {
			cnt = 0;
			// print horizontal lines for each block (every 3 rows)
			if (i == 0 || i == 3 || i == 6) {
				for (int k = 0; k < Math.sqrt(N); k++) {
					System.out.print("+---------");
				}
				System.out.println("+");
			}
			System.out.print("|");
			for (int j = 0; j < N; j++) {
				// print values or placeholders for each cell
				if (tableau[i][j] < 0) {
					System.out.print("(" + Math.abs(tableau[i][j]) + ")");
				} else if (tableau[i][j] > 0) {
					System.out.print(" " + tableau[i][j] + " ");
				} else if (tableau[i][j] == 0) {
					System.out.print(" . ");
				}
				cnt++;
				// print vertical lines based on the Sudoku size
				if (cnt == 3 || cnt == 6) {
					System.out.print("|");
				} else if (cnt == N) {
					System.out.print("|");
				}
			}
			System.out.println();
		}
		// print the final horizontal line for the Sudoku board
		for (int k = 0; k < Math.sqrt(N); k++) {
			System.out.print("+---------");
		}
		System.out.println("+");
	}

}
