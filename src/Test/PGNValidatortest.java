package src.Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.model.*;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PGNValidatortest {

    private PGNParser parser;
    private PGNValidator validator;

    @BeforeEach
    public void setup() {
        parser = new PGNParser();
        validator = new PGNValidator();
    }

    @Test
    public void testValidGameValidation() {
        File file = new File("src/test/resources/valid_game.pgn");
        PGNGame game = parser.parse(String.valueOf(file));
        boolean result = validator.validate(game);
        assertTrue(result, "Valid PGN game should be validated successfully");
    }

    @Test
    public void testInvalidSyntaxDetection() {
        File file = new File("src/test/resources/syntax_error.pgn");
        PGNGame game = parser.parse(String.valueOf(file));
        boolean result = validator.validate(game);
        assertFalse(result, "Syntax errors should cause validation to fail");
        assertFalse(validator.getErrors().isEmpty(), "Errors should be reported");
    }

    @Test
    public void testIllegalMoveDetection() {
        File file = new File("src/test/resources/illegal_move.pgn");
        PGNGame game = parser.parse(String.valueOf(file));
        boolean result = validator.validate(game);
        assertFalse(result, "Illegal moves should cause validation to fail");
        assertTrue(validator.getErrors().stream().anyMatch(msg -> msg.contains("Illegal move")));
    }


}
