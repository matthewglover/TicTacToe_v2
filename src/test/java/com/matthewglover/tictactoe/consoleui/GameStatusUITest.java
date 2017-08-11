package com.matthewglover.tictactoe.consoleui;

import com.matthewglover.tictactoe.core.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameStatusUITest {
    private final TicTacToeModel ticTacToeModel = new TicTacToeModel(new DelayedRunner());
    private final IOTestHelper ioTestHelper = new IOTestHelper();
    private final int BOARD_LINE_OFFSET = 5;

    @Test
    public void printsFinalBoardState() {
        setupTest("n\n");

        // x x x
        // o o 6
        // 7 8 9
        int[] moves = new int[]{1, 4, 2, 5, 3};
        GameTestHelper.runGame(ticTacToeModel, moves);

        String[] lines = ioTestHelper.getOutContentAsLines();

        assertEquals(
                IOTestHelper.CLEAR_LINE + new BoardFormatter(buildBoard(moves)).format(),
                lines[0] + "\n" + lines[1] + "\n" + lines[2] + "\n" + lines[3] + "\n" + lines[4]);
    }

    private Board buildBoard(int[] moves) {
        Game game = new Game(new Board(3));
        for (int move : moves) {
            game.move(move);
        }
        return game.getBoard();
    }

    @Test
    public void reportsWinnerOnGameOverAndPromptsForPlayAgain() {
        setupTest("n\n");

        // x x x
        // o o 6
        // 7 8 9
        GameTestHelper.runGame(ticTacToeModel, new int[]{1, 4, 2, 5, 3});

        String winningMessage = String.format(GameStatusMessages.REPORT_WINNER, PlayerSymbol.X);
        String[] lines = ioTestHelper.getOutContentAsLines();

        assertEquals(winningMessage, lines[0 + BOARD_LINE_OFFSET]);
        assertEquals(GameStatusMessages.REQUEST_PLAY_AGAIN, lines[1 + BOARD_LINE_OFFSET]);
    }

    @Test
    public void reportsDrawOnGameOverAndPromptsForPlayAgain() {
        setupTest("n\n");

        // x o x
        // o o x
        // x x o
        GameTestHelper.runGame(ticTacToeModel, new int[]{1, 4, 6, 5, 3, 2, 8, 9, 7});

        String[] lines = ioTestHelper.getOutContentAsLines();

        assertEquals(GameStatusMessages.REPORT_DRAW, lines[0 + BOARD_LINE_OFFSET]);
        assertEquals(GameStatusMessages.REQUEST_PLAY_AGAIN, lines[1 + BOARD_LINE_OFFSET]);
    }

    @Test
    public void requestsNewGameOnUserInputOfy() {
        TicTacToeModelTestObserver testObserver = runAndObserveGame("y\n", ticTacToeModel);
        assertEquals(ModelUpdate.SETUP_NEW_GAME, testObserver.getLastUpdate());
    }

    @Test
    public void requestsNewGameOnUserInputOfY() {
        TicTacToeModelTestObserver testObserver = runAndObserveGame("Y\n", ticTacToeModel);
        assertEquals(ModelUpdate.SETUP_NEW_GAME, testObserver.getLastUpdate());
    }

    @Test
    public void replaysGameOnUserInputOfr() {
        TicTacToeModelTestObserver testObserver = runAndObserveGame("r\n", ticTacToeModel);
        assertTrue(testObserver.getUpdates().contains(ModelUpdate.REPLAY_GAME));
    }

    @Test
    public void replaysGameOnUserInputOfR() {
        TicTacToeModelTestObserver testObserver = runAndObserveGame("R\n", ticTacToeModel);
        assertTrue(testObserver.getUpdates().contains(ModelUpdate.REPLAY_GAME));
    }

    private TicTacToeModelTestObserver runAndObserveGame(String inputStream, TicTacToeModel ticTacToeModel) {
        setupTest(inputStream);

        TicTacToeModelTestObserver testObserver = new TicTacToeModelTestObserver(ticTacToeModel);

        // x x x
        // o o 6
        // 7 8 9
        GameTestHelper.runGame(ticTacToeModel, new int[]{1, 4, 2, 5, 3});
        return testObserver;
    }

    private void setupTest(String inputStream) {
        ioTestHelper.setInputStream(inputStream);
        setupGameStatusUI();
        ticTacToeModel.setupNewGame();
        ticTacToeModel.setGameType(GameType.HUMAN_HUMAN);
        ticTacToeModel.createGame(3);
    }

    private void setupGameStatusUI() {
        new GameStatusUI(ioTestHelper.getInputStream(), ioTestHelper.getOutputStream(), ticTacToeModel);
    }
}