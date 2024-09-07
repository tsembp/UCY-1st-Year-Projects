/*
* Author: Panagiotis Tsembekis 
* Written: 02/10/2023 
* Last updated: 07/10/2023
*
* Compilation command: javac DNASequencer.java 
* Execution command: java DNASequencer <input>
* 
* This Java program is designed to process multiple DNA strands provided by the user. It begins by verifying 
* whether the input is empty or not. If the input is not empty, the program proceeds to identify the base of 
* the DNA molecule, if it exists. If it does, it examines whether the remaining strands in the input can be 
* combined with the base to form a single DNA molecule. If a successful merge is possible, the  program performs 
* it and outputs the resulting final DNA molecule after examining all the strands inside the Arguments. 
* 
*/

public class DNASequencer {

	private static boolean ValidityChecker(String str) { // function that checks whether the strand that has been
		for (int i = 0; i < str.length(); i++) { 		// to it is valid (contains only the characters a,c,g,t)
			char cch = str.charAt(i);
			if (cch != 'a' && cch != 'c' && cch != 't' && cch != 'g') {
				return false;
			}
		}
		return true;
	}

	public static void main(String args[]) {
		String dnaMolecule = "";
		if (args.length == 0) { // check for empty arguments/incorrect input format
			System.out.println("Wrong input!");
			System.out.println("Expected input: <strand1> <strand2> ...");
		} 
		else { // if input is not empty
		
			if (args.length == 1) { // if input contains only 1 strand, we check if it is valid
				boolean valid = true;
				valid = ValidityChecker(args[0]);
				if (valid) {
					dnaMolecule = args[0]; // if the strand is valid, it becomes our DNA molecule and we print it
					System.out.println("DNA molecule : " + dnaMolecule);
				} 
				else {
					System.out.println("Wrong input!"); // if the only strand we have is invalid we print an error message
				}
			} 
			else { // if the input contains multiple strands
				boolean foundBase = false;
				String baseDna = "";
				int baseIndex = -1, j = 0;
				while (!foundBase && j < args.length) { // we locate the first in order valid strand using our									
					if (ValidityChecker(args[j])) {		// function to use as the base of the DNA molecule
						foundBase = true;
						baseIndex = j;
						baseDna = args[j];
					} else { // if strand j is invalid, we can't use it as a base, so we increase j to move on
						j++;
					}
				}
				if (!foundBase) { // if none of the strands given are valid, there is no base to use => Wrong input
					System.out.println("Wrong input!");
				} 
				else { // if we manage to find a base, we start building the DNA molecule
					int y = baseIndex + 1;	// we use index y to continue checking the strands that come after the base
					for (int i = y; i < args.length; i++) {
						if (ValidityChecker(args[i])) { // if the strand i is valid we check whether it can be merged						
							int length0 = baseDna.length();	// with the base
							int length1 = args[i].length();
							int pos = 0, cThreshold = 0, x = 0, maxThreshold = 0, lastX = -1, lastXPos = -1;
							for (int r = length0 - 4; r >= 0; r--) { // since the threshold must be >=4 for the strands
								if (baseDna.charAt(r) == args[i].charAt(0)) {	// to be merged, we can start the for 
									pos = r;									// loop at the 4th-to-last character
									lastX = -1;
									x = 0;
									cThreshold = 0;
									while ((pos < length0 && x < length1)&& (baseDna.charAt(pos) == args[i].charAt(x))) {
										cThreshold++;
										lastX = x;	// the position of the last character in the sequence
										pos++;	// we increase pos by one to move on to the next character of the base
										x++; // we increase x by one to move on to the next character of the strand
									}
									if (cThreshold >=4 && cThreshold >= maxThreshold) {	// if the threshold is valid (>=4)
										maxThreshold = cThreshold;						// and larger than the previous max
										lastXPos = lastX;
									}
								}
							} // close for loop that holds baseDNA char positions
							if (lastXPos >= 0 && lastXPos < args[i].length()) {
								if (args[i].charAt(lastXPos) == baseDna.charAt(baseDna.length() - 1)) { //	if there are characters on the
									for (int h = lastXPos + 1; h < args[i].length(); h++) {	// strand that are excess, we add them to the end
										if (args[i].charAt(h) == ' ') {					// of the DNA molecule. ex. acccgtgca
											break;																	//  gtgcaccc => acccgtgcaccc
										}
										baseDna += args[i].charAt(h);
									}
								}
							}

						} // close if statement when strand is valid

					} // close for loop that cycles through all of the given strands

					dnaMolecule = baseDna;
					System.out.println("DNA molecule : " + dnaMolecule);

				} // close else statement for found base, build the molecule

			} // close else statement for multiple strands
		} // close else statement for not empty input
	} // main
} // public class