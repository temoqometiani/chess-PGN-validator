package src.view;

import src.model.*;

import java.util.List;
import java.util.Map;

/**
 * Console-based UI for printing PGN game results and errors.
 */
public class ConsoleUI {

    public void printWelcome() {
        System.out.println("\u2654 Welcome to PGN Chess Validator \u265A\n");
    }

    public void printGameSummary(PGNGame game, boolean isValid) {
        String result = game.getResult();
        String event = game.getTag("Event");
        String white = game.getTag("White");
        String black = game.getTag("Black");

        System.out.printf("[Event] %s\n", event);
        System.out.printf("[White] %s\n", white);
        System.out.printf("[Black] %s\n", black);
        System.out.printf("[Result] %s\n", result);
        System.out.println("Validation: " + (isValid ? "PASS" : "FAIL"));
        System.out.println("----------------------------\n");
    }

    public void printBoard(Board board) {
        Square[][] squares = board.getSquareArray();

        System.out.println("   a b c d e f g h");
        System.out.println("  -----------------");

        for (int y = 7; y >= 0; y--) {
            System.out.print((y + 1) + " |");
            for (int x = 0; x < 8; x++) {
                Piece p = squares[x][y].getOccupyingPiece();
                if (p == null) {
                    System.out.print(" .");
                } else {
                    char symbol = getSymbol(p);
                    System.out.print(" " + symbol);
                }
            }
            System.out.println(" | " + (y + 1));
        }

        System.out.println("  -----------------");
        System.out.println("   a b c d e f g h");
    }

    private char getSymbol(Piece piece) {
        String type = piece.getClass().getSimpleName().toLowerCase();

        char symbol = switch (type) {
            case "pawn"   -> 'P';
            case "rook"   -> 'R';
            case "knight" -> 'N';
            case "bishop" -> 'B';
            case "queen"  -> 'Q';
            case "king"   -> 'K';
            default       -> '?';
        };

        return piece.getColor() == 0 ? Character.toLowerCase(symbol) : symbol; // black = lowercase, white = uppercase
    }

    public void printMoves(List<Move> moves) {
        System.out.println("Moves:");
        for (int i = 0; i < moves.size(); i++) {
            Move move = moves.get(i);
            if (move.isWhiteMove()) {
                System.out.printf("%d. %s ", move.getMoveNumber(), move.getSan());
            } else {
                System.out.printf("%s\n", move.getSan());
            }
        }
        System.out.println();
    }

    public void printError(String message) {
        System.out.println("Error: " + message);
    }

    public void printErrors(List<String> errors) {
        for (String error : errors) {
            printError(error);
        }
    }

    public void printFinalSummary(int total, int passed) {
        System.out.println("\n=== Final Report ===");
        System.out.printf("Validated %d games. %d passed, %d failed.\n",
                total, passed, total - passed);
    }
    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void displayErrors(java.util.List<String> errors) {
        System.out.println("Errors found:");
        for (String error : errors) {
            System.out.println("- " + error);
        }
    }
    public void printGameHeader(Map<String, String> tags) {
        System.out.println("📋 PGN Game Header:");
        for (Map.Entry<String, String> entry : tags.entrySet()) {
            System.out.printf("[%s \"%s\"]\n", entry.getKey(), entry.getValue());
        }
        System.out.println();
    }
}
