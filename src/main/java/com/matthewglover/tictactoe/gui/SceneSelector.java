package com.matthewglover.tictactoe.gui;

import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.scene.Scene;

import java.util.Observable;
import java.util.Observer;

public class SceneSelector implements Observer {

    private final Model model;
    private final GameOptionsUI gameOptionsUI;
    private final BoardUI boardUI;
    private final GameStatusUI gameStatusUI;
    private final int gameStatusDelay;
    private final Scene scene;

    public SceneSelector(Model model, int gameStatusDelay) {
        this.model = model;
        this.gameStatusDelay = gameStatusDelay;
        gameOptionsUI = new GameOptionsUI(model);
        boardUI = new BoardUI(model);
        gameStatusUI = new GameStatusUI(model);
        scene = new Scene(gameOptionsUI.getNode(), 100, 100);
        model.addObserver(this);
    }

    public Scene getScene() {
        return scene;
    }

    @Override
    public void update(Observable o, Object arg) {
        ModelUpdate modelUpdate = (ModelUpdate) arg;

        if (modelUpdate.isSetGameType()) {
            model.createGame(3);
            selectBoardUI();
        }

        if (modelUpdate == ModelUpdate.GAME_OVER) {
            selectGameStatusUI();
        }

        if (modelUpdate == ModelUpdate.START_NEW_GAME) {
            selectGameOptionsUI();
        }
    }

    private void selectGameOptionsUI() {
        scene.setRoot(gameOptionsUI.getNode());
    }

    private void selectGameStatusUI() {
        runDelayed(gameStatusDelay, event -> {
            scene.setRoot(gameStatusUI.getNode());
        });
    }

    private void selectBoardUI() {
        scene.setRoot(boardUI.getNode());
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
