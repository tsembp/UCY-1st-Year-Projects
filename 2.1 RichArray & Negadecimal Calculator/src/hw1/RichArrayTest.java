package hw1;

/**
 * This class serves as the main class for testing the {@link RichArray} class
 * functionality. It demonstrates various operations on RichArray objects and
 * prints the results.
 * 
 * @author Panagiotis Tsembekis
 * @version 1.0
 * @since 15/02/24
 */
public class RichArrayTest {

	/**
	 * The main method to execute the {@link RichArray} testing.
	 *
	 * @param args The command-line arguments (not used in this application).
	 */
	public static void main(String[] args) {
		// Demonstrate various tests for the RichArray methods
		System.out.println("============================");
		int[] reverse = { 3, 1, 4, 1, 6 };
		RichArray reverseTest = new RichArray(reverse);
		System.out.println("Reverse Method (1D/2D array) " + "\n" + reverseTest.reverse());
		int[][] reverse2d = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 } };
		RichArray reverse2dTest = new RichArray(reverse2d);
		System.out.print(reverse2dTest.reverse());
		System.out.println("============================");
		int[][] rotateRight = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 } };
		RichArray rotateRightTest = new RichArray(rotateRight);
		System.out.print("Rotate Right Method" + "\n" + rotateRightTest.rotateRight());
		System.out.println("============================");
		int[][] rotateLeft = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 } };
		RichArray rotateLeftTest = new RichArray(rotateLeft);
		System.out.print("Rotate Left Method" + "\n" + rotateLeftTest.rotateLeft());
		System.out.println("============================");
		int[][] transpose = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 } };
		RichArray transposeTest = new RichArray(transpose);
		System.out.print("Transpose Method" + "\n" + transposeTest.transpose());
		System.out.println("============================");
		int[] ravel = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };
		int n = 4;
		RichArray ravelTest = new RichArray(ravel);
		ravelTest = ravelTest.ravel(n);
		System.out.print("Ravel Method (n=" + n + ")" + "\n" + ravelTest);
		System.out.println("============================");
		int[][] unravel = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 } };
		RichArray unravelTest = new RichArray(unravel);
		System.out.print("Unravel Method" + "\n" + unravelTest.unravel());
		System.out.println("============================");
		int[][] reshape = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 } };
		n = 6;
		RichArray reshapeTest = new RichArray(reshape);
		reshapeTest = reshapeTest.reshape(n);
		System.out.print("Reshape Method (n=" + n + ")" + "\n" + reshapeTest);
		System.out.println("============================");
		int[][] joinLeft = { { 1, 2, 3 }, { 4, 5, 6 } };
		int[][] joinRight = { { 10, 20, 30, 40 }, { 50, 60, 70, 80 } };
		RichArray joinTestLeft = new RichArray(joinLeft);
		RichArray joinTestRight = new RichArray(joinRight);
		RichArray joinTest = joinTestLeft.join(joinTestRight);
		System.out.print("Join Method" + "\n" + joinTest);
		System.out.println("============================");
		int[][] stackTop = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 } };
		int[] stackBottom = { 10, 20, 30, 40 };
		RichArray stackTestTop = new RichArray(stackTop);
		RichArray stackTestBottom = new RichArray(stackBottom);
		RichArray stackTest = stackTestTop.stack(stackTestBottom);
		System.out.print("Stack Method" + "\n" + stackTest);
		System.out.println("============================");
		int[][] slice = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 } };
		int firstRow = 1, lastRow = 2, firstColumn = 2, lastColumn = 3;
		RichArray sliceTest = new RichArray(slice);
		sliceTest = sliceTest.slice(firstRow, lastRow, firstColumn, lastColumn);
		System.out.print("Slice Method" + "\n" + sliceTest);
		System.out.println("============================");
		int[][] replaceOriginal = { { 1, 2, 3, 4, 5 }, { 6, 7, 8, 9, 10 }, { 11, 12, 13, 14, 15 },
				{ 16, 17, 18, 19, 20 } };
		int[][] replaceAt = { { 55, 66, 77 }, { 88, 99, 100 } };
		int row = 1, column = 2;
		RichArray replaceTestOriginal = new RichArray(replaceOriginal);
		RichArray replaceTestAt = new RichArray(replaceAt);
		RichArray replaceTest = replaceTestOriginal.replace(replaceTestAt, row, column);
		System.out.print("Replace Method" + "\n" + replaceTest);
		System.out.println("============================");

	}

}
