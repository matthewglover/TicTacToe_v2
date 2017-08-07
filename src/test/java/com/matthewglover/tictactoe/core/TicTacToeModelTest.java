package com.matthewglover.tictactoe.core;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TicTacToeModelTest {
    private final TicTacToeModel ticTacToeModel = new TicTacToeModel();
    private final TicTacToeModelTestObserver testObserver = new TicTacToeModelTestObserver(ticTacToeModel);

    @Test
    public void setGameTypeNotifiesObservers() {
        ticTacToeModel.startNewGame();
        ticTacToeModel.setCurrentGameType(GameType.COMPUTER_HUMAN);
        assertEquals(ModelUpdate.SET_GAME_TYPE, testObserver.getLastUpdate());
    }

    @Test
    public void getPlayerTypeBasedOnGameType() {
        ticTacToeModel.startNewGame();
        ticTacToeModel.setCurrentGameType(GameType.COMPUTER_HUMAN);
        assertEquals(PlayerType.COMPUTER, ticTacToeModel.getCurrentGameTypeModel().getPlayerType(PlayerSymbol.X));
        assertEquals(PlayerType.HUMAN, ticTacToeModel.getCurrentGameTypeModel().getPlayerType(PlayerSymbol.O));
    }

    @Test
    public void tracksGameAndCurrentPlayer() {
        ticTacToeModel.startNewGame();
        ticTacToeModel.setCurrentGameType(GameType.HUMAN_HUMAN);
        ticTacToeModel.createGame(3);
        assertEquals(PlayerSymbol.X, ticTacToeModel.getCurrentGame().getNextPlayerSymbol());
        ticTacToeModel.gameMove(1);
        assertEquals(PlayerSymbol.O, ticTacToeModel.getCurrentGame().getNextPlayerSymbol());
    }

    @Test
    public void delegatesMoveToComputer () {
        ticTacToeModel.startNewGame();
        ticTacToeModel.setCurrentGameType(GameType.HUMAN_COMPUTER);
        ticTacToeModel.createGame(3);
        ticTacToeModel.gameMove(1);
        assertEquals(PlayerType.COMPUTER, ticTacToeModel.getNextPlayerType());
    }
}
