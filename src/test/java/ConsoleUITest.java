import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

public class ConsoleUITest {

    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private PrintStream outputStream = new PrintStream(outContent);
    private Board board = new Board(3);

    @Test
    public void promptForBoardSizePrints() {
        ConsoleUI UI = buildUIWithInput("3");
        UI.promptForBoardSize(Board.MIN_SIZE, Board.MAX_SIZE);
        assertEquals(UIMessages.SELECT_BOARD_SIZE_PROMPT, outContent.toString());
    }

    @Test
    public void promptForBoardSizeReturnsSize() {
        ConsoleUI UI = buildUIWithInput("3");
        int boardSize = UI.promptForBoardSize(Board.MIN_SIZE, Board.MAX_SIZE);
        assertEquals(3, boardSize);
    }

    @Test
    public void promptForBoardSizeReportsInputErrorWhenOutOfBoundsBoardSize() {
        ConsoleUI UI = buildUIWithInput("5\n4\n");
        int boardSize = UI.promptForBoardSize(Board.MIN_SIZE, Board.MAX_SIZE);
        assertEquals(UIMessages.INVALID_INPUT_PROMPT, getLastLineOfOutput());
        assertEquals(4, boardSize);
    }

    @Test
    public void promptForBoardSizeReportsInputErrorWhenNonIntegerInput() {
        ConsoleUI UI = buildUIWithInput("invalid input\n4\n");
        int boardSize = UI.promptForBoardSize(Board.MIN_SIZE, Board.MAX_SIZE);
        assertEquals(UIMessages.INVALID_INPUT_PROMPT, getLastLineOfOutput());
        assertEquals(4, boardSize);
    }

    @Test
    public void promptForMovePrintsCurrentBoardState() {
        board.setSquare(1, PlayerSymbol.X);
        board.setSquare(2, PlayerSymbol.O);
        board.setSquare(3, PlayerSymbol.O);
        board.setSquare(9, PlayerSymbol.X);
        ConsoleUI UI = buildUIWithInput("4");
        UI.promptForMove(PlayerSymbol.X, board);
        assertLinesEqual(new String[]{
                "",
                " X | O | O ",
                "--- --- ---",
                " 4 | 5 | 6 ",
                "--- --- ---",
                " 7 | 8 | X ",
                "PlayerSymbol X: "
        });
    }

    @Test
    public void leftPadDoubleDigitNumbers() {
        board = new Board(4);
        board.setSquare(1, PlayerSymbol.X);
        board.setSquare(2, PlayerSymbol.O);
        ConsoleUI UI = buildUIWithInput("4");
        UI.promptForMove(PlayerSymbol.X, board);
        assertLinesEqual(new String[]{
                "",
                "  X |  O |  3 |  4 ",
                "---- ---- ---- ----",
                "  5 |  6 |  7 |  8 ",
                "---- ---- ---- ----",
                "  9 | 10 | 11 | 12 ",
                "---- ---- ---- ----",
                " 13 | 14 | 15 | 16 ",
                "PlayerSymbol X: "
        });
    }

    @Test
    public void promptForMoveReturnsUsersValidInput() {
        ConsoleUI UI = buildUIWithInput("1");
        int userMove = UI.promptForMove(PlayerSymbol.X, board);
        assertEquals(1, userMove);
    }

    @Test
    public void nonIntegerReportsInputError() {
        ConsoleUI UI = buildUIWithInput("INVALID_INPUT\n1");
        UI.promptForMove(PlayerSymbol.X, board);
        assertEquals(UIMessages.INVALID_INPUT_PROMPT, getLastLineOfOutput());
    }

    @Test
    public void outOfBoundsMoveReportsInputError() {
        ConsoleUI UI = buildUIWithInput("10\n1");
        UI.promptForMove(PlayerSymbol.X, board);
        assertEquals(UIMessages.INVALID_INPUT_PROMPT, getLastLineOfOutput());
    }

    @Test
    public void moveForTakenSquareReportsInputError() {
        board.setSquare(1, PlayerSymbol.O);
        ConsoleUI UI = buildUIWithInput("1\n2");
        UI.promptForMove(PlayerSymbol.X, board);
        assertEquals(UIMessages.INVALID_INPUT_PROMPT, getLastLineOfOutput());
    }

    @Test
    public void reportsDraw() {
        ConsoleUI UI = buildUIWithInput("");
        UI.reportDraw();
        assertEquals(UIMessages.GAME_DRAWN, outContent.toString());
    }

    @Test
    public void reportsWinner() {
        ConsoleUI UI = buildUIWithInput("");
        UI.reportWinner(PlayerSymbol.X);
        assertEquals("X Wins!!", outContent.toString());
    }

    @Test
    public void promptUserToPlayAgainPrintsMessage() {
        ConsoleUI UI = buildUIWithInput(UIMessages.DONT_PLAY_AGAIN_INPUT + "\n");
        UI.promptPlayAgain();
        assertEquals(UIMessages.NEW_GAME_PROMPT, outContent.toString());
    }

    @Test
    public void playAgainWhenUserInputIsN() {
        ConsoleUI UI = buildUIWithInput(UIMessages.DONT_PLAY_AGAIN_INPUT + "\n");
        assertTrue(UI.promptPlayAgain());
    }

    @Test
    public void dontPlayAgainWhenUserInputIsNotN() {
        ConsoleUI UI = buildUIWithInput("Other\n");
        assertFalse(UI.promptPlayAgain());
    }

    private ConsoleUI buildUIWithInput(String testInput) {
        InputStream inputStream = new ByteArrayInputStream(testInput.getBytes());
        return new ConsoleUI(inputStream, outputStream);
    }

    private void assertLinesEqual(String[] expected) {
        assertEquals(String.join("\n", expected), outContent.toString());
    }

    private String getLastLineOfOutput() {
        String[] lines = outContent.toString().split("\\n");
        return lines[lines.length - 1];
    }
}