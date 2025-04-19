package src.model;

import java.util.LinkedList;
import java.util.List;

public class king extends Piece {

    public king(int color, Square initSquare, String imagePath) {
        super(color, initSquare, imagePath);
    }



    @Override
    public List<Square> getLegalMoves(Board board) {
        List<Square> legalMoves = new LinkedList<>();
        Square[][] squares = board.getSquareArray();

        int x = getPosition().getXNum();
        int y = getPosition().getYNum();

        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (dx == 0 && dy == 0) continue;

                int newX = x + dx;
                int newY = y + dy;

                if (isInBounds(newX, newY)) {
                    Square target = squares[newX][newY];
                    if (!target.isOccupied() || target.getOccupyingPiece().getColor() != this.getColor()) {
                        legalMoves.add(target);
                    }
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
