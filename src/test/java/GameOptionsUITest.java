import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class GameOptionsUITest {
    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private PrintStream outputStream = new PrintStream(outContent);

    @Test
    public void printRequestBoardSizeOutputsMessage() {
        InputStream inputStream = new ByteArrayInputStream("".getBytes());
        GameOptionsUI gameOptionsUI = new GameOptionsUI(inputStream, outputStream);
        gameOptionsUI.printRequestBoardSize();
        assertEquals(GameOptionsMessages.REQUEST_BOARD_SIZE + "\n", outContent.toString());
    }

    @Test
    public void printRequestGameTypeOutputsMessage() {
        InputStream inputStream = new ByteArrayInputStream("".getBytes());
        GameOptionsUI gameOptionsUI = new GameOptionsUI(inputStream, outputStream);
        gameOptionsUI.printRequestGameType();
        assertEquals(GameOptionsMessages.REQUEST_GAME_TYPE_INTRO + "\n" +
                "(1) " + GameType.HUMAN_HUMAN.getDescription() + "\n" +
                "(2) " + GameType.HUMAN_COMPUTER.getDescription() + "\n" +
                "(3) " + GameType.COMPUTER_COMPUTER.getDescription() + "\n", outContent.toString());
    }

    @Test
    public void promptForBoardSizeReturnsSizeForValidInteger() {
        InputStream inputStream = new ByteArrayInputStream("3\n".getBytes());
        GameOptionsUI gameOptionsUI = new GameOptionsUI(inputStream, outputStream);
        int boardSize = gameOptionsUI.promptForBoardSize();
        assertEquals(3, boardSize);
    }

    @Test
    public void promptForBoardSizeReportsErrorAndPromptsForValidInput() {
        InputStream inputStream = new ByteArrayInputStream("invalid input\n3\n".getBytes());
        GameOptionsUI gameOptionsUI = new GameOptionsUI(inputStream, outputStream);
        int boardSize = gameOptionsUI.promptForBoardSize();
        assertEquals(GameOptionsMessages.INVALID_INPUT + "\n", outContent.toString());
        assertEquals(3, boardSize);
    }

    @Test
    public void promptForGameTypeReturnsGameTypeForCorrespondingValue() {
        InputStream inputStream = new ByteArrayInputStream("1\n".getBytes());
        GameOptionsUI gameOptionsUI = new GameOptionsUI(inputStream, outputStream);
        GameType gameType = gameOptionsUI.promptForGameType();
        assertEquals(GameType.values()[0], gameType);
    }

    @Test
    public void promptForGameTypeReportsErrorAndPromptsForValidInput() {
        InputStream inputStream = new ByteArrayInputStream("4\n2".getBytes());
        GameOptionsUI gameOptionsUI = new GameOptionsUI(inputStream, outputStream);
        GameType gameType = gameOptionsUI.promptForGameType();
        assertEquals(GameOptionsMessages.INVALID_INPUT + "\n", outContent.toString());
        assertEquals(GameType.values()[1], gameType);
    }
}