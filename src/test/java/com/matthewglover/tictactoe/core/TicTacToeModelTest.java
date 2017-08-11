package com.matthewglover.tictactoe.core;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class TicTacToeModelTest {
    private final TicTacToeModel ticTacToeModel = new TicTacToeModel(new ImmediateRunner());
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
        assertEquals(PlayerType.COMPUTER, ticTacToeModel.getGameTypeModel().getPlayerType(PlayerSymbol.X));
        assertEquals(PlayerType.HUMAN, ticTacToeModel.getGameTypeModel().getPlayerType(PlayerSymbol.O));
    }

    @Test
    public void tracksGameAndCurrentPlayer() {
        ticTacToeModel.setupNewGame();
        ticTacToeModel.setGameType(GameType.HUMAN_HUMAN);
        ticTacToeModel.createGame(3);
        assertEquals(PlayerSymbol.X, ticTacToeModel.getGame().getNextPlayerSymbol());
        ticTacToeModel.gameMove(1);
        assertEquals(PlayerSymbol.O, ticTacToeModel.getGame().getNextPlayerSymbol());
    }

    @Test
    public void delegatesMoveToComputer() {
        TicTacToeModel ticTacToeModel = new TicTacToeModel(new DelayedRunner());
        ticTacToeModel.setupNewGame();
        ticTacToeModel.setGameType(GameType.HUMAN_COMPUTER);
        ticTacToeModel.createGame(3);
        ticTacToeModel.gameMove(1);
        assertEquals(PlayerType.COMPUTER, ticTacToeModel.getNextPlayer().getType());
    }

    @Test
    public void replaysCompletedGame() {
        createAndRunGame(ticTacToeModel, new int[]{1, 4, 2, 5, 3});
        ticTacToeModel.replayGame();
        assertTrue(testObserver.getUpdates().contains(ModelUpdate.REPLAY_GAME));
    }

    @Test
    public void doesntReplayInCompleteGame() {
        createAndRunGame(ticTacToeModel, new int[]{1});
        ticTacToeModel.replayGame();
        assertNotEquals(ModelUpdate.REPLAY_GAME, testObserver.getLastUpdate());
    }

    @Test
    public void replayGameCreatesNewGameWithComputerPlayers() {
        TicTacToeModel ticTacToeModel = new TicTacToeModel(new DelayedRunner());
        createAndRunGame(ticTacToeModel, new int[]{1, 4, 2, 5, 3});
        ticTacToeModel.replayGame();
        assertTrue(ticTacToeModel.getGame().isNew());
        assertEquals(GameType.REPLAY, ticTacToeModel.getGameTypeModel().getGameType());
        assertTrue(ticTacToeModel.getGameTypeModel().getPlayer(PlayerSymbol.X) instanceof ReplayPlayer);
        assertTrue(ticTacToeModel.getGameTypeModel().getPlayer(PlayerSymbol.O) instanceof ReplayPlayer);
    }

    @Test
    public void replayGameRunsMovesOfLastGame() throws InterruptedException {
        createAndRunGame(ticTacToeModel, new int[]{1, 4, 2, 5, 3});
        List<Integer> originalMoves = ticTacToeModel.getGame().getMoveSequence();
        testObserver.resetCollectedMoves();
        ticTacToeModel.replayGame();
        assertEquals(originalMoves, testObserver.getCollectedMoves());
    }

    private void createAndRunGame(TicTacToeModel ticTacToeModel, int[] moves) {
        ticTacToeModel.setupNewGame();
        ticTacToeModel.setGameType(GameType.HUMAN_HUMAN);
        ticTacToeModel.createGame(3);
        GameTestHelper.runGame(ticTacToeModel, moves);
    }
}
