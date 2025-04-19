package src.Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.model.*;

import static org.junit.jupiter.api.Assertions.*;

public class testBoard {
    private Board board;

    @BeforeEach
    public void setup() {
        board = new Board();
    }

    @Test
    public void testInitialPieceCount() {
        assertEquals(16, board.getWhitePieces().size(), "White should have 16 pieces at start");
        assertEquals(16, board.getBlackPieces().size(), "Black should have 16 pieces at start");
    }

    @Test
    public void testSquareAccess() {
        Square square = board.getSquare(0, 1);
        assertNotNull(square, "Square at (0,1) should exist");
        assertTrue(square.isOccupied(), "Square at (0,1) should have a black pawn");
        assertTrue(square.getOccupyingPiece() instanceof Pawn);
    }

    @Test
    public void testBoardDimensions() {
        Square[][] squares = board.getSquareArray();
        assertEquals(8, squares.length, "Board should have 8 rows");
        assertEquals(8, squares[0].length, "Board should have 8 columns");
    }

    @Test
    public void testCustomPiecePlacement() {
        Queen customQueen = new Queen(1, null, "wqueen.png");
        Square target = board.getSquare(4, 4);
        target.put(customQueen);

        assertEquals(customQueen, target.getOccupyingPiece(), "Queen should be placed at (4,4)");
        assertTrue(target.isOccupied());
        assertEquals(target, customQueen.getPosition());
    }

    @Test
    public void testPieceCapture() {
        // Place a black pawn on (4,4) and a white queen on (4,5), then capture
        Square target = board.getSquare(4, 4);
        Square attackerSq = board.getSquare(4, 5);

        Pawn victim = new Pawn(0, target, "bpawn.png");
        Queen attacker = new Queen(1, attackerSq, "wqueen.png");

        target.put(victim);
        attackerSq.put(attacker);

        target.capture(attacker);

        assertEquals(attacker, target.getOccupyingPiece());
        assertFalse(board.getBlackPieces().contains(victim), "Captured piece should be removed from black list");
    }

    @Test
    public void testEmptySquareAfterRemoval() {
        Square square = board.getSquare(0, 1); // Black pawn
        Piece p = square.removePiece();

        assertFalse(square.isOccupied(), "Square should be empty after removal");
        assertNull(square.getOccupyingPiece(), "Square should have no piece");
        assertNotNull(p, "Removed piece should not be null");
    }

    @Test
    public void testGetWhiteAndBlackPiecesNotNull() {
        assertNotNull(board.getWhitePieces(), "White pieces list should not be null");
        assertNotNull(board.getBlackPieces(), "Black pieces list should not be null");
    }
}
