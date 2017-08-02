package com.matthewglover.tictactoe.consoleui;

import com.matthewglover.tictactoe.core.GameType;
import com.matthewglover.tictactoe.core.TicTacToeModel;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameTypeUITest {
    private TicTacToeModel ticTacToeModel = new TicTacToeModel();

    @Test
    public void printsAllGameTypeOptions() {
        IOTestHelper ioTestHelper = new IOTestHelper();
        ioTestHelper.setInputStream("2\n");
        GameTypeUI gameTypeUI = new GameTypeUI(ioTestHelper.getInputStream(), ioTestHelper.getOutputStream(), ticTacToeModel);
        gameTypeUI.run();
        assertEquals(GameOptionsMessages.REQUEST_GAME_TYPE_INTRO + "\n" +
                "(1) " + GameType.HUMAN_HUMAN.getDescription() + "\n" +
                "(2) " + GameType.HUMAN_COMPUTER.getDescription() + "\n" +
                "(3) " + GameType.COMPUTER_HUMAN.getDescription() + "\n" +
                "(4) " + GameType.COMPUTER_COMPUTER.getDescription() + "\n", ioTestHelper.getOutContentString());
    }

    @Test
    public void setGameTypeForCorrespondingValue() {
        IOTestHelper ioTestHelper = new IOTestHelper();
        ioTestHelper.setInputStream("2\n");
        GameTypeUI gameTypeUI = new GameTypeUI(ioTestHelper.getInputStream(), ioTestHelper.getOutputStream(), ticTacToeModel);
        gameTypeUI.run();
        assertEquals(GameType.values()[1], ticTacToeModel.getCurrentGameTypeModel().getGameType());
    }

    @Test
    public void promptForGameTypeReportsErrorAndPromptsForValidInputForOutOfRangeInput() {
        IOTestHelper ioTestHelper = new IOTestHelper();
        ioTestHelper.setInputStream("5\n2\n");
        GameTypeUI gameTypeUI = new GameTypeUI(ioTestHelper.getInputStream(), ioTestHelper.getOutputStream(), ticTacToeModel);
        gameTypeUI.run();
        assertEquals(GameOptionsMessages.INVALID_INPUT, ioTestHelper.getLastLineOfOutput());
        assertEquals(GameType.values()[1], ticTacToeModel.getCurrentGameTypeModel().getGameType());
    }

    @Test
    public void promptForGameTypeReportsErrorAndPromptsForValidInputForNonIntegerInput() {
        IOTestHelper ioTestHelper = new IOTestHelper();
        ioTestHelper.setInputStream("non integer\n3\n");
        GameTypeUI gameTypeUI = new GameTypeUI(ioTestHelper.getInputStream(), ioTestHelper.getOutputStream(), ticTacToeModel);
        gameTypeUI.run();
        assertEquals(GameOptionsMessages.INVALID_INPUT, ioTestHelper.getLastLineOfOutput());
        assertEquals(GameType.values()[2], ticTacToeModel.getCurrentGameTypeModel().getGameType());
    }
}