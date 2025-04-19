package src.Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.model.*;

import static org.junit.jupiter.api.Assertions.*;

public class PieceTest {

    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board();
    }

    @Test
    void testPawnMoveForward() {
        Square start = board.getSquare(4, 6); // White pawn
        Piece pawn = start.getOccupyingPiece();

        Square forward = board.getSquare(4, 5);
        assertTrue(pawn.getLegalMoves(board).contains(forward));
    }

    @Test
    void testKnightJumpsOverPieces() {
        Square knightSquare = board.getSquare(1, 7); // White knight
        Piece knight = knightSquare.getOccupyingPiece();

        Square target = board.getSquare(2, 5);
        assertTrue(knight.getLegalMoves(board).contains(target));
    }

    @Test
    void testBishopDiagonalMoveBlocked() {
        Square bishopSquare = board.getSquare(2, 7);
        Piece bishop = bishopSquare.getOccupyingPiece();

        Square blockedTarget = board.getSquare(4, 5);
        assertFalse(bishop.getLegalMoves(board).contains(blockedTarget));
    }

    @Test
    void testRookHorizontalMoveBlocked() {
        Square rookSquare = board.getSquare(0, 7);
        Piece rook = rookSquare.getOccupyingPiece();

        Square blockedTarget = board.getSquare(0, 5);
        assertFalse(rook.getLegalMoves(board).contains(blockedTarget));
    }

    @Test
    void testQueenCanMoveInAllDirections() {
        Square queenSquare = board.getSquare(3, 7);
        Piece queen = queenSquare.getOccupyingPiece();

        assertNotNull(queen);
        assertTrue(queen instanceof Queen);
    }

}
