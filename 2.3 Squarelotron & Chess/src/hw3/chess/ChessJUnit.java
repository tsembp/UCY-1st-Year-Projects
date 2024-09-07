package hw3.chess;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ChessJUnit {
	
	@Test
	public void testInvalidSquareException() {
		assertThrows(InvalidSquareException.class, () -> new Square("p1"));
	}

	@Test
	public void testAlgebraicNameAndFenNameForKnight() {
		Piece knight = new Knight(Color.BLACK);
		assertEquals("N", knight.algebraicName(), "Algebraic name should be N for a knight.");
		assertEquals("n", knight.fenName(), "FEN name should be n for a black knight.");
	}

	@Test
	public void testSquareEquality() throws InvalidSquareException {
		Square a1 = new Square("a1");
		Square otherA1 = new Square('a', '1'); // Assuming constructor Square(char, char) exists and is correct
		Square h8 = new Square("h8");

		assertTrue(a1.equals(otherA1), "Squares a1 and otherA1 should be considered equal.");
		assertFalse(a1.equals(h8), "Squares a1 and h8 should not be considered equal.");
	}
	
	@Test
	public void testPawn() {
		Piece pawn = new Pawn(Color.WHITE);
		assertEquals("P", pawn.algebraicName(), "Algebraic name should be P for pawn.");
		assertEquals("P", pawn.fenName(), "FEN name should be 'P' for white pawn.");
		Square[] actual = pawn.movesFrom(new Square("b2"));
		Square[] expected = {new Square("b3"), new Square("b4")};
		assertArrayEquals(expected, actual);
	}
	
	@Test
	public void testKing() {
		Piece king = new King(Color.BLACK);
		assertEquals("K", king.algebraicName(), "Algebraic name should be K for King.");
		assertEquals("k", king.fenName(), "FEN name should be 'k' for black King.");
		Square[] actual = king.movesFrom(new Square("e8"));
		Square[] expected = {new Square("d7"), new Square("e7"), new Square("f7"), new Square("d8"), new Square("f8")};
		Set<Square> actualSet = new HashSet<>(Arrays.asList(actual));
        Set<Square> expectedSet = new HashSet<>(Arrays.asList(expected));
        assertTrue("Actual moves should match expected moves for Queen on e2", actualSet.equals(expectedSet));
	}
	
	@Test
	public void testQueen() {
		Piece queen = new Queen(Color.WHITE);
		assertEquals("Q", queen.algebraicName(), "Algebraic name should be Q for queen.");
		assertEquals("Q", queen.fenName(), "FEN name should be 'Q' for white queen.");
		Square[] actual = queen.movesFrom(new Square("e2"));
        Square[] expected = {
                new Square("e1"), new Square("e3"), new Square("e4"), new Square("e5"), 
                new Square("e6"), new Square("e7"), new Square("e8"),
                new Square("a2"), new Square("b2"), new Square("c2"), new Square("d2"), 
                new Square("f2"), new Square("g2"), new Square("h2"),
                new Square("f1"), new Square("f3"), new Square("g4"), 
                new Square("h5"), new Square("d3"), new Square("c4"), 
                new Square("b5"), new Square("a6"), new Square("d1")
            };
        
        Set<Square> actualSet = new HashSet<>(Arrays.asList(actual));
        Set<Square> expectedSet = new HashSet<>(Arrays.asList(expected));
        assertTrue("Actual moves should match expected moves for Queen on e2", actualSet.equals(expectedSet));	
	}
	
	@Test
	public void testRook() {
	    Piece rook = new Rook(Color.WHITE);
	    assertEquals("R", rook.algebraicName(), "Algebraic name should be R for Rook.");
	    assertEquals("R", rook.fenName(), "FEN name should be 'R' for white Rook.");
	    Square[] actual = rook.movesFrom(new Square("e4"));
	    Square[] expected = {
	        new Square("e1"), new Square("e2"), new Square("e3"), new Square("e5"), 
	        new Square("e6"), new Square("e7"), new Square("e8"),
	        new Square("a4"), new Square("b4"), new Square("c4"), new Square("d4"), 
	        new Square("f4"), new Square("g4"), new Square("h4")
	    };
	    
	    Set<Square> actualSet = new HashSet<>(Arrays.asList(actual));
	    Set<Square> expectedSet = new HashSet<>(Arrays.asList(expected));
	    assertTrue("Actual moves should match expected moves for Rook on e4", actualSet.equals(expectedSet));
	}
	
	@Test
	public void testBishop() {
	    Piece bishop = new Bishop(Color.WHITE);
	    assertEquals("B", bishop.algebraicName(), "Algebraic name should be B for Bishop.");
	    assertEquals("B", bishop.fenName(), "FEN name should be 'B' for white Bishop.");
	    Square[] actual = bishop.movesFrom(new Square("d4"));
	    Square[] expected = {
	        new Square("c3"), new Square("b2"), new Square("a1"),
	        new Square("c5"), new Square("b6"), new Square("a7"),
	        new Square("e3"), new Square("f2"), new Square("g1"),
	        new Square("e5"), new Square("f6"), new Square("g7"), new Square("h8")
	    };
	    
	    Set<Square> actualSet = new HashSet<>(Arrays.asList(actual));
	    Set<Square> expectedSet = new HashSet<>(Arrays.asList(expected));
	    assertTrue("Actual moves should match expected moves for Bishop on d4", actualSet.equals(expectedSet));
	}
	
	@Test
	public void testKnight() {
	    Piece knight = new Knight(Color.WHITE);
	    assertEquals("N", knight.algebraicName(), "Algebraic name should be N for Knight.");
	    assertEquals("N", knight.fenName(), "FEN name should be 'N' for white Knight.");
	    Square[] actual = knight.movesFrom(new Square("e4"));
	    Square[] expected = {
	        new Square("d6"), new Square("f6"),
	        new Square("c5"), new Square("g5"),
	        new Square("c3"), new Square("g3"),
	        new Square("d2"), new Square("f2")
	    };
	    
	    Set<Square> actualSet = new HashSet<>(Arrays.asList(actual));
	    Set<Square> expectedSet = new HashSet<>(Arrays.asList(expected));
	    assertTrue("Actual moves should match expected moves for Knight on e4", actualSet.equals(expectedSet));
	}
	
}