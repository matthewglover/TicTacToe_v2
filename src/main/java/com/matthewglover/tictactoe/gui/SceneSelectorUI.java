package com.matthewglover.tictactoe.gui;

import com.matthewglover.tictactoe.core.ModelObserver;
import com.matthewglover.tictactoe.core.ModelUpdate;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.scene.Scene;

public class SceneSelectorUI extends ModelObserver {

    private final GameTypeUI gameTypeUI;
    private final BoardSizeUI boardSizeUI;
    private final BoardUI boardUI;
    private final GameStatusUI gameStatusUI;
    private final int gameStatusDelay;
    private final Scene scene;

    public SceneSelectorUI(TicTacToeModel ticTacToeModel, int gameStatusDelay) {
        super(ticTacToeModel);
        this.gameStatusDelay = gameStatusDelay;

        gameTypeUI = new GameTypeUI(ticTacToeModel);
        boardSizeUI = new BoardSizeUI(ticTacToeModel);
        boardUI = new BoardUI(ticTacToeModel);
        gameStatusUI = new GameStatusUI(ticTacToeModel);

        scene = new Scene(gameTypeUI.getNode(), 500, 500);
        scene.getStylesheets().add(this.getClass().getResource("scene.css").toExternalForm());
    }

    @Override
    protected void update(ModelUpdate modelUpdate) {
        switch (modelUpdate) {
            case SETUP_NEW_GAME:
                selectGameType();
                break;
            case SET_GAME_TYPE:
                selectBoardSize();
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

    private void selectBoardSize() {
        scene.setRoot(boardSizeUI.getNode());
    }

    private void selectBoardUI() {
        scene.setRoot(boardUI.getNode());
    }

    private void selectGameType() {
        scene.setRoot(gameTypeUI.getNode());
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
