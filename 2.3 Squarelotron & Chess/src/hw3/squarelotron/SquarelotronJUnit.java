package hw3.squarelotron;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class SquarelotronJUnit {
	
	int[] array16 = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16};
	int[] array25 = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25};
	int[][] array4x4 = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
	int[][] array5x5 = {{1,2,3,4,5},{6,7,8,9,10},{11,12,13,14,15},{16,17,18,19,20},{21,22,23,24,25}};
	int[] invalidArr1 = {1,2,3,4,5,6,7,8,9};
	int[] invalidArr2 = {-1,-2,3,4,5,6,-7,8};
	int[] invalidArr3 = {1,2,3,4,5,6,7,8,9,10,11,102,41241,1213,141241,456};
	SmallSquarelotron smallSq = new SmallSquarelotron(array16);
	LargeSquarelotron largeSq = new LargeSquarelotron(array25);
	
	private void check2Dcontents(int[][] expected, int[][] actual) {
		assertEquals(expected.length, actual.length);
		for (int i = 0; i < expected.length; i++) {
			assertArrayEquals(expected[i], actual[i]);
		}
	}
	
	@Test
	void testSmallSquarelotronConstructor() {
		SmallSquarelotron sq = new SmallSquarelotron(array16);
		
		// Check row and column lengths
		assertEquals(4, sq.getSquarelotron().length);
		assertEquals(4, sq.getSquarelotron()[0].length);
		
		// Check squarelotron's array contents
		check2Dcontents(array4x4, sq.getSquarelotron());
	}
	
	@Test
	void testLargeSquarelotronConstructor() {
		LargeSquarelotron sq = new LargeSquarelotron(array25);
		
		// Check row and column lengths
		assertEquals(5, sq.getSquarelotron().length);
		assertEquals(5, sq.getSquarelotron()[0].length);
		
		// Check squarelotron's array contents
		check2Dcontents(array5x5, sq.getSquarelotron());
	}
	
	@Test
	void testInvalidSquarelotronArray() {
		// Test with an invalid array size
		assertThrows(IllegalArgumentException.class, () -> new SmallSquarelotron(invalidArr1));

		// Test with negative numbers in the array
		assertThrows(IllegalArgumentException.class, () -> new LargeSquarelotron(invalidArr2));
		
		// Test with >99 numbers in the array
		assertThrows(IllegalArgumentException.class, () -> new LargeSquarelotron(invalidArr3));
	}
	
	@Test
	void testUpsideDownFlip() {
		// Test for a small squarelotron for outer ring
		Squarelotron outerSmall = smallSq.upsideDownFlip("outer");

		// Verify the array's contents after the flip
		int[][] expectedOuterSmall = {{13,14,15,16},{9,6,7,12},{5,10,11,8},{1,2,3,4}};
		check2Dcontents(expectedOuterSmall, outerSmall.getSquarelotron());
		
		// Test for a large squarelotron for outer ring
		Squarelotron outerLarge = largeSq.upsideDownFlip("outer");

		// Verify the array's contents after the flip
		int[][] expectedOuterLarge = {{21,22,23,24,25},{16,7,8,9,20},{11,12,13,14,15},{6,17,18,19,10}, {1,2,3,4,5}};
		check2Dcontents(expectedOuterLarge, outerLarge.getSquarelotron());
		
		// Test for small squarelotron for inner ring
	    Squarelotron innerSmall = smallSq.upsideDownFlip("inner");
	    
		// Verify the array's contents after the flip
	    int[][] expectedInnerSmall = {{1,2,3,4},{5,10,11,8},{9,6,7,12},{13,14,15,16}};
	    check2Dcontents(expectedInnerSmall, innerSmall.getSquarelotron());
	    
	    // Test for large squarelotron for inner ring
	    Squarelotron innerLarge = largeSq.upsideDownFlip("inner");
	    
	    // Verify the array's contents after the flip
	    int[][] expectedInnerLarge = {{1,2,3,4,5},{6,17,18,19,10},{11,12,13,14,15},{16,7,8,9,20},{21,22,23,24,25}};
	    check2Dcontents(expectedInnerLarge, innerLarge.getSquarelotron());
	}

	@Test
	void testLeftRightFlip() {
		// Test for a small squarelotron for inner ring
		Squarelotron innerSmall = smallSq.leftRightFlip("inner");

		// Verify the array's contents after the flip
		int[][] expectedInnerSmall = {{1,2,3,4},{5,7,6,8},{9,11,10,12},{13,14,15,16}};
		check2Dcontents(expectedInnerSmall, innerSmall.getSquarelotron());

		// Test for a large squarelotron for inner ring
		Squarelotron innerLarge = largeSq.leftRightFlip("inner");
		
		// Verify the array's contents after the flip
		int[][] expectedInnerLarge = {{1,2,3,4,5},{6,9,8,7,10},{11,14,13,12,15},{16,19,18,17,20},{21,22,23,24,25}};
		check2Dcontents(expectedInnerLarge, innerLarge.getSquarelotron());
		
		// Test for a small squarelotron for inner ring
	    Squarelotron outerSmall = smallSq.leftRightFlip("outer");
	    
		// Verify the array's contents after the flip
	    int[][] expectedOuterSmall = {{4,3,2,1},{8,6,7,5},{12,10,11,9},{16,15,14,13}};
	    check2Dcontents(expectedOuterSmall, outerSmall.getSquarelotron());
	    
		// Test for a large squarelotron for outer ring
	    Squarelotron outerLarge = largeSq.leftRightFlip("outer");
	    
		// Verify the array's contents after the flip
	    int[][] expectedOuterLarge = {{5,4,3,2,1},{10,7,8,9,6},{15,12,13,14,11},{20,17,18,19,16},{25,24,23,22,21}};
	    check2Dcontents(expectedOuterLarge, outerLarge.getSquarelotron());
	}

	@Test
	void testInverseDiagonalFlip() {
		// Test for a small squarelotron for inner ring
		Squarelotron innerSmall = smallSq.inverseDiagonalFlip("inner");

		// Verify the array's contents after the flip
		int[][] expectedInnerSmall = {{1,2,3,4},{5,11,7,8},{9,10,6,12},{13,14,15,16}};
		check2Dcontents(expectedInnerSmall, innerSmall.getSquarelotron());

		// Test for a large squarelotron for inner ring
		Squarelotron innerLarge = largeSq.inverseDiagonalFlip("inner");

		// Verify the array's contents after the flip
		int[][] expectedInnerLarge = {{1,2,3,4,5},{6,19,14,9,10},{11,18,13,8,15},{16,17,12,7,20},{21,22,23,24,25}};
		check2Dcontents(expectedInnerLarge, innerLarge.getSquarelotron());
		
		// Test for a small squarelotron for outer ring
	    Squarelotron outerSmall = smallSq.inverseDiagonalFlip("outer");

	    // Verify the array's contents after the flip
	    int[][] expectedOuterSmall = {{16,12,8,4},{15,6,7,3},{14,10,11,2},{13,9,5,1}};
	    check2Dcontents(expectedOuterSmall, outerSmall.getSquarelotron());
	    
	    // Test for a large squarelotron for outer ring
	    Squarelotron outerLarge = largeSq.inverseDiagonalFlip("outer");

	    // Verify the array's contents after the flip
		int[][] expectedOuterLarge = {{25,20,15,10,5},{24,7,8,9,4},{23,12,13,14,3},{22,17,18,19,2},{21,16,11,6,1}};
	    check2Dcontents(expectedOuterLarge, outerLarge.getSquarelotron());
	}

	@Test
	void testMainDiagonalFlip() {
		// Test for a small squarelotron
		Squarelotron innerSmall = smallSq.mainDiagonalFlip("inner");

		// Verify the contents after the flip for small squarelotron
		int[][] expectedInnerSmall = { { 1, 2, 3, 4 }, { 5, 6, 10, 8 }, { 9, 7, 11, 12 }, { 13, 14, 15, 16 } };
		check2Dcontents(expectedInnerSmall, innerSmall.getSquarelotron());

		// Test for a large squarelotron for inner ring
		Squarelotron innerLarge = largeSq.mainDiagonalFlip("inner");

		// Verify the array's contents after the flip
		int[][] expectedInnerLarge = {{1,2,3,4,5},{6,7,12,17,10},{11,8,13,18,15},{16,9,14,19,20},{21,22,23,24,25}};
		check2Dcontents(expectedInnerLarge, innerLarge.getSquarelotron());
		
		// Test for a small squarelotron for outer ring
	    Squarelotron outerSmall = smallSq.mainDiagonalFlip("outer");

	    // Verify the array's contents after the flip
		int[][] expectedOuterSmall = {{1,5,9,13},{2,6,7,14},{3,10,11,15},{4,8,12,16}};
	    check2Dcontents(expectedOuterSmall, outerSmall.getSquarelotron());
		
	    // Test for a large squarelotron for outer ring
	    Squarelotron outerLarge = largeSq.mainDiagonalFlip("outer");

	    // Verify the array's contents after the flip
		int[][] expectedOuterLarge = {{1,6,11,16,21},{2,7,8,9,22},{3,12,13,14,23},{4,17,18,19,24},{5,10,15,20,25}};
	    check2Dcontents(expectedOuterLarge, outerLarge.getSquarelotron());
	}

	@Test
	void testSideFlip() {

	}
	
	@Test
	void testRotateRight() {
		// Test for 1 clockwise and 3 counter-clockwise, should be the same
		// For small squarelotron
		SmallSquarelotron rotatedSmall1 = new SmallSquarelotron(array16);
		SmallSquarelotron rotatedSmallNeg3 = new SmallSquarelotron(array16);
		rotatedSmall1.rotateRight(1);
		rotatedSmallNeg3.rotateRight(-3);
		int[][] expectedRotate1 = {{13,9,5,1},{14,10,6,2},{15,11,7,3},{16,12,8,4}};
		check2Dcontents(expectedRotate1, rotatedSmall1.getSquarelotron());
		check2Dcontents(expectedRotate1, rotatedSmallNeg3.getSquarelotron());
		
		// For large squarelotron
		LargeSquarelotron rotatedLarge1 = new LargeSquarelotron(array25);
		LargeSquarelotron rotatedLargeNeg3 = new LargeSquarelotron(array25);
		rotatedLarge1.rotateRight(1);
		rotatedLargeNeg3.rotateRight(-3);
		int[][] expectedRotate2 = {{21,16,11,6,1},{22,17,12,7,2},{23,18,13,8,3},{24,19,14,9,4},{25,20,15,10,5}};
		check2Dcontents(expectedRotate2, rotatedLarge1.getSquarelotron());
		check2Dcontents(expectedRotate2, rotatedLargeNeg3.getSquarelotron());
		
		// Test for 2 clockwise and 2 counter-clockwise (180 degree rotation), should be the same
		// For small squarelotron
		SmallSquarelotron rotatedSmall2 = new SmallSquarelotron(array16);
		SmallSquarelotron rotatedSmallNeg2 = new SmallSquarelotron(array16);
		rotatedSmall2.rotateRight(2);
		rotatedSmallNeg2.rotateRight(-2);
		int[][] expectedRotate3 = {{16,15,14,13},{12,11,10,9},{8,7,6,5},{4,3,2,1}};
		check2Dcontents(expectedRotate3, rotatedSmall2.getSquarelotron());
		check2Dcontents(expectedRotate3, rotatedSmallNeg2.getSquarelotron());
	
		// For large squarelotron
		LargeSquarelotron rotatedLarge2 = new LargeSquarelotron(array25);
		LargeSquarelotron rotatedLargeNeg2 = new LargeSquarelotron(array25);
		rotatedLarge2.rotateRight(2);
		rotatedLargeNeg2.rotateRight(-2);
		int[][] expectedRotate4 = {{25,24,23,22,21},{20,19,18,17,16},{15,14,13,12,11},{10,9,8,7,6},{5,4,3,2,1}};
		check2Dcontents(expectedRotate4, rotatedLarge2.getSquarelotron());
		check2Dcontents(expectedRotate4, rotatedLargeNeg2.getSquarelotron());
		
		// Test for 3 clockwise and 1 counter-clockwise, should be the same
		// For small squarelotron
		SmallSquarelotron rotatedSmall3 = new SmallSquarelotron(array16);
		SmallSquarelotron rotatedSmallNeg1 = new SmallSquarelotron(array16);
		rotatedSmall3.rotateRight(3);
		rotatedSmallNeg1.rotateRight(-1);
		int[][] expectedRotate5 = {{4,8,12,16},{3,7,11,15},{2,6,10,14},{1,5,9,13}};
		check2Dcontents(expectedRotate5, rotatedSmall3.getSquarelotron());
		check2Dcontents(expectedRotate5, rotatedSmallNeg1.getSquarelotron());
		
		// For large squarelotron
		LargeSquarelotron rotatedLarge3 = new LargeSquarelotron(array25);
		LargeSquarelotron rotatedLargeNeg1 = new LargeSquarelotron(array25);
		rotatedLarge3.rotateRight(3);
		rotatedLargeNeg1.rotateRight(-1);
		int[][] expectedRotate6 = {{5,10,15,20,25},{4,9,14,19,24},{3,8,13,18,23},{2,7,12,17,22},{1,6,11,16,21}};
		check2Dcontents(expectedRotate6, rotatedLarge3.getSquarelotron());
		check2Dcontents(expectedRotate6, rotatedLargeNeg1.getSquarelotron());		
	}
	
    @Test
    void testEquals() {
        // Test equality with itself
        SmallSquarelotron sq = new SmallSquarelotron(array16);
        assertTrue(sq.equals(sq), "A Squarelotron should be equal to itself.");

        // Test equality with another Squarelotron with the same values
        Squarelotron sqSame = new SmallSquarelotron(array16);
        assertTrue(sq.equals(sqSame) && sqSame.equals(sq), "Two Squarelotrons with the same arrays should be equal.");

        // Test inequality with another Squarelotron with different values
        int[] arrayDifferent = {12,13,10,2,5,6,1,3,15,16,11,9,7,4,8,14};
        Squarelotron sqDifferent = new SmallSquarelotron(arrayDifferent);
        assertFalse(sq.equals(sqDifferent), "Two Squarelotrons with different values should not be equal.");

        // Test inequality with null
        assertFalse(sq.equals(null), "A Squarelotron should not be equal to null.");

        // Test inequality with an object of a different type
        Object otherObject = new Object();
        assertFalse(sq.equals(otherObject), "A Squarelotron should not be equal to an object of a different type.");
        
        // Test equality with squarelotron that is the same with this but rotated
        int[] rotated16 = {4,8,12,16,3,7,11,15,2,6,10,14,1,5,9,13}; // 16array but rotated one counter-clockwise
        SmallSquarelotron rotatedSq = new SmallSquarelotron(rotated16);
		assertTrue(sq.equals(rotatedSq),
				"A Squarelotron must be equal with another squarelotron that is generated by rotatig the initial.");        
        
    }

}
