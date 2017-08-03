package com.matthewglover.tictactoe.consoleui;

import com.matthewglover.tictactoe.core.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameStatusUITest {
    private final TicTacToeModel ticTacToeModel = new TicTacToeModel();
    private final IOTestHelper ioTestHelper = new IOTestHelper();

    @Test
    public void reportsWinnerOnGameOverAndPromptsForPlayAgain() {
        setupTest("n\n");

        // x x x
        // o o 6
        // 7 8 9
        runGame(new int[]{1, 4, 2, 5, 3});

        String winningMessage = String.format(GameStatusMessages.REPORT_WINNER, PlayerSymbol.X);
        String[] lines = ioTestHelper.getOutContentAsLines();

        assertEquals(winningMessage, lines[0]);
        assertEquals(GameStatusMessages.REQUEST_PLAY_AGAIN, lines[1]);
    }

    @Test
    public void reportsDrawOnGameOverAndPromptsForPlayAgain() {
        setupTest("n\n");

        // x o x
        // o o x
        // x x o
        runGame(new int[]{1, 4, 6, 5, 3, 2, 8, 9, 7});

        String[] lines = ioTestHelper.getOutContentAsLines();

        assertEquals(GameStatusMessages.REPORT_DRAW, lines[0]);
        assertEquals(GameStatusMessages.REQUEST_PLAY_AGAIN, lines[1]);
    }

    @Test
    public void requestsNewGameOnUserInputOfy() {
        setupTest("y\n");

        // NOTE: TestObserver needs to be set after GameStatusUI
        // Otherwise event dispatch order is lost and GAME_OVER event
        // is received after START_NEW_GAME
        TicTacToeModelTestObserver testObserver = new TicTacToeModelTestObserver(ticTacToeModel);

        // x x x
        // o o 6
        // 7 8 9
        runGame(new int[]{1, 4, 2, 5, 3});

        assertEquals(ModelUpdate.START_NEW_GAME, testObserver.getLastUpdate());
    }

    public void setupTest(String inputStream) {
        ioTestHelper.setInputStream(inputStream);
        setupGameStatusUI();
        ticTacToeModel.setCurrentGameType(GameType.HUMAN_HUMAN);
        ticTacToeModel.setCurrentBoard(3);
    }

    private void setupGameStatusUI() {
        new GameStatusUI(ioTestHelper.getInputStream(), ioTestHelper.getOutputStream(), ticTacToeModel);
    }

    private void runGame(int[] moves) {
        for (int move : moves) {
            ticTacToeModel.gameMove(move);
        }
    }
}