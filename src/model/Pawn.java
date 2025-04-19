package src.model;

import java.util.LinkedList;
import java.util.List;

public class Pawn extends Piece {
    private boolean hasMoved = false;

    public Pawn(int color, Square initSquare, String imagePath) {
        super(color, initSquare, imagePath);
    }

    @Override
    public boolean move(Square destination) {
        boolean moved = super.move(destination);
        if (moved) hasMoved = true;
        return moved;
    }

    @Override
    public List<Square> getLegalMoves(Board board) {
        List<Square> legalMoves = new LinkedList<>();
        Square[][] squares = board.getSquareArray();

        int x = getPosition().getXNum();
        int y = getPosition().getYNum();
        int direction = (getColor() == 0) ? 1 : -1; // black moves down, white moves up

        // Forward move
        int oneStep = y + direction;
        if (isInBounds(x, oneStep) && !squares[x][oneStep].isOccupied()) {
            legalMoves.add(squares[x][oneStep]);

            // First move, two-step
            int twoStep = y + 2 * direction;
            if (!hasMoved && isInBounds(x, twoStep) && !squares[x][twoStep].isOccupied()) {
                legalMoves.add(squares[x][twoStep]);
            }
        }

        // Diagonal captures
        int[] dx = { -1, 1 };
        for (int i : dx) {
            int nx = x + i;
            int ny = y + direction;
            if (isInBounds(nx, ny)) {
                Square target = squares[nx][ny];
                if (target.isOccupied() && target.getOccupyingPiece().getColor() != getColor()) {
                    legalMoves.add(target);
                }
            }
        }

        return legalMoves;
    }

    @Override
    public boolean playMove(Board board, String notation) {
        return false;
    }

    private boolean isInBounds(int x, int y) {
        return x >= 0 && x < 8 && y >= 0 && y < 8;
    }
}
