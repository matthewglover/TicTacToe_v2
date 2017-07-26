package com.matthewglover.tictactoe.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TicTacToeApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Tic Tac Toe");
        primaryStage.show();

        Scene scene = new Scene(new GuiBoard(3).getNode(), 300, 300);
        primaryStage.setScene(scene);
    }
}
