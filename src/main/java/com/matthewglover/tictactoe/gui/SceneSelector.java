package com.matthewglover.tictactoe.gui;

import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.scene.Scene;

import java.util.Observable;
import java.util.Observer;

public class SceneSelector implements Observer {

    private final TicTacToeModel ticTacToeModel;
    private final GameOptionsUI gameOptionsUI;
    private final BoardUI boardUI;
    private final GameStatusUI gameStatusUI;
    private final int gameStatusDelay;
    private final Scene scene;

    public SceneSelector(TicTacToeModel ticTacToeModel, int gameStatusDelay) {
        this.ticTacToeModel = ticTacToeModel;
        this.gameStatusDelay = gameStatusDelay;

        gameOptionsUI = new GameOptionsUI(ticTacToeModel);
        boardUI = new BoardUI(ticTacToeModel);
        gameStatusUI = new GameStatusUI(ticTacToeModel);
        scene = new Scene(gameOptionsUI.getNode(), 300, 300);

        ticTacToeModel.addObserver(this);
    }

    public Scene getScene() {
        return scene;
    }

    @Override
    public void update(Observable o, Object arg) {
        ModelUpdate modelUpdate = (ModelUpdate) arg;

        switch (modelUpdate) {
            case SET_GAME_TYPE:
                ticTacToeModel.createGame(3);
                selectBoardUI();
                break;
            case START_NEW_GAME:
                selectGameOptionsUI();
                break;
            case GAME_OVER:
                selectGameStatusUI();
                break;
        }
    }

    private void selectBoardUI() {
        scene.setRoot(boardUI.getNode());
    }

    private void selectGameOptionsUI() {
        scene.setRoot(gameOptionsUI.getNode());
    }

    private void selectGameStatusUI() {
        runDelayed(gameStatusDelay, event -> {
            scene.setRoot(gameStatusUI.getNode());
        });
    }

    private void runDelayed(int milliSecondsDelay, EventHandler eventHandler) {
        Task sleeper = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                Thread.sleep(milliSecondsDelay);
                return null;
            }
        };

        sleeper.setOnSucceeded(eventHandler);

        new Thread(sleeper).start();
    }
}
