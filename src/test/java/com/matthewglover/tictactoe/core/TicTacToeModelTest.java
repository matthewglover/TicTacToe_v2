package com.matthewglover.tictactoe.core;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TicTacToeModelTest {
    private final TicTacToeModel ticTacToeModel = new TicTacToeModel();
    private final TicTacToeModelTestObserver testObserver = new TicTacToeModelTestObserver(ticTacToeModel);

    @Test
    public void setGameTypeNotifiesObservers() {
        ticTacToeModel.setupNewGame();
        ticTacToeModel.setGameType(GameType.COMPUTER_HUMAN);
        assertEquals(ModelUpdate.SET_GAME_TYPE, testObserver.getLastUpdate());
    }

    @Test
    public void getPlayerTypeBasedOnGameType() {
        ticTacToeModel.setupNewGame();
        ticTacToeModel.setGameType(GameType.COMPUTER_HUMAN);
        assertEquals(PlayerType.COMPUTER, ticTacToeModel.getCurrentGameTypeModel().getPlayerType(PlayerSymbol.X));
        assertEquals(PlayerType.HUMAN, ticTacToeModel.getCurrentGameTypeModel().getPlayerType(PlayerSymbol.O));
    }

    @Test
    public void tracksGameAndCurrentPlayer() {
        ticTacToeModel.setupNewGame();
        ticTacToeModel.setGameType(GameType.HUMAN_HUMAN);
        ticTacToeModel.createGame(3);
        assertEquals(PlayerSymbol.X, ticTacToeModel.getCurrentGame().getNextPlayerSymbol());
        ticTacToeModel.gameMove(1);
        assertEquals(PlayerSymbol.O, ticTacToeModel.getCurrentGame().getNextPlayerSymbol());
    }

    @Test
    public void delegatesMoveToComputer() {
        ticTacToeModel.setupNewGame();
        ticTacToeModel.setGameType(GameType.HUMAN_COMPUTER);
        ticTacToeModel.createGame(3);
        ticTacToeModel.gameMove(1);
        assertEquals(PlayerType.COMPUTER, ticTacToeModel.getNextPlayerType());
    }

    @Test
    public void replaysCompletedGame() {
        ticTacToeModel.setupNewGame();
        ticTacToeModel.setGameType(GameType.HUMAN_HUMAN);
        ticTacToeModel.createGame(3);
        GameTestHelper.runGame(ticTacToeModel, new int[]{1, 4, 2, 5, 3});
        ticTacToeModel.replayGame();
        assertEquals(ModelUpdate.REPLAY_GAME, testObserver.getLastUpdate());
    }

    @Test
    public void doesntReplayInCompleteGame() {
        ticTacToeModel.setupNewGame();
        ticTacToeModel.setGameType(GameType.HUMAN_HUMAN);
        ticTacToeModel.createGame(3);
        GameTestHelper.runGame(ticTacToeModel, new int[]{1});
        ticTacToeModel.replayGame();
        assertNotEquals(ModelUpdate.REPLAY_GAME, testObserver.getLastUpdate());
    }
}
