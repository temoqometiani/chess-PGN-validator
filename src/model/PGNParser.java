package src.model;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parses PGN text into PGNGame objects (tags + SAN moves).
 */
public class PGNParser {

    private static final Pattern TAG_PATTERN = Pattern.compile("\\[(\\w+)\\s+\"([^\"]+)\"\\]");
    private static final Pattern MOVE_TOKEN_PATTERN = Pattern.compile("([a-zA-Z0-9+=#]+|\\d+\\.|\\d+\\.\\.\\.|\\*)");

    /**
     * Parses a single PGN game string into a PGNGame object.
     *
     * @param pgnText The raw PGN game as a string
     * @return Parsed PGNGame
     * @throws IllegalArgumentException if required tags or format are invalid
     */
    public PGNGame parse(String pgnText) {
        Map<String, String> tags = new HashMap<>();
        List<Move> moves = new ArrayList<>();
        String result = "*";

        Scanner scanner = new Scanner(pgnText);
        StringBuilder moveSection = new StringBuilder();

        // Parse tags
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) break;

            Matcher tagMatcher = TAG_PATTERN.matcher(line);
            if (tagMatcher.matches()) {
                tags.put(tagMatcher.group(1), tagMatcher.group(2));
            }
        }

        // Parse move section
        while (scanner.hasNextLine()) {
            moveSection.append(scanner.nextLine()).append(" ");
        }
        scanner.close();

        Matcher tokenMatcher = MOVE_TOKEN_PATTERN.matcher(moveSection.toString());
        while (tokenMatcher.find()) {
            String token = tokenMatcher.group().trim();

            if (token.matches("\\d+\\.")) {
                continue; // Skip move numbers like "1."
            } else if (token.matches("1-0|0-1|1/2-1/2|\\*")) {
                result = token;
            } else if (!token.isEmpty()) {
                moves.add(new Move(token));
            }
        }

        return new PGNGame(tags, moves, result);
    }
}
