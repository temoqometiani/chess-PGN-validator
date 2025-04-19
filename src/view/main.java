package src.view;

import src.model.PGNGame;
import src.model.PGNParser;
import src.model.PGNValidator;
import java.io.File;
import java.util.List;

public class main {

    public static void main(String[] args) {
        File file = new File("src/resources/good.pgn");

        PGNParser parser = new PGNParser();
        PGNValidator validator = new PGNValidator();

        List<PGNGame> games = parser.parse(file);

        if (games.isEmpty()) {
            System.out.println("No games found in the PGN file.");
            return;
        }

        int gameNumber = 1;
        for (PGNGame game : games) {
            System.out.println("------ Game #" + gameNumber + " ------");
            boolean isValid = validator.validate(game);
            if (isValid) {
                System.out.println("Status:VALID game.");
            } else {
                System.out.println("Status: INVALID game.");
                validator.getErrors().forEach(error -> System.out.println(" - " + error));
            }
            gameNumber++;
        }
    }
}
