package src.controller;

import src.model.*;
import src.view.ConsoleUI;

import java.util.List;

/**
 * Handles the replay or validation of a single PGN game.
 */
public class GameController {
    private final Board board;
    private final PGNValidator validator;
    private final ConsoleUI ui;
    private boolean whiteTurn;

    public GameController() {
        this.board = new Board();
        this.validator = new PGNValidator();
        this.ui = new ConsoleUI();
        this.whiteTurn = true;
    }

    /**
     * Loads a PGN game, validates it, and optionally replays it.
     */
    public void runGame(PGNGame game, boolean replay) {
        ui.printGameHeader(game.getTags());

        boolean isValid = validator.validate(game);
        if (!isValid) {
            List<String> errors = validator.getErrors();
            ui.printErrors(errors);
            return;
        }

        if (replay) replayGame(game.getMoves());
    }

    /**
     * Replays the moves of a PGN game on the board, printing the board after each move.
     */
    private void replayGame(List<Move> moves) {
        for (Move move : moves) {
            String notation = move.getNotation();
            boolean success = applyMove(notation);
            ui.printBoard(board);
            if (!success) {
                ui.printMessage("Error: Failed to apply move " + notation);
                break;
            }
            whiteTurn = !whiteTurn;
        }
    }

    /**
     * Tries to apply a move in algebraic notation to the current board.
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
}
