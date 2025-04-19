package src.model;

import java.util.LinkedList;
import java.util.List;

public class Bishop extends Piece {

    public Bishop(int color, Square initSquare, String imagePath) {
        super(color, initSquare, imagePath);
    }

    @Override
    public List<Square> getLegalMoves(Board board) {
        List<Square> legalMoves = new LinkedList<>();
        Square[][] squares = board.getSquareArray();

        int x = getPosition().getXNum();
        int y = getPosition().getYNum();

        int[][] directions = {
                {1, 1}, {-1, 1}, {-1, -1}, {1, -1}
        };

        for (int[] dir : directions) {
            int dx = dir[0];
            int dy = dir[1];
            int newX = x + dx;
            int newY = y + dy;

            while (isInBounds(newX, newY)) {
                Square target = squares[newX][newY];
                if (target.isOccupied()) {
                    if (target.getOccupyingPiece().getColor() != this.getColor()) {
                        legalMoves.add(target);
                    }
                    break;
                }
                legalMoves.add(target);
                newX += dx;
                newY += dy;
            }
        }

        return legalMoves;
    }

    @Override
    public boolean playMove(Board board, String notation) {
        return false;
    }

    private boolean isInBounds(int x, int y) {
        return x >= 0 && y >= 0 && x < 8 && y < 8;
    }
}
