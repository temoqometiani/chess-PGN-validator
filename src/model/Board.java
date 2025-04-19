
package src.model;

import java.util.LinkedList;

/**
 * Represents an 8x8 chess board with squares and active pieces.
 */
public class Board {
    private final Square[][] board;
    private final LinkedList<Piece> whitePieces;
    private final LinkedList<Piece> blackPieces;

    public Board() {
        board = new Square[8][8];
        whitePieces = new LinkedList<>();
        blackPieces = new LinkedList<>();
        initializeSquares();
        initializePieces();
    }

    private void initializeSquares() {
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                board[x][y] = new Square(x, y);
            }
        }
    }

    private void initializePieces() {
        // Pawns
        for (int x = 0; x < 8; x++) {
            placePiece(new Pawn(1), x, 6); // White pawns
            placePiece(new Pawn(0), x, 1); // Black pawns
        }

        // Rooks
        placePiece(new Rook(1), 0, 7);
        placePiece(new Rook(1), 7, 7);
        placePiece(new Rook(0), 0, 0);
        placePiece(new Rook(0), 7, 0);

        // Knights
        placePiece(new Knight(1), 1, 7);
        placePiece(new Knight(1), 6, 7);
        placePiece(new Knight(0), 1, 0);
        placePiece(new Knight(0), 6, 0);

        // Bishops
        placePiece(new Bishop(1), 2, 7);
        placePiece(new Bishop(1), 5, 7);
        placePiece(new Bishop(0), 2, 0);
        placePiece(new Bishop(0), 5, 0);

        // Queens
        placePiece(new Queen(1), 3, 7);
        placePiece(new Queen(0), 3, 0);

        // Kings
        placePiece(new king(1), 4, 7);
        placePiece(new king(0), 4, 0);
    }

    private void placePiece(Piece piece, int x, int y) {
        Square square = board[x][y];
        square.setPiece(piece);
        piece.setPosition(square);

        if (piece.getColor() == 1) whitePieces.add(piece);
        else blackPieces.add(piece);
    }

    public Square getSquare(int x, int y) {
        return board[x][y];
    }

    public Square[][] getBoardMatrix() {
        return board;
    }

    public LinkedList<Piece> getWhitePieces() {
        return whitePieces;
    }

    public LinkedList<Piece> getBlackPieces() {
        return blackPieces;
    }

    public Square[][] getSquareArray() {
        return board;
    }
}
