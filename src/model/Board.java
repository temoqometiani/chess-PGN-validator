
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
                int color = (x + y) % 2; // 0 for dark, 1 for light
                board[x][y] = new Square(this, color, x, y);
            }
        }
    }

    private void initializePieces() {
        // Pawns
        for (int x = 0; x < 8; x++) {
            placePiece(new Pawn(1, board[x][6], "wpawn.png"), x, 6); // White
            placePiece(new Pawn(0, board[x][1], "bpawn.png"), x, 1); // Black
        }

        // Rooks
        placePiece(new Rook(1, board[0][7], "wrook.png"), 0, 7);
        placePiece(new Rook(1, board[7][7], "wrook.png"), 7, 7);
        placePiece(new Rook(0, board[0][0], "brook.png"), 0, 0);
        placePiece(new Rook(0, board[7][0], "brook.png"), 7, 0);

        // Knights
        placePiece(new Knight(1, board[1][7], "wknight.png"), 1, 7);
        placePiece(new Knight(1, board[6][7], "wknight.png"), 6, 7);
        placePiece(new Knight(0, board[1][0], "bknight.png"), 1, 0);
        placePiece(new Knight(0, board[6][0], "bknight.png"), 6, 0);

        // Bishops
        placePiece(new Bishop(1, board[2][7], "wbishop.png"), 2, 7);
        placePiece(new Bishop(1, board[5][7], "wbishop.png"), 5, 7);
        placePiece(new Bishop(0, board[2][0], "bbishop.png"), 2, 0);
        placePiece(new Bishop(0, board[5][0], "bbishop.png"), 5, 0);

        // Queens
        placePiece(new Queen(1, board[3][7], "wqueen.png"), 3, 7);
        placePiece(new Queen(0, board[3][0], "bqueen.png"), 3, 0);

        // Kings
        placePiece(new king(1, board[4][7], "wking.png"), 4, 7);
        placePiece(new king(0, board[4][0], "bking.png"), 4, 0);
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
