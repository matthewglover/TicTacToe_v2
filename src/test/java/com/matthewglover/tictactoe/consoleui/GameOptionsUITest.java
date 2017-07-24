package com.matthewglover.tictactoe.consoleui;

import com.matthewglover.tictactoe.core.GameType;
import org.junit.Test;


import static org.junit.Assert.*;

public class GameOptionsUITest {
    @Test
    public void printRequestBoardSizeOutputsMessage() {
        GameOptionsUIBuilder builder = new GameOptionsUIBuilder("");
        builder.getGameOptionsUI().printRequestBoardSize();
        assertEquals(GameOptionsMessages.REQUEST_BOARD_SIZE + "\n", builder.getIoTestHelper().getOutContentString());
    }

    @Test
    public void printRequestGameTypeOutputsMessage() {
        GameOptionsUIBuilder builder = new GameOptionsUIBuilder("");
        builder.getGameOptionsUI().printRequestGameType();
        assertEquals(GameOptionsMessages.REQUEST_GAME_TYPE_INTRO + "\n" +
                "(1) " + GameType.HUMAN_HUMAN.getDescription() + "\n" +
                "(2) " + GameType.HUMAN_COMPUTER.getDescription() + "\n" +
                "(3) " + GameType.COMPUTER_COMPUTER.getDescription() + "\n", builder.getIoTestHelper().getOutContentString());
    }

    @Test
    public void promptForBoardSizeReturnsSizeForValidInteger() {
        GameOptionsUIBuilder builder = new GameOptionsUIBuilder("3\n");
        int boardSize = builder.getGameOptionsUI().promptForBoardSize();
        assertEquals(3, boardSize);
    }

    @Test
    public void promptForBoardSizeReportsErrorAndPromptsForValidInput() {
        GameOptionsUIBuilder builder = new GameOptionsUIBuilder("invalid\n3\n");
        int boardSize = builder.getGameOptionsUI().promptForBoardSize();
        assertEquals(GameOptionsMessages.INVALID_INPUT + "\n", builder.getIoTestHelper().getOutContentString());
        assertEquals(3, boardSize);
    }

    @Test
    public void promptForGameTypeReturnsGameTypeForCorrespondingValue() {
        GameOptionsUIBuilder builder = new GameOptionsUIBuilder("1\n");
        GameType gameType = builder.getGameOptionsUI().promptForGameType();
        assertEquals(GameType.values()[0], gameType);
    }

    @Test
    public void promptForGameTypeReportsErrorAndPromptsForValidInputForOutOfRangeInput() {
        GameOptionsUIBuilder builder = new GameOptionsUIBuilder("4\n2\n");
        GameType gameType = builder.getGameOptionsUI().promptForGameType();
        assertEquals(GameOptionsMessages.INVALID_INPUT + "\n", builder.getIoTestHelper().getOutContentString());
        assertEquals(GameType.values()[1], gameType);
    }

    @Test
    public void promptForGameTypeReportsErrorAndPromptsForValidInputForNonIntegerInput() {
        GameOptionsUIBuilder builder = new GameOptionsUIBuilder("non integer\n3\n");
        GameType gameType = builder.getGameOptionsUI().promptForGameType();
        assertEquals(GameOptionsMessages.INVALID_INPUT + "\n", builder.getIoTestHelper().getOutContentString());
        assertEquals(GameType.values()[2], gameType);
    }
}