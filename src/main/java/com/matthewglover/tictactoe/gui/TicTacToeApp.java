package com.matthewglover.tictactoe.gui;

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
        SceneSelectorUI sceneSelectorUI = new SceneSelectorUI(ticTacToeModel, 1000);
        assembleStage(primaryStage, sceneSelectorUI);
    }

    private void assembleStage(Stage primaryStage, SceneSelectorUI sceneSelectorUI) {
        primaryStage.setTitle("Tic Tac Toe");
        primaryStage.show();
        primaryStage.setScene(sceneSelectorUI.getScene());
    }
}
