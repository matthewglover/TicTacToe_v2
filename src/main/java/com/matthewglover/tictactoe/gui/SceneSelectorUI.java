package com.matthewglover.tictactoe.gui;

import com.matthewglover.tictactoe.core.ModelObserver;
import com.matthewglover.tictactoe.core.ModelUpdate;
import com.matthewglover.tictactoe.core.TicTacToeModel;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.scene.Scene;

public class SceneSelectorUI extends ModelObserver {

    private final GameOptionsUI gameOptionsUI;
    private final BoardSizeUI boardSizeUI;
    private final BoardUI boardUI;
    private final GameStatusUI gameStatusUI;
    private final int gameStatusDelay;
    private final Scene scene;

    public SceneSelectorUI(TicTacToeModel ticTacToeModel, int gameStatusDelay) {
        super(ticTacToeModel);
        this.gameStatusDelay = gameStatusDelay;

        gameOptionsUI = new GameOptionsUI(ticTacToeModel);
        boardSizeUI = new BoardSizeUI(ticTacToeModel);
        boardUI = new BoardUI(ticTacToeModel);
        gameStatusUI = new GameStatusUI(ticTacToeModel);
        scene = new Scene(gameOptionsUI.getNode(), 300, 300);
    }

    @Override
    protected void update(ModelUpdate modelUpdate) {
        switch (modelUpdate) {
            case START_NEW_GAME:
                selectGameOptionsUI();
                break;
            case SET_GAME_TYPE:
                selectBoardSizeUI();
                break;
            case CREATE_GAME:
                selectBoardUI();
                break;
            case GAME_OVER:
                selectGameStatusUI();
                break;
        }
    }

    public Scene getScene() {
        return scene;
    }

    private void selectBoardSizeUI() {
        scene.setRoot(boardSizeUI.getNode());
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
