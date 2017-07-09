import org.junit.Ignore;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

public class ConsoleUITest {

    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private PrintStream outputStream = new PrintStream(outContent);
    private Board board = new Board();
    private ConsoleUI UI;

    @Test
    public void promptForBoardSizePrints() {
        buildUIWithInput("3");
        UI.promptForBoardSize();
        assertEquals("Select board size: ", outContent.toString());
    }

    @Test
    public void promptForBoardSizeReturnsSize() {
        buildUIWithInput("3");
        int boardSize = UI.promptForBoardSize();
        assertEquals(3, boardSize);
    }

    @Test
    public void promptForBoardSizeReportsInputError() {
        buildUIWithInput("5\n4\n");
        int boardSize = UI.promptForBoardSize();
        assertEquals("Oops, invalid input. Try again: ", getLastLineOfOutput());
        assertEquals(4, boardSize);
    }

    @Test
    public void promptForMovePrintsCurrentBoardState() {
        board.setSquare(1, Player.X);
        board.setSquare(2, Player.O);
        board.setSquare(3, Player.O);
        board.setSquare(9, Player.X);
        buildUIWithInput("4");
        UI.promptForMove(Player.X, board);
        assertLinesEqual(new String[]{
            " X | O | O ",
            "--- --- ---",
            " 4 | 5 | 6 ",
            "--- --- ---",
            " 7 | 8 | X ",
            "Player X: "
        });
    }

    @Test
    public void leftPadDoubleDigitNumbers() {
        board = new Board(4);
        board.setSquare(1, Player.X);
        board.setSquare(2, Player.O);
        buildUIWithInput("4");
        UI.promptForMove(Player.X, board);
        assertLinesEqual(new String[]{
                "  X |  O |  3 |  4 ",
                "---- ---- ---- ----",
                "  5 |  6 |  7 |  8 ",
                "---- ---- ---- ----",
                "  9 | 10 | 11 | 12 ",
                "---- ---- ---- ----",
                " 13 | 14 | 15 | 16 ",
                "Player X: "
        });
    }

    @Test
    public void promptForMoveReturnsUsersValidInput() {
        buildUIWithInput("1");
        int userMove = UI.promptForMove(Player.X, board);
        assertEquals(1, userMove);
    }

    @Test
    public void nonIntegerReportsInputError() {
        buildUIWithInput("INVALID_INPUT\n1");
        UI.promptForMove(Player.X, board);
        assertEquals("Oops, invalid input. Try again: ", getLastLineOfOutput());
    }

    @Test
    public void outOfBoundsMoveReportsInputError() {
        buildUIWithInput("10\n1");
        UI.promptForMove(Player.X, board);
        assertEquals("Oops, invalid input. Try again: ", getLastLineOfOutput());
    }

    @Test
    public void moveForTakenSquareReportsInputError() {
        board.setSquare(1, Player.O);
        buildUIWithInput("1\n2");
        UI.promptForMove(Player.X, board);
        assertEquals("Oops, invalid input. Try again: ", getLastLineOfOutput());
    }

    @Test
    public void reportsDraw() {
        buildUIWithInput("");
        UI.reportDraw();
        assertEquals("It's a draw!", outContent.toString());
    }

    @Test
    public void reportsWinner() {
        buildUIWithInput("");
        UI.reportWinner(Player.X);
        assertEquals("X Wins!!", outContent.toString());
    }

    @Test
    public void promptUserToPlayAgainPrintsMessage() {
        buildUIWithInput("N");
        UI.promptPlayAgain();
        assertEquals("Type <N> for a new game or any other key to exit: ", outContent.toString());
    }

    @Test
    public void playAgainWhenUserInputIsN() {
        buildUIWithInput("N\n");
        assertTrue(UI.promptPlayAgain());
    }

    @Test
    public void dontPlayAgainWhenUserInputIsNotN() {
        buildUIWithInput("Other\n");
        assertFalse(UI.promptPlayAgain());
    }

    private void assertLinesEqual(String[] expected) {
        assertEquals(String.join("\n", expected), outContent.toString());
    }

    private void buildUIWithInput(String testInput) {
        InputStream inputStream = new ByteArrayInputStream(testInput.getBytes());
        UI = new ConsoleUI(inputStream, outputStream);
    }

    private String getLastLineOfOutput() {
        String[] lines = outContent.toString().split("\\n");
        return lines[lines.length - 1];
    }
}