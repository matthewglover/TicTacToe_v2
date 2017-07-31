package com.matthewglover.tictactoe.gui;

import javafx.application.Application;
import javafx.stage.Stage;

public class TicTacToeApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Model model = new Model();
        SceneSelector sceneSelector = new SceneSelector(model, 1000);
        assembleStage(primaryStage, sceneSelector);
    }

    private void assembleStage(Stage primaryStage, SceneSelector sceneSelector) {
        primaryStage.setTitle("Tic Tac Toe");
        primaryStage.show();
        primaryStage.setScene(sceneSelector.getScene());
    }
}
