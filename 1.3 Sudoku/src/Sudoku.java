/*
* Author: Panagiotis Tsembekis
* Written: 07/11/2023
* Last updated: 25/11/2023
*
* Compilation: javac -cp your\library\path\stdlib.jar Board.java UserChoice.java Sudoku.java
* Execution: java -cp .;your\library\path\stdlib.jar Sudoku <N> <game-file>
*
* This class performs the basic execution of the Sudoku. Firstly, it receives from the arguments two values, the first one corresponds to the
* Sudoku's size and the second one is a .txt file that contains a Sudoku game. The value for the Sudoku's size can only be 4 or 9, any other values
* are rejected by the program. The program later proceeds with creating a NxN 2D array with the help of the Board Class and then calls the play 
* method to start playing the game. The user enters his input and the program takes it as an object of the UserChoice class. The program checks the
* validity of the input's format and also if the i,j,val values are within the accepted bounds. While the user plays the game, the program checks
* if every move meets the game's rules. If the user manages to solve the game, the solved Sudoku is printed along side a message. The user also has
* the choice to end the game and save his progress to a .txt file to continue another time. 
* 
*/

public class Sudoku {

	public static int N;

	public static void main(String[] args) {

		if (args.length != 2) { // incorrect arguments input (less or more content)
			System.out.println("Please give the dimension N followed by a <game-file> as the only 2 arguments");
			System.exit(0);
		}

		N = Integer.parseInt(args[0]);
		String inFileName = args[1];

		if (N != 4 && N != 9) { // check if the given sudoku size is not 4 or 9
			System.out.println("The allowed value for N is either 4 or 9!");
			System.out.println("Please re run the program with a valid value for N");
			System.exit(0);
		}

		Board sudoku = new Board(N); // create an object for the Board class
		sudoku.readBoard(inFileName); // read, check and fill the sudoku tableau with the file's content
		play(sudoku, inFileName); // call the method that is responsible for the execution of the game

	}

	public static void play(Board sudoku, String inFileName) {
		UserChoice choice = new UserChoice();
		boolean solved = false;
		while (!solved) {
			int tableau[][] = sudoku.getBoard();
			sudoku.displayBoard(tableau);
			System.out.println("Enter your command in the following format:");
			System.out.println("+ i,j=val: for entering val at position (i,j)");
			System.out.println("+ i,j=0 : for clearing cell (i,j)");
			System.out.println("+ 0,0=0 : for saving and ending the game");
			System.out.println("Notice: i, j, val numbering is from [1.." + N + "]");
			System.out.print(">");
			getUserInput(choice); // get the user's input as an object of the UserChoice class
			int i = choice.getRow();
			int j = choice.getColumn();
			int val = choice.getValue();
			if (i == -1 && j == -1 && val == -1) { // if input is incorrect, set i, j and val to -1 and ask for new input
				continue;
			} else { // if the input format and numbers given are valid proceed with the execution of the user's choice

				if (i == 0 && j == 0 && val == 0) { // 0,0=0 => Exit and print tableau to output file
					// create the output file
					String tempFile = "";
					int k = 0;
					while (inFileName.charAt(k) != '.') { // get the name of the file without the .txt ending
						tempFile += inFileName.charAt(k);
						k++;
					}
					String outFileName = "out-" + tempFile + ".txt";
					Out outfile = new Out(outFileName);
					System.out.println("0,0=0");
					System.out.println("Saving game to " + outFileName);
					System.out.println("bye!");
					for (int x = 0; x < N; x++) { // print the integers in the output file
						for (int y = 0; y < N; y++) {
							outfile.print(tableau[x][y] + " ");
						}
						outfile.println();
					}
					outfile.close(); // close the file and end the program
					System.exit(0);
				} else if (i > 0 && j > 0 && val != 0) { // (int),(int)=(int)
					if (tableau[i - 1][j - 1] == 0) { // cell is blank
						tableau[i - 1][j - 1] = val; // insert the value and check for game rules (row/col/box)
						if (!Board.isValidRow(tableau, N)) { // row doesn't meet game's rules
							System.out.println("Error: Illegal value insertion! Same row rule not met!");
							tableau[i - 1][j - 1] = 0;
						} else if (!Board.isValidColumn(tableau, N)) { // column doesn't meet game's rules
							System.out.println("Error: Illegal value insertion! Same column rule not met!");
							tableau[i - 1][j - 1] = 0;
						} else if (!Board.isValidBox(tableau, N)) { // box doesn't meet game's rules
							System.out.println("Error: Illegal value insertion! Same box rule not met!");
							tableau[i - 1][j - 1] = 0;
						} else { // if all rules are met print the according message
							System.out.println("Value Inserted!");
						}
					} else if (tableau[i - 1][j - 1] < 0 || tableau[i - 1][j - 1] != 0) { // cell is occupied
						System.out.println("Error: cell is already occupied!");
					}
				} else if (i > 0 && j > 0 && val == 0) { // (int),(int)=0 clear cell value
					if (tableau[i - 1][j - 1] >= 0) { // user clears value entered by him
						tableau[i - 1][j - 1] = 0;
						System.out.println("Value cleared!");
					} else if (tableau[i - 1][j - 1] < 0) { // if user tries to clear given value
						System.out.println("Error: can't clear given number value!");
					}
				}
			} 

			if (isSolution(tableau)) { // if the board is solved print a message along with the board and end the program
				solved = true;
				System.out.println("Game completed!!!");
				sudoku.displayBoard(tableau);
			}

		}

	}

	public static void getUserInput(UserChoice choice) {
		String input = StdIn.readLine(); 
		if (!isValidFormat(input)) { // check the validity of the format read
			System.out.println("Error: wrong format of command!");
			choice.setChoice(-1, -1, -1); // if input is incorrect set the values of i,j and val to -1
			return;
		}

		String stringValues[] = input.split("[,=]"); // split the input in 3 parts to get the i,j and val values
		int values[] = new int[3];
		for (int i = 0; i < stringValues.length; i++) { // enter the i,j and val in a integer array
			values[i] = Integer.parseInt(stringValues[i]);
		}

		if (!numberBounds(values[0], values[1], values[2])) { // check if every value is within the allowed bounds 
			System.out.println("Error: i,j or val are outside the allowed range [1.." + N + "]!");
			choice.setChoice(-1, -1, -1); // if the values are out of bounds set them to -1
			return;
		} else {
			choice.setChoice(values[0], values[1], values[2]); // set the i,j,val values to the row,col and val
		}
	}

	public static boolean isInteger(String part) { // helper method for isValidFormat
		for (int i = 0; i < part.length(); i++) {
			if (!(part.charAt(i) >= '0' && part.charAt(i) <= '9')) { // checking if the character is a number or not
				return false;
			}
		}
		return true;
	}

	public static boolean isValidFormat(String input) {
		// check if input contains spaces
		for (int i = 0; i < input.length(); i++) {
			if (input.charAt(i) == ' ') {
				return false;
			}
		}
		// count how many , and = there are
		int cntComma = 0;
		int cntEquals = 0;
		for (int i = 0; i < input.length(); i++) {
			if (input.charAt(i) == ',') {
				cntComma++;
			}
			if (input.charAt(i) == '=') {
				cntEquals++;
			}
		}
		if (cntComma > 1 || cntEquals > 1) { // if the input contains , or = more than once -> incorrect input format
			return false;
		}

		String parts[] = input.split("="); // split the input at the = character
		if (parts.length != 2) {
			return false;
		}

		String integers[] = parts[0].split(",");
		if (integers.length != 2) {
			return false;
		}
		// check if the parts contain only numbers and not other special characters
		if (!isInteger(parts[1]) || !isInteger(integers[0]) || !isInteger(integers[1])) { // check if all the parts are integers and do not contain other special characters
			return false;
		}

		return true;
	}

	public static boolean numberBounds(int i, int j, int val) {
		if ((i < 0 || i > N) || (j < 0 || j > N) || (val < 0 || val > N)) { // check whether the number entered is negative or greater than N
			return false;
		}

		return true;
	}

	public static boolean isSolution(int[][] tableau) {
		// to see if the sudoku is solved, we just need to check if there are any empty cells remaining
		// since every time the user enters a new number we check whether the tableau corresponds to a valid sudoku game
		// so there is no need to check for the validity of the game again
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (tableau[i][j] == 0) { // if we find an empty cell we let the play method know that the Sudoku is unsolved
					return false;
				}
			}
		}

		return true;
	}
}