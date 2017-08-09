package com.matthewglover.tictactoe.consoleui;

import com.matthewglover.tictactoe.core.GameType;
import com.matthewglover.tictactoe.core.TicTacToeModel;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameTypeUITest {
    private TicTacToeModel ticTacToeModel = new TicTacToeModel();
    private final IOTestHelper ioTestHelper = new IOTestHelper();

    @Test
    public void printsAllGameTypeOptions() {
        setupTest("2\n");
        assertEquals(IOTestHelper.CLEAR_LINE +
                GameOptionsMessages.REQUEST_GAME_TYPE_INTRO + "\n" +
                "(1) " + GameType.HUMAN_HUMAN.getDescription() + "\n" +
                "(2) " + GameType.HUMAN_COMPUTER.getDescription() + "\n" +
                "(3) " + GameType.COMPUTER_HUMAN.getDescription() + "\n" +
                "(4) " + GameType.COMPUTER_COMPUTER.getDescription() + "\n", ioTestHelper.getOutContentString());
    }

    @Test
    public void setGameTypeForCorrespondingValue() {
        setupTest("2\n");
        assertEquals(GameType.values()[1], ticTacToeModel.getCurrentGameTypeModel().getGameType());
    }

    @Test
    public void promptForGameTypeReportsErrorAndPromptsForValidInputForOutOfRangeInput() {
        setupTest("5\n2\n");
        assertEquals(GameOptionsMessages.INVALID_INPUT, ioTestHelper.getLastLineOfOutput());
        assertEquals(GameType.values()[1], ticTacToeModel.getCurrentGameTypeModel().getGameType());
    }

    @Test
    public void promptForGameTypeReportsErrorAndPromptsForValidInputForNonIntegerInput() {
        setupTest("non integer\n3\n");
        assertEquals(GameOptionsMessages.INVALID_INPUT, ioTestHelper.getLastLineOfOutput());
        assertEquals(GameType.values()[2], ticTacToeModel.getCurrentGameTypeModel().getGameType());
    }

    private IOTestHelper setupTest(String input) {
        ioTestHelper.setInputStream(input);
        new GameTypeUI(ioTestHelper.getInputStream(), ioTestHelper.getOutputStream(), ticTacToeModel);
        ticTacToeModel.setupNewGame();
        return ioTestHelper;
    }
}