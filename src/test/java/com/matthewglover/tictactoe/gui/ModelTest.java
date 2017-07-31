package com.matthewglover.tictactoe.gui;

import com.matthewglover.tictactoe.core.GameType;
import com.matthewglover.tictactoe.core.PlayerSymbol;
import com.matthewglover.tictactoe.core.PlayerType;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import static org.junit.Assert.assertEquals;

public class ModelTest {


    private final Model model = new Model();
    private final TestObserver testObserver = new TestObserver();

    @Test
    public void setGameTypeNotifiesObservers() {
        model.addObserver(testObserver);
        model.setGameType(GameType.COMPUTER_HUMAN);
        assertEquals(ModelUpdate.SET_GAME_TYPE, testObserver.getLastUpdate());
    }

    @Test
    public void getPlayerTypeBasedOnGameType() {
        model.addObserver(testObserver);
        model.setGameType(GameType.COMPUTER_HUMAN);
        assertEquals(PlayerType.COMPUTER, model.getPlayerXType());
        assertEquals(PlayerType.HUMAN, model.getPlayerOType());
    }

    @Test
    public void createGameNotifiesObservers() {
        model.addObserver(testObserver);
        model.setGameType(GameType.HUMAN_HUMAN);
        model.createGame(3);
        assertEquals(ModelUpdate.CREATE_GAME, testObserver.getLastUpdate());
    }

    @Test
    public void tracksGameAndCurrentPlayer() {
        model.setGameType(GameType.HUMAN_HUMAN);
        model.createGame(3);
        assertEquals(PlayerSymbol.X, model.getNextPlayerSymbol());
        model.move(1);
        assertEquals(PlayerSymbol.O, model.getNextPlayerSymbol());
    }

    @Test
    public void delegatesMoveToComputer () {
        model.setGameType(GameType.HUMAN_COMPUTER);
        model.createGame(3);
        model.move(1);
        assertEquals(PlayerType.HUMAN, model.getNextPlayerType());
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
