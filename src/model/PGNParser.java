package src.model;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * PGNParser reads .pgn files and splits them into PGNGame objects.
 */
public class PGNParser {

    private static final Pattern TAG_PATTERN = Pattern.compile("\\[(\\w+)\\s+\\\"([^\\\"]*)\\\"]");
    private static final Pattern MOVE_TOKEN_PATTERN = Pattern.compile("[^\\s]+\\s*");

    public List<PGNGame> parse(File file) {
        List<PGNGame> games = new ArrayList<>();
        List<String> currentTags = new ArrayList<>();
        StringBuilder moveText = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = reader.readLine()) != null) {
                line = line.trim();

                if (line.startsWith("[")) {
                    currentTags.add(line);
                } else if (line.isEmpty()) {
                    if (!currentTags.isEmpty() || moveText.length() > 0) {
                        PGNGame game = parseGame(currentTags, moveText.toString());
                        games.add(game);
                        currentTags.clear();
                        moveText.setLength(0);
                    }
                } else {
                    moveText.append(line).append(" ");
                }
            }

            // Add last game if file doesn't end with a blank line
            if (!currentTags.isEmpty() || moveText.length() > 0) {
                PGNGame game = parseGame(currentTags, moveText.toString());
                games.add(game);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return games;
    }

    private PGNGame parseGame(List<String> tagLines, String moveSection) {
        Map<String, String> tags = new HashMap<>();

        for (String tagLine : tagLines) {
            Matcher matcher = TAG_PATTERN.matcher(tagLine);
            if (matcher.matches()) {
                tags.put(matcher.group(1), matcher.group(2));
            }
        }

        List<Move> moves = new ArrayList<>();
        Matcher tokenMatcher = MOVE_TOKEN_PATTERN.matcher(moveSection);
        int moveNumber = 1;
        boolean whiteTurn = true;

        while (tokenMatcher.find()) {
            String token = tokenMatcher.group().trim();

            if (token.matches("\\d+\\.")) {
                moveNumber = Integer.parseInt(token.replace(".", ""));
                whiteTurn = true;
            } else if (token.matches("1-0|0-1|1/2-1/2|\\*") || token.isEmpty()) {
                continue;
            } else {
                moves.add(new Move(token, moveNumber, whiteTurn, moveSection));
                whiteTurn = !whiteTurn;
            }
        }

        return new PGNGame(tags, moves, moveSection);
    }
}
