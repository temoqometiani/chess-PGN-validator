package src.model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public abstract class Piece {
    private final int color; // 0 = Black, 1 = White
    private Square currentSquare;
    private BufferedImage image;

    public Piece(int color, Square initSq, String imageFile) {
        this.color = color;
        this.currentSquare = initSq;
        try {
            this.image = ImageIO.read(getClass().getResource("/resources/" + imageFile));
        } catch (IOException | IllegalArgumentException e) {
            System.err.println("Failed to load image: " + imageFile);
        }
    }

    public boolean move(Square target) {
        Piece occupant = target.getOccupyingPiece();
        if (occupant != null && occupant.getColor() == this.color) return false;

        if (occupant != null) target.capture(this);
        currentSquare.removePiece();
        target.put(this);
        currentSquare = target;
        return true;
    }

    public Square getPosition() {
        return currentSquare;
    }

    public void setPosition(Square square) {
        this.currentSquare = square;
    }

    public int getColor() {
        return color;
    }

    public Image getImage() {
        return image;
    }

    public void draw(Graphics g) {
        if (image != null) {
            g.drawImage(image, currentSquare.getX(), currentSquare.getY(), null);
        }
    }

    protected int[] getLinearBounds(Square[][] board, int x, int y) {
        int up = 0, down = 7, left = 0, right = 7;

        for (int i = y - 1; i >= 0; i--) {
            if (board[x][i].isOccupied()) {
                up = board[x][i].getOccupyingPiece().getColor() != color ? i : i + 1;
                break;
            }
        }
        for (int i = y + 1; i < 8; i++) {
            if (board[x][i].isOccupied()) {
                down = board[x][i].getOccupyingPiece().getColor() != color ? i : i - 1;
                break;
            }
        }
        for (int i = x - 1; i >= 0; i--) {
            if (board[i][y].isOccupied()) {
                left = board[i][y].getOccupyingPiece().getColor() != color ? i : i + 1;
                break;
            }
        }
        for (int i = x + 1; i < 8; i++) {
            if (board[i][y].isOccupied()) {
                right = board[i][y].getOccupyingPiece().getColor() != color ? i : i - 1;
                break;
            }
        }

        return new int[]{up, down, left, right};
    }

    protected List<Square> getDiagonalMoves(Square[][] board, int x, int y) {
        List<Square> moves = new LinkedList<>();
        int[] dx = {-1, -1, 1, 1};
        int[] dy = {-1, 1, -1, 1};

        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            while (nx >= 0 && ny >= 0 && nx < 8 && ny < 8) {
                Square sq = board[nx][ny];
                if (!sq.isOccupied()) {
                    moves.add(sq);
                } else {
                    if (sq.getOccupyingPiece().getColor() != color) moves.add(sq);
                    break;
                }
                nx += dx[d];
                ny += dy[d];
            }
        }

        return moves;
    }

    public abstract List<Square> getLegalMoves(Board board);
}
