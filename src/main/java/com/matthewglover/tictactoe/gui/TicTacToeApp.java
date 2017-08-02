package com.matthewglover.tictactoe.gui;

import com.matthewglover.tictactoe.core.TicTacToeModel;
import javafx.application.Application;
import javafx.stage.Stage;

public class TicTacToeApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        TicTacToeModel ticTacToeModel = new TicTacToeModel();
        ticTacToeModel.setComputerMoveDelay(1000);
        SceneSelector sceneSelector = new SceneSelector(ticTacToeModel, 1000);
        assembleStage(primaryStage, sceneSelector);
    }

    private void assembleStage(Stage primaryStage, SceneSelector sceneSelector) {
        primaryStage.setTitle("Tic Tac Toe");
        primaryStage.show();
        primaryStage.setScene(sceneSelector.getScene());
    }
}