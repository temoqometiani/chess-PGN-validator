package src.model;

import java.util.LinkedList;
import java.util.List;

public class Rook extends Piece {

    public Rook(int color, Square initSquare, String imagePath) {
        super(color, initSquare, imagePath);
    }

    @Override
    public List<Square> getLegalMoves(Board board) {
        List<Square> legalMoves = new LinkedList<>();
        Square[][] squares = board.getSquareArray();

        int x = getPosition().getXNum();
        int y = getPosition().getYNum();

        // Move up
        for (int i = y - 1; i >= 0; i--) {
            if (addIfLegal(squares, legalMoves, x, i)) break;
        }

        // Move down
        for (int i = y + 1; i < 8; i++) {
            if (addIfLegal(squares, legalMoves, x, i)) break;
        }

        // Move left
        for (int i = x - 1; i >= 0; i--) {
            if (addIfLegal(squares, legalMoves, i, y)) break;
        }

        // Move right
        for (int i = x + 1; i < 8; i++) {
            if (addIfLegal(squares, legalMoves, i, y)) break;
        }

        return legalMoves;
    }

    @Override
    public boolean playMove(Board board, String notation) {
        return false;
    }

    private boolean addIfLegal(Square[][] board, List<Square> moves, int x, int y) {
        Square target = board[x][y];
        if (!target.isOccupied()) {
            moves.add(target);
            return false; // continue in direction
        } else if (target.getOccupyingPiece().getColor() != this.getColor()) {
            moves.add(target); // capture
        }
        return true; // stop iteration
    }
}
