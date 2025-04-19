package src.model;

/**
 * Represents a single move in a chess game parsed from PGN.
 */
public class Move {
    private final String san;      // Standard Algebraic Notation (e.g., "e4", "Nf3")
    private final int moveNumber;  // PGN move number (e.g., 1, 2, 3)
    private final boolean isWhiteMove;  // true = white move, false = black move
    private final String rawLine;  // Original line from PGN (for error reporting)

    public Move(String san, int moveNumber, boolean isWhiteMove, String rawLine) {
        this.san = san;
        this.moveNumber = moveNumber;
        this.isWhiteMove = isWhiteMove;
        this.rawLine = rawLine;
    }

    public String getSan() {
        return san;
    }

    public int getMoveNumber() {
        return moveNumber;
    }

    public boolean isWhiteMove() {
        return isWhiteMove;
    }

    public String getRawLine() {
        return rawLine;
    }

    @Override
    public String toString() {
        return (isWhiteMove ? moveNumber + ". " : "") + san;
    }
}
