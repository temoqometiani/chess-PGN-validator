# ♟️ Chess PGN Validator

This is a Java-based PGN (Portable Game Notation) parser and validator for chess games.
The application reads `.pgn` files and processes their contents into structured game objects,
then verifies the validity of each game by checking both syntactic correctness and move legality.
It is ideal for developers or hobbyists working with chess data who need to verify PGN content before using 
it in analysis tools or game replays.

## 📁 Project Structure

The project is organized into clearly defined components. 
The `PGNParser` class handles reading and parsing PGN files into structured `PGNGame` objects. 
The `PGNValidator` class checks each game for format issues and illegal moves. 
Supporting classes like `PGNGame` and `Move` store game metadata and individual chess moves respectively. 
Test coverage is provided through `PGNparserTest` and `main`, 
while sample PGN files for testing are stored in the `src/resources/` directory.

## ✅ Features

This project supports parsing standard PGN files that may contain one or more chess games,
and it validates each game by ensuring proper tag formatting,
correct SAN (Standard Algebraic Notation) move parsing, and legality of each move according to chess rules.
It identifies syntax issues, ambiguous or malformed notation, 
and illegal actions like invalid piece movement or skipped turns. 
Clear error messages are generated when validation fails, 
and a suite of unit tests ensures that core functionalities work as expected.

## 🚀 How to Run

To use the parser and validator, 
open the project in your preferred Java IDE such as IntelliJ IDEA or VS Code. 
Place the `.pgn` files you want to analyze in the `src/resources/` directory. 
You can run the validation logic through the provided `main.java` or write your own main class to call the
`PGNParser` and `PGNValidator`. Once run, 
the application will output whether each game is valid and display helpful feedback if issues are found.

## 🧪 Running Unit Tests

The project uses JUnit 5 for testing and includes comprehensive test cases for PGN parsing and validation. 
The `PGNparserTest` class focuses on verifying correct file reading, move extraction, and tag interpretation, 
while `PGNValidatortest` checks for problems like illegal moves, malformed syntax, and ambiguous notations. 
For these tests to function correctly, make sure all relevant `.pgn` 
test files are placed in `src/resources/`.

## 📂 Sample Test Files

Included test files demonstrate various scenarios.
The `good.pgn` file contains a clean, legal chess game. 
The `syntax.pgn` file has improperly formatted PGN headers to test tag validation. 
The `illegalmove.pgn` file includes games with illegal moves like invalid rook or pawn movement. 
The `multiple_games.pgn` file contains two valid games in one file, 
and `empty.pgn` is used to ensure the parser handles empty input gracefully.

## 🧠 Future Ideas

Potential improvements for the future include adding support for PGN comments and alternate move variations,
developing a visual board interface to render parsed games and highlight problems, 
and enabling export of cleaned or corrected PGN files for further use or sharing.
