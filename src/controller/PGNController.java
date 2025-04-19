package src.controller;

import src.model.PGNGame;
import src.model.PGNParser;
import src.model.PGNValidator;
import src.view.ConsoleUI;

import java.io.File;
import java.util.Collections;
import java.util.List;

/**
 * Handles loading and validating multiple PGN files.
 */
public class PGNController {
    private final PGNParser parser;
    private final PGNValidator validator;
    private final ConsoleUI ui;

    public PGNController() {
        this.parser = new PGNParser();
        this.validator = new PGNValidator();
        this.ui = new ConsoleUI();
    }

    /**
     * Loads and validates all PGN games from a file.
     * @param file The PGN file to process
     */
    public void run(File file) {
        List<PGNGame> games = Collections.singletonList((PGNGame) parser.parse(new File(String.valueOf(file))));
        int gameNumber = 1;

        for (PGNGame game : games) {
            ui.displayMessage("\nGame " + gameNumber + ":");
            boolean isValid = validator.validate(game);
            if (isValid) {
                ui.displayMessage("Game is valid.\n");
            } else {
                List<String> errors = validator.getErrors();
                ui.displayErrors(errors);
            }
            gameNumber++;
        }
    }
}
