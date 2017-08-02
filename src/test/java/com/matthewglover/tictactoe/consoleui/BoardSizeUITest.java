package com.matthewglover.tictactoe.consoleui;

import com.matthewglover.tictactoe.core.GameType;
import com.matthewglover.tictactoe.core.TicTacToeModel;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BoardSizeUITest {
    private TicTacToeModel ticTacToeModel = new TicTacToeModel();

    @Test
    public void printsRequestBoardSizeOutputsMessage() {
        ticTacToeModel.setCurrentGameType(GameType.HUMAN_HUMAN);
        IOTestHelper ioTestHelper = new IOTestHelper();
        ioTestHelper.setInputStream("3\n");
        BoardSizeUI boardSizeUI = new BoardSizeUI(ioTestHelper.getInputStream(), ioTestHelper.getOutputStream(), ticTacToeModel);
        boardSizeUI.run();
        assertEquals(GameOptionsMessages.REQUEST_BOARD_SIZE + "\n", ioTestHelper.getOutContentString());
    }

    @Test
    public void promptForBoardSizeReturnsSizeForValidInteger() {
        ticTacToeModel.setCurrentGameType(GameType.HUMAN_HUMAN);
        IOTestHelper ioTestHelper = new IOTestHelper();
        ioTestHelper.setInputStream("3\n");
        BoardSizeUI boardSizeUI = new BoardSizeUI(ioTestHelper.getInputStream(), ioTestHelper.getOutputStream(), ticTacToeModel);
        boardSizeUI.run();
        assertEquals(3, ticTacToeModel.getCurrentBoard().getSize());
    }

    @Test
    public void promptForBoardSizeReportsErrorAndPromptsForValidInput() {
        ticTacToeModel.setCurrentGameType(GameType.HUMAN_HUMAN);
        IOTestHelper ioTestHelper = new IOTestHelper();
        ioTestHelper.setInputStream("invalid\n4\n");
        BoardSizeUI boardSizeUI = new BoardSizeUI(ioTestHelper.getInputStream(), ioTestHelper.getOutputStream(), ticTacToeModel);
        boardSizeUI.run();
        assertEquals(GameOptionsMessages.INVALID_INPUT, ioTestHelper.getLastLineOfOutput());
        assertEquals(4, ticTacToeModel.getCurrentBoard().getSize());
    }
}
