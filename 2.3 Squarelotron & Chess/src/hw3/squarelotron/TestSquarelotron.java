package hw3.squarelotron;

import java.util.Scanner;

/**
 * The {@code TestSquarelotron} class provides a text-based interface to interact with the Squarelotron game.
 * It allows users to create a Squarelotron of specified size (4x4 or 5x5), perform various flips on it, and
 * rotate it right by a number of quarter turns. The class demonstrates the functionality of the Squarelotron
 * through user input in a command-line environment.
 * 
 * @author Panagiotis Tsembekis
 * @since 31/04/24
 * @version 1.0
 */
public class TestSquarelotron {
	
	private static final Scanner scan = new Scanner(System.in);
	
    /**
     * Main method that serves as the entry point for the Squarelotron game. It handles user inputs to create
     * Squarelotrons, select and perform flip operations, and decide whether to continue or exit the game.
     * 
     * @param args Command line arguments (not used in this application).
     */
	public static void main(String[] args) {

		while (true) {
			System.out.println("> Enter squarelotron size (4x4 or 5x5): ");
			String input = scan.nextLine(); // get squarelotron size
			Squarelotron squarelotron;
			// Generate the appropriate table according to the squarelotron's size
			if ("4x4".equals(input)) {
				squarelotron = Squarelotron.makeSquarelotron(generateArray(16));
			} else if ("5x5".equals(input)) {
				squarelotron = Squarelotron.makeSquarelotron(generateArray(25));
			} else { // if given size is anything but 4x4/5x5 ask for size again
				System.out.println("\n> Invalid input. Please try again.\n");
				continue;
			}

			System.out.println("--== Initial " + input + " Squarelotron ==--"); // print initial squarelotron
			System.out.println(squarelotron);

			printFlipMenu(); // print the method selection menu

			String methodIndex = scan.nextLine().trim(); // get method selelection index
			while (!methodIndex.matches("[1-6]")) { // if number is outside of the range [1,6] -> invalid input
				System.out.println("\n> Wrong method selection. Please try again.\n");
				printFlipMenu();
				methodIndex = scan.nextLine(); // re-ask for input
			}
			
			System.out.println("");
			
			// Find which flip will be executed
			switch(methodIndex) {
			case "6": handleRotate(squarelotron);
				break;
			case "5": handleSideFlip(squarelotron);
				break;
			default: handleOtherFlips(squarelotron, methodIndex);
				break;
			}
			
			while(true) {
				System.out.println("> Do you wish to continue with a new Squarelotron (yes/no)? ");
				String answer = scan.nextLine().trim().toLowerCase();
				if("yes".equals(answer)) // if user wishes to play again, break out of the while loop and return to the initial one
					break;
				else if("no".equals(answer)) { // exit the game
					System.out.println("\n--== Thank you for playing ==--");
					return;
				} else{ // wrong input handle
					System.out.println("> Wrong answer. Please try again.");
				}
			}
			
		}

	}
	
    /**
     * Handles the rotation of the Squarelotron to the right by a specified number of quarter turns.
     * 
     * @param squarelotron The Squarelotron object to rotate.
     */
	private static void handleRotate(Squarelotron squarelotron) {
		System.out.println("> Enter number of rotations: "); // get number of rotations
		while(!scan.hasNextInt()) {
			System.out.println("\n> Invalid number of rotations. Enter number of rotations:");
			scan.next();
		}
		int rotations = scan.nextInt();
		scan.nextLine();
		squarelotron.rotateRight(rotations);
		System.out.println("\n --== Result Squarelotron ==--"); // print the resulting squarelotron
		System.out.println(squarelotron);
	}
	
    /**
     * Handles the side flip of the Squarelotron based on user input for the side to be flipped.
     * 
     * @param squarelotron The Squarelotron object to perform the side flip on.
     */
	private static void handleSideFlip(Squarelotron squarelotron) {
		System.out.println("> Enter side (top/bottom/left/right): "); // get side for sideflip
		String side = scan.nextLine().trim().toLowerCase();
		while(!side.matches("top|bottom|left|right")) { // if side is not one of the valid ones, ask again
			System.out.println("> Invalid side entered. Enter side (top/bottom/left/right): ");
			side = scan.nextLine().trim().toLowerCase();
		}
		System.out.println("\n --== Result Squarelotron ==--"); // print the resulting squarelotron
		System.out.println(squarelotron.sideFlip(side));
	}
	
    /**
     * Handles the flips that are not side flips, based on the user's selection of flip type and ring.
     * 
     * @param squarelotron The Squarelotron object to perform the flip on.
     * @param methodIndex The index of the flip method selected by the user.
     */
	private static void handleOtherFlips(Squarelotron squarelotron, String methodIndex) {
		System.out.println("> Enter ring (outer/inner): "); // get ring for upsideDown, leftRight,... flips
		String ring = scan.nextLine().trim().toLowerCase();
		while(!ring.matches("inner|outer")) { // if the ring is not outer or inner, ask again
			System.out.println("> Invalid ring. Please try again. Enter ring (outer/inner): ");
			ring = scan.nextLine().trim().toLowerCase();
		}
		executeFlip(squarelotron, methodIndex, ring); // execute the appropriate flip according to the index
	}
	
    /**
     * Executes the selected flip operation on the Squarelotron based on the method index and the ring selected.
     * 
     * @param squarelotron The Squarelotron object to perform the flip on.
     * @param methodIndex The index of the flip method selected by the user.
     * @param ring The selected ring ("inner" or "outer") on which the flip is to be performed.
     */
	private static void executeFlip(Squarelotron squarelotron, String methodIndex, String ring) {
		System.out.println("\n --== Result Squarelotron ==--");
		switch(methodIndex) {
		case "1": System.out.println(squarelotron.upsideDownFlip(ring)); // execute upside-down flip
			break;
		case "2": System.out.println(squarelotron.leftRightFlip(ring)); // execute left-right flip
			break;
		case "3": System.out.println(squarelotron.inverseDiagonalFlip(ring)); // execute inverse-diagonal flip
			break;
		case "4": System.out.println(squarelotron.mainDiagonalFlip(ring)); // execute main-diagonal flip
			break;
		}
	}
	
    /**
     * Generates an array of integers to populate a Squarelotron, with values starting from 1 up to the specified size.
     * 
     * @param size The total number of elements in the Squarelotron (e.g., 16 for a 4x4 Squarelotron).
     * @return An array of integers to be used for initializing a Squarelotron.
     */
	private static int[] generateArray(int size) {
		int[] array = new int[size];
		for (int i = 0; i < size; i++) {
			array[i] = i + 1; // for elements 1 - size
		}

		return array;
	}
	
    /**
     * Prints the menu for selecting the type of flip operation to perform on the Squarelotron.
     */
	private static void printFlipMenu() {
		System.out.println("> Select flip type (1-6)");
		System.out.println("  1. Upside-Down Flip" + "\n" + "  2. Left-Right Flip" + "\n" + "  3. Inverse Diagonal Flip"
				+ "\n" + "  4. Main Diagonal Flip" + "\n" + "  5. Side Flip" + "\n" + "  6. Rotate Right");
		System.out.print("Selection: ");
	}

}
