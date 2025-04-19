package src.Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.model.*;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PGNparserTest {

    private PGNParser parser;

    @BeforeEach
    public void setup() {
        parser = new PGNParser();
    }

    @Test
    public void testParseValidPGNFile() {
        File file = new File("src/test/resources/good.pgn");
        List<PGNGame> games = parser.parse(file);

        assertFalse(games.isEmpty(), "Should parse at least one game");
        PGNGame game = games.get(0);
        assertNotNull(game.getTags().get("Event"));
        assertFalse(game.getMoves().isEmpty(), "Game should contain moves");
    }

    @Test
    public void testParseEmptyFile() {
        File file = new File("src/test/resources/empty.pgn");
        List<PGNGame> games = parser.parse(file);
        assertTrue(games.isEmpty(), "Should return no games for empty file");
    }

    @Test
    public void testParseMalformedTags() {
        File file = new File("src/resources/syntax.pgn");
        List<PGNGame> games = parser.parse(file);
        assertEquals(1, games.size(), "Parser should still return a game despite malformed tags");
        assertFalse(games.get(0).getTags().isEmpty(), "Tags should be present, even if partially parsed");
    }

    @Test
    public void testParseMultipleGames() {
        File file = new File("src/resources/multiplegames.pgn");
        List<PGNGame> games = parser.parse(file);
        assertEquals(2, games.size(), "Should parse multiple games correctly");
    }

    @Test
    public void testMoveParsingAccuracy() {
        File file = new File("src/resources/good.pgn");
        System.out.println("File exists? " + file.exists());

        List<PGNGame> games = parser.parse(file);
        System.out.println("Parsed games: " + games.size());

        assertFalse(games.isEmpty(), "Parser returned no games — likely file not found or empty.");

        List<Move> moves = games.get(0).getMoves();
        assertEquals("e4", moves.get(0).getSan());
        assertEquals("e5", moves.get(1).getSan());
        assertEquals("Nf3", moves.get(2).getSan());
    }
}
