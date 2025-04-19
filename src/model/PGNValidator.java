package src.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Validates the syntax and logic of a PGNGame.
 */
public class PGNValidator {

    private final List<String> errors = new ArrayList<>();
    private Board board;
    private boolean whiteTurn;

    public PGNValidator() {
    }

    /**
     * Validates a parsed PGN game (syntax + move legality).
     *
     * @param game The PGNGame to validate
     * @return true if no syntax or logic errors
     */
    public boolean validate(PGNGame game) {
        errors.clear();

        List<Move> moves = game.getMoves();
        if (moves.isEmpty()) {
            errors.add("Empty game: No moves found.");
            return false;
        }

        board = new Board(); // Reset the board for each game
        whiteTurn = true;

        for (int i = 0; i < moves.size(); i++) {
            Move move = moves.get(i);
            String notation = move.getSan();

            if (!isValidSAN(notation)) {
                errors.add("Syntax error at move " + move.getMoveNumber() + ": Invalid SAN '" + notation + "'");
                return false;
            }

            if (!applyMove(notation)) {
                errors.add("Illegal move at " + move.getMoveNumber() + ": '" + notation + "'");
                return false;
            }

            whiteTurn = !whiteTurn;
        }

        return true;
    }

    private boolean isValidSAN(String move) {
        return move.matches("([KQRBN]?[a-h]?[1-8]?x?[a-h][1-8](=[QRNB])?[+#]?|O-O(-O)?)[+#]?");
    }

    /**
     * Attempts to apply the move to the board using simplified disambiguation logic.
     */
    private boolean applyMove(String notation) {
        List<Piece> pieces = whiteTurn ? board.getWhitePieces() : board.getBlackPieces();

        for (Piece piece : pieces) {
            if (piece.canHandle(notation)) {
                if (piece.playMove(board, notation)) {
                    return true;
                }
            }
        }
        return false;
    }

    public List<String> getErrors() {
        return errors;
    }
}
