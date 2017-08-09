package com.matthewglover.tictactoe.consoleui;

import com.matthewglover.tictactoe.core.GameType;
import com.matthewglover.tictactoe.core.TicTacToeModel;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BoardSizeUITest {
    private TicTacToeModel ticTacToeModel = new TicTacToeModel();
    private final IOTestHelper ioTestHelper = new IOTestHelper();

    @Test
    public void printsRequestBoardSizeOutputsMessage() {
        setupTest("3\n");
        assertEquals(IOTestHelper.CLEAR_LINE +
                GameOptionsMessages.REQUEST_BOARD_SIZE + "\n", ioTestHelper.getOutContentString());
    }

    @Test
    public void setsBoardSizeToValidUserInput() {
        setupTest("3\n");
        assertEquals(3, ticTacToeModel.getBoard().getSize());
    }

    @Test
    public void validatesInputAndPromptsUntilValidInputReceived() {
        setupTest("invalid\n4\n");
        assertEquals(GameOptionsMessages.INVALID_INPUT, ioTestHelper.getLastLineOfOutput());
        assertEquals(4, ticTacToeModel.getBoard().getSize());
    }

    private void setupTest(String input) {
        ioTestHelper.setInputStream(input);
        new BoardSizeUI(ioTestHelper.getInputStream(), ioTestHelper.getOutputStream(), ticTacToeModel);
        ticTacToeModel.setupNewGame();
        ticTacToeModel.setGameType(GameType.HUMAN_HUMAN);

    }
}
