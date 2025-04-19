package src.model;

import javax.swing.*;
import java.awt.*;

public class Square extends JComponent {
    private final Board board;
    private final int color; // 0 = dark, 1 = light
    private final int xNum, yNum; // Coordinates on the board
    private boolean displayPiece = true;
    private Piece occupyingPiece;

    public Square(Board board, int color, int xNum, int yNum) {
        this.board = board;
        this.color = color;
        this.xNum = xNum;
        this.yNum = yNum;
        setBorder(BorderFactory.createEmptyBorder());
    }

    public Square(int x, int y, Board board, int color, int xNum, int yNum) {
        this.board = board;
        this.color = color;
        this.xNum = xNum;
        this.yNum = yNum;
    }

    public int getXNum() {
        return xNum;
    }

    public int getYNum() {
        return yNum;
    }

    public int getColorValue() {
        return color;
    }

    public boolean isOccupied() {
        return occupyingPiece != null;
    }

    public Piece getOccupyingPiece() {
        return occupyingPiece;
    }

    public void setDisplay(boolean display) {
        this.displayPiece = display;
    }

    public void put(Piece piece) {
        this.occupyingPiece = piece;
        piece.setPosition(this);
    }

    public Piece removePiece() {
        Piece removed = this.occupyingPiece;
        this.occupyingPiece = null;
        return removed;
    }

    public void capture(Piece attacker) {
        Piece victim = this.occupyingPiece;
        if (victim != null) {
            if (victim.getColor() == 0) {
                board.getBlackPieces().remove(victim);
            } else {
                board.getWhitePieces().remove(victim);
            }
        }
        this.put(attacker);
    }

    public void setPiece(Piece piece) {
        this.occupyingPiece = piece;
        if (piece != null) {
            piece.setPosition(this);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw square background
        g.setColor(color == 1 ? new Color(240, 217, 181) : new Color(181, 136, 99));
        g.fillRect(0, 0, getWidth(), getHeight());

        // Draw piece image if present and display is enabled
        if (occupyingPiece != null && displayPiece) {
            occupyingPiece.draw(g);
        }
    }

    @Override
    public int hashCode() {
        return 31 * xNum + yNum;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Square other)) return false;
        return this.xNum == other.xNum && this.yNum == other.yNum;
    }
}
