package src.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a parsed PGN game, including metadata tags and SAN moves.
 */
public class PGNGame {
    private final Map<String, String> tags;   // Metadata (e.g., Event, Date, Result)
    private final List<Move> moves;           // Parsed list of moves
    private final String result;              // Game result (e.g., "1-0", "0-1", "1/2-1/2", "*")

    public PGNGame(Map<String, String> tags, List<Move> moves, String result) {
        this.tags = new HashMap<>(tags);
        this.moves = new ArrayList<>(moves);
        this.result = result;
    }

    public Map<String, String> getTags() {
        return tags;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public String getResult() {
        return result;
    }

    public String getTag(String key) {
        return tags.getOrDefault(key, "");
    }

    @Override
    public String toString() {
        return "PGNGame{" +
                "Event=" + getTag("Event") +
                ", Date=" + getTag("Date") +
                ", Result=" + result +
                ", Moves=" + moves.size() +
                '}';
    }
}
