/**
* Author: Panagiotis Tsembekis
* Written: 12/10/2023
* Last updated: 30/10/2023
*
* Compilation: javac -cp your/library/path/stdlib.jar NPuzzle.java NPuzzleLib.java
* Execution: java -cp .;your/library/path/stdlib.jar NPuzzle <input for N> <input for d>
*
* This program is centered around a Puzzle game that can be experienced either through 
* the console or with graphical elements. Users have the option to solve the puzzle
* interactively or let the program solve it automatically. If a user chooses to tackle
* the puzzle themselves, they can select a difficulty level that determines how many random 
* moves the '0' (or blank square) makes within the game board. Once the puzzle is shuffled, the 
* challenge begins as the user attempts to solve it by moving the '0' in various directions—up, 
* down, left, or right. Throughout the game, the program tracks the user's moves and displays them 
* at the end. Alternatively, for those who prefer an automated game, the user specifies the minimum 
* and maximum difficulty levels, the maximum number of moves, and the number of times each difficulty 
* level should be played. In this mode, the program undertakes the task of solving the puzzle randomly. 
* Upon completing the last difficulty level, it presents the user with their win percentage and the average 
* number of moves per game.
*
*/

public class NPuzzle {
	
	public static void main(String[] args) {
		int N,d,playMethod=-1;
		N = Integer.parseInt(args[0]);
		int arr[][] = new int[N][N];
		d = Integer.parseInt(args[1]);
		
		arr = NPuzzleLib.initializePuzzle(N);
		
		if(d== 0) { // no ouput option
			playMethod = NPuzzleLib.playTypeMenu();
			if(playMethod == 1) {
				int k = NPuzzleLib.difficultyMenu();
				NPuzzleLib.play(arr, k, N, d);
			}
			else if(playMethod == 2) { // automatic play
				int kmin,kmax,p,q;
				System.out.print("Dwse kmin: "); // take automatic play inputs from user
				kmin = StdIn.readInt();
				System.out.print("Dwse kmax: ");
				kmax =  StdIn.readInt();
				System.out.print("Dwse p: ");
				p = StdIn.readInt();
				System.out.print("Dwse q: ");
				q = StdIn.readInt();
				NPuzzleLib.automaticPlay(arr, kmin, kmax, p, q, N, d);
			}
			else if(playMethod == 3) {
				System.out.println("GAME OVER!");
			}
		} // end of no output
		
		else if(d == 1) { // text-based output 
			playMethod = NPuzzleLib.playTypeMenu();
			if(playMethod == 1) {
				int k = NPuzzleLib.difficultyMenu();
				NPuzzleLib.play(arr, k, N, d);
			}
			else if(playMethod == 2) { // automatic play
				int kmin,kmax,p,q;
				System.out.print("Dwse kmin: "); // take automatic play inputs from user
				kmin = StdIn.readInt();
				System.out.print("Dwse kmax: ");
				kmax =  StdIn.readInt();
				System.out.print("Dwse p: ");
				p = StdIn.readInt();
				System.out.print("Dwse q: ");
				q = StdIn.readInt();
				NPuzzleLib.automaticPlay(arr, kmin, kmax, p, q, N, d);
			}
			else if(playMethod == 3) { // if the user exited the game
				System.out.println("GAME OVER!");
			}
		} // end of text-based output
		
		else if(d == 2) { // StdDraw Bonus part
			int index = NPuzzleLib.mainHub(N);
			int inputs[] = new int[4]; // we use a 1D array with 4 cells to keep inside all our automatic play inputs (kmin,kmax,p,q)
			if(index == 1) { // we use index to determine if the user has selected PLAY or EXIT
				playMethod = NPuzzleLib.playMenuGraph();
				if(playMethod == 1) { // interactive play
					int k = NPuzzleLib.difficultySelection();
					NPuzzleLib.initializePuzzle(N);
					NPuzzleLib.shufflePuzzle(arr, k);
					NPuzzleLib.interactiveGraphicsPlay(arr, N);
				} // end of interactive play
				else if(playMethod == 2) { // automatic play
					inputs = NPuzzleLib.automaticSettingsMenu(arr, N);
					NPuzzleLib.automaticGraphicsPlay(arr, inputs[0], inputs[1], inputs[2], inputs[3], N);
				}// end of automatic play
				else { // if the user exits while playing the game we prompt him with the exit screen
					NPuzzleLib.exitScreen();
				}
			}
			else { // if the user wishes to exit we prompt him with the exit screen of the game
				NPuzzleLib.exitScreen();
			}
		}// end of graphics output
		
	}
}