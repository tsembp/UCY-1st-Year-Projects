package hw3.chess;

import java.util.Scanner;

/**
 * Test class for the chess part of the assignment.
 * 
 * It allows users to select a chess piece, its color, and position on a simulated 8x8 chessboard. Users can then
 * move the selected piece according to the rules defined for each piece type. The class utilizes the standard
 * input/output for interaction.
 * 
 * @author Panagiotis Tsembekis
 * @version 1.0
 * @since 02/04/24
 * 
 */
public class ChessTest {
	
    /**
     * The main method serves as the entry point of the application. It handles user interactions for selecting
     * a chess piece, its color, and making moves on a simulated chessboard. The process includes validating user
     * input for piece type, color, initial position, and move legality according to chess rules.
     *
     * @param args Command line arguments (not used).
     */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		// Get algrbraic name for piece
		System.out.print("> Enter algebraic name for piece: ");
		String algName = scan.nextLine();
		while(!checkAlgName(algName)) {
			System.out.println("\n> Invalid algebraic name: " + algName);
			System.out.print("  Enter algebraic name for piece: ");
			algName = scan.nextLine();
		}
		
		// Get color for piece
		System.out.print("\n> Choose color for piece: 1) White 2) Black: ");
		String colorInput = scan.nextLine();
		while(!checkColor(colorInput)) {
			System.out.println("\n> Invalid color choice: " + colorInput);
			System.out.print(" Choose color for piece: 1) White 2) Black: ");
			colorInput = scan.nextLine();
		}
		
		hw3.chess.Color color;
		if(Integer.parseInt(colorInput) == 1)
			color = hw3.chess.Color.WHITE;
		else
			color = hw3.chess.Color.BLACK;
		
		// Construct piece
		Piece piece = null;
		switch(algName.toUpperCase()) {
		case "K": piece = new King(color);
			break;
		case "Q": piece = new Queen(color);
			break;
		case "B": piece = new Bishop(color);
			break;
		case "N": piece = new Knight(color);
			break;
		case "R": piece = new Rook(color);
			break;
		case "P": piece = new Pawn(color);
			break;
		}
		
		// Get starting location
		System.out.print("\n> Enter starting position for piece (e.g. a4): ");
		String startPos = scan.nextLine();
		startPos = startPos.trim();
		while(!checkSquare(startPos)) {
			System.out.println("\n> Invalid chess square: " + startPos);
			System.out.print("  Enter starting position for piece (e.g. a4): ");
			startPos = scan.nextLine();
		}
		
		// Initialize and display board
		char[][] board = initializeBoard(startPos, piece);
		System.out.println();
		displayBoard(board);
		
		// Print the available moves
		Square[] possibleMoves = piece.movesFrom(new Square(startPos));
		System.out.println("\nAvailable moves for " + piece.fenName() + ":");
		for(int i = 0; i < possibleMoves.length; i++) {
			System.out.print(possibleMoves[i] + "|");
		}
		System.out.println("\n");
		
		// Move execution
		System.out.print("> Would you like to make a move? (yes/no): ");
		String answer = scan.nextLine();
		while(!checkAnswer(answer)) {
			System.out.println("\n> Invalid answer: " + answer);
			System.out.print("  Would you like to make a move? (yes/no): ");
			answer = scan.nextLine();
		}
		
		if(answer.trim().toLowerCase().equals("no")) {
			System.out.println("---=== Game over ===---");
			System.exit(0);
		}
		else {
			while(true) {
				System.out.print("\n> Enter target position (e.g. c4): "); // get target square/position
				String endPos = scan.nextLine();
				endPos = endPos.trim();
				if (!checkSquare(endPos) || startPos.equals(endPos)) { // check format & equality with startPos
					System.out.println("\n> Invalid input or same position. Plese try again.");
					continue;
				} else if(!checkMove(endPos, possibleMoves)) { // check if mvoe is possible
					System.out.println("\n> Move is not one of the available moves of the piece.");
					System.out.println("  Please try again.");
					continue;
				} else {
					board = executeMove(startPos, endPos, board); // execute the move
					System.out.println("Move made from " + startPos + " to " + endPos + ".");
					displayBoard(board); // print the new chess board
					
					// Calculate and print available moves from new starting position (current end position)
					possibleMoves = piece.movesFrom(new Square(endPos));
					System.out.println("\nAvailable moves for " + piece.fenName() + ":");
					for(int i = 0; i < possibleMoves.length; i++) {
						System.out.print(possibleMoves[i] + "|");
					}
					System.out.println();
					
					System.out.print("\n> Would you like to make a move? (yes/no): "); // ask for new move
					String ans = scan.nextLine();
					while(!checkAnswer(ans)) {
						System.out.println("\n> Invalid answer: " + ans);
						System.out.print("  Would you like to make a move? (yes/no): ");
						ans = scan.nextLine();
					}
					
					if(ans.trim().toLowerCase().equals("yes"))
						continue;
					else {
						System.out.println("\n---=== Game over ===---");
						System.exit(0);
					}
				}
				
			}
		}
		
		scan.close();
		
	}
	
    /**
     * Initializes the chessboard with a specified piece at a given position. All other squares are marked as empty.
     *
     * @param startPos The starting position of the piece on the chessboard, in algebraic notation (e.g., "e4").
     * @param piece The chess piece to be placed on the board.
     * @return A 2D character array representing the initial state of the chessboard.
     */
	private static char[][] initializeBoard(String startPos, Piece piece) {
		char board[][] = new char[8][8];
		int startRow = 8 - Character.getNumericValue(startPos.charAt(1));
		int startCol = startPos.charAt(0) - 'a';
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				if(i == startRow && j == startCol) // find the piece's position and put it there
					board[i][j] = piece.fenName().charAt(0);
				else
					board[i][j] = '.'; // initialize all other squares with '.'
			}
		}
		
		return board;
	}
	
    /**
     * Displays the current state of the chessboard, including the positions of pieces and empty squares.
     *
     * @param board A 2D character array representing the current state of the chessboard.
     */
	private static void displayBoard(char[][] board) {
		System.out.println("----------------");
		for (int r = 0; r < 8; r++) {
			for (int c = 0; c < 8; c++) {
				System.out.print(board[r][c] + " ");
			}
			System.out.println("|" + (8 - r)); // print the row's index
		}
		System.out.println("----------------");
		System.out.println("a b c d e f g h"); // print column indexes
	}
	
    /**
     * Validates the algebraic name of a chess piece entered by the user.
     *
     * @param input The algebraic name of the chess piece (e.g., "K" for king).
     * @return {@code true} if the input is a valid algebraic name, {@code false} otherwise.
     */
	private static boolean checkAlgName(String input) {
		if(input.trim().length() != 1) return false;
		
		// Check if algebraic name is either K,Q,R,B,N,P or their lowercase value
		else if(!input.trim().matches("K|Q|R|B|N|P") && !input.trim().matches("k|q|r|b|n|p"))
			return false;
		
		return true;
	}
	
    /**
     * Validates the user's choice of color for the chess piece.
     *
     * @param colorInput The user's input for the piece color, expected to be "1" for white or "2" for black.
     * @return {@code true} if the input is a valid color choice, {@code false} otherwise.
     */
	private static boolean checkColor(String colorInput) {
		if(colorInput.trim().length() != 1) return false;
		
		// check if colorInput is an integer with the specified regex
		else if (!colorInput.matches("-?\\d+")) return false;
		
		// if colorInput is not 1 for white or 2 for black, the value is invalid
		else if(Integer.parseInt(colorInput) != 1 && Integer.parseInt(colorInput) != 2) return false;
		
		return true;
	}
	
    /**
     * Validates the user's response to yes/no questions.
     *
     * @param input The user's input response to a yes/no question.
     * @return {@code true} if the input is a valid response, {@code false} otherwise.
     */
	private static boolean checkAnswer(String input) {
		// answer should only be yes or no
		if(!input.trim().toLowerCase().equals("yes") && !input.trim().toLowerCase().equals("no")) return false;
		
		return true;
	}
	
    /**
     * Validates the notation of a chessboard square entered by the user.
     *
     * @param input The notation of the chessboard square (e.g., "a1").
     * @return {@code true} if the input is a valid square notation, {@code false} otherwise.
     */
	private static boolean checkSquare(String input) {
		// check if square given is [letter] from a-h and [number] from 1-8
		String square = input.trim();
		if (square.length() != 2)
			return false;
		else if (square.charAt(0) < 'a' || square.charAt(0) > 'h' || square.charAt(1) < '1' || square.charAt(1) > '8')
			return false;
		else
			return true;
	}
		
    /**
     * Validates whether a move is possible based on the available moves for a piece.
     *
     * @param endPos The target position for the move, in algebraic notation.
     * @param possibleMoves An array of available moves for the piece.
     * @return {@code true} if the move is among the available moves, {@code false} otherwise.
     */
	private static boolean checkMove(String endPos, Square[] possibleMoves) {
		for(Square square : possibleMoves) {
			// find is end position is in array of possible moves
			if(square.toString().equalsIgnoreCase(endPos)) {
				return true;
			}
		}
		
		return false;
	}
	
    /**
     * Executes a move on the chessboard, updating the positions of the piece and empty squares accordingly.
     *
     * @param startPos The starting position of the piece, in algebraic notation.
     * @param endPos The target position of the piece, in algebraic notation.
     * @param board The current state of the chessboard.
     * @return The updated state of the chessboard after the move.
     */
	private static char[][] executeMove(String startPos, String endPos, char[][] board) {
		int startRow = 8 - Character.getNumericValue(startPos.charAt(1));
		int startCol = startPos.charAt(0) - 'a';
		int endRow = 8 - Character.getNumericValue(endPos.charAt(1));
		int endCol = endPos.charAt(0) - 'a';
		
		// swap the elements of the board array (piece with blank space)
		board[endRow][endCol] = board[startRow][startCol];
		board[startRow][startCol] = '.';

		return board;
	}

}
