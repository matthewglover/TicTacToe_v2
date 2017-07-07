import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

public class ConsoleUITest {

    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private PrintStream outputStream = new PrintStream(outContent);
    private Board board = new Board();


    @Test
    public void promptForMovePrintsCurrentBoardState() {
        board.setSquare(1, Player.X);
        board.setSquare(2, Player.O);
        board.setSquare(3, Player.O);
        board.setSquare(9, Player.X);
        ConsoleUI UI = buildUIWithInput("4");
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
    public void promptForMoveReturnsUsersValidInput() {
        ConsoleUI UI = buildUIWithInput("1");
        int userMove = UI.promptForMove(Player.X, board);
        assertEquals(1, userMove);
    }

    @Test
    public void nonIntegerReportsInputError() {
        ConsoleUI UI = buildUIWithInput("INVALID_INPUT\n1");
        UI.promptForMove(Player.X, board);
        assertEquals("Oops, invalid input. Try again: ", getLastLineOfOutput());
    }

    @Test
    public void outOfBoundsMoveReportsInputError() {
        ConsoleUI UI = buildUIWithInput("10\n1");
        UI.promptForMove(Player.X, board);
        assertEquals("Oops, invalid input. Try again: ", getLastLineOfOutput());
    }

    @Test
    public void moveForTakenSquareReportsInputError() {
        board.setSquare(1, Player.O);
        ConsoleUI UI = buildUIWithInput("1\n2");
        UI.promptForMove(Player.X, board);
        assertEquals("Oops, invalid input. Try again: ", getLastLineOfOutput());
    }

    @Test
    public void reportsDraw() {
        ConsoleUI UI = buildUIWithInput("");
        UI.reportDraw();
        assertEquals("It's a draw!", outContent.toString());
    }

    @Test
    public void reportsWinner() {
        ConsoleUI UI = buildUIWithInput("");
        UI.reportWinner(Player.X);
        assertEquals("X Wins!!", outContent.toString());
    }

    @Test
    public void reportsInputError() {
        ConsoleUI UI = buildUIWithInput("");
        UI.reportMoveError();
        assertEquals("Oops, that square is already taken. Try again: ", outContent.toString());
    }

    private void assertLinesEqual(String[] expected) {
        assertEquals(String.join("\n", expected), outContent.toString());
    }

    private ConsoleUI buildUIWithInput(String testInput) {
        InputStream inputStream = new ByteArrayInputStream(testInput.getBytes());
        return new ConsoleUI(inputStream, outputStream);
    }

    private String getLastLineOfOutput() {
        String[] lines = outContent.toString().split("\\n");
        return lines[lines.length - 1];
    }
}