package src.view;

/**
 * Utility class for formatting PGN syntax and logic error messages.
 */
public class PGNErrorFormatter {

    public static String formatSyntaxError(int lineNumber, String moveText, String message) {
        return String.format("[Syntax Error] Line %d: '%s' -> %s", lineNumber, moveText, message);
    }

    public static String formatLogicError(int moveNumber, String move, String reason) {
        return String.format("[Logic Error] Move %d (%s): %s", moveNumber, move, reason);
    }

    public static String formatGeneralError(String context, String message) {
        return String.format("[Error] %s: %s", context, message);
    }

    public static String formatGameHeader(String gameId, String white, String black) {
        return String.format("Game: %s | White: %s vs Black: %s", gameId, white, black);
    }

    public static String divider() {
        return "--------------------------------------------";
    }
}
