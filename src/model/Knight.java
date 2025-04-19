package src.model;

import java.util.LinkedList;
import java.util.List;

public class Knight extends Piece {

    public Knight(int color, Square initSquare, String imagePath) {
        super(color, initSquare, imagePath);
    }

    @Override
    public List<Square> getLegalMoves(Board board) {
        List<Square> legalMoves = new LinkedList<>();
        Square[][] squares = board.getSquareArray();

        int x = getPosition().getXNum();
        int y = getPosition().getYNum();

        int[][] moves = {
                {2, 1}, {1, 2}, {-1, 2}, {-2, 1},
                {-2, -1}, {-1, -2}, {1, -2}, {2, -1}
        };

        for (int[] move : moves) {
            int newX = x + move[0];
            int newY = y + move[1];

            if (isInBounds(newX, newY)) {
                Square target = squares[newX][newY];
                if (!target.isOccupied() || target.getOccupyingPiece().getColor() != this.getColor()) {
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
        return x >= 0 && y >= 0 && x < 8 && y < 8;
    }
}
