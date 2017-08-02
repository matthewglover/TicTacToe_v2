package com.matthewglover.tictactoe.core;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import static org.junit.Assert.assertEquals;

public class TicTacToeModelTest {
    private final TicTacToeModel ticTacToeModel = new TicTacToeModel();
    private final TestObserver testObserver = new TestObserver();

    @Test
    public void setGameTypeNotifiesObservers() {
        ticTacToeModel.addObserver(testObserver);
        ticTacToeModel.setCurrentGameType(GameType.COMPUTER_HUMAN);
        assertEquals(ModelUpdate.SET_GAME_TYPE, testObserver.getLastUpdate());
    }

    @Test
    public void getPlayerTypeBasedOnGameType() {
        ticTacToeModel.addObserver(testObserver);
        ticTacToeModel.setCurrentGameType(GameType.COMPUTER_HUMAN);
        assertEquals(PlayerType.COMPUTER, ticTacToeModel.getCurrentGameTypeModel().getPlayerType(PlayerSymbol.X));
        assertEquals(PlayerType.HUMAN, ticTacToeModel.getCurrentGameTypeModel().getPlayerType(PlayerSymbol.O));
    }

    @Test
    public void setCurrentBoardCreatesGameAndNotifiesObservers() {
        ticTacToeModel.addObserver(testObserver);
        ticTacToeModel.setCurrentGameType(GameType.HUMAN_HUMAN);
        ticTacToeModel.setCurrentBoard(3);
        assertEquals(ModelUpdate.CREATE_GAME, testObserver.getLastUpdate());
    }

    @Test
    public void createGameNotifiesObservers() {
        ticTacToeModel.addObserver(testObserver);
        ticTacToeModel.setCurrentGameType(GameType.HUMAN_HUMAN);
        ticTacToeModel.createGame(3);
        assertEquals(ModelUpdate.CREATE_GAME, testObserver.getLastUpdate());
    }

    @Test
    public void tracksGameAndCurrentPlayer() {
        ticTacToeModel.setCurrentGameType(GameType.HUMAN_HUMAN);
        ticTacToeModel.createGame(3);
        assertEquals(PlayerSymbol.X, ticTacToeModel.getCurrentGame().getNextPlayerSymbol());
        ticTacToeModel.gameMove(1);
        assertEquals(PlayerSymbol.O, ticTacToeModel.getCurrentGame().getNextPlayerSymbol());
    }

    @Test
    public void delegatesMoveToComputer () {
        ticTacToeModel.setCurrentGameType(GameType.HUMAN_COMPUTER);
        ticTacToeModel.createGame(3);
        ticTacToeModel.gameMove(1);
        assertEquals(PlayerType.COMPUTER, ticTacToeModel.getNextPlayerType());
    }

    private class TestObserver implements Observer {
        private final List<ModelUpdate> modelUpdates = new ArrayList<>();

        @Override
        public void update(Observable o, Object arg) {
            ModelUpdate modelUpdate = (ModelUpdate) arg;
            modelUpdates.add(modelUpdate);
        }

        public ModelUpdate getLastUpdate() {
            return modelUpdates.get(modelUpdates.size() - 1);
        }
    }
}
