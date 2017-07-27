package com.matthewglover.tictactoe.gui;

import com.matthewglover.tictactoe.core.Game;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TicTacToeApp extends Application {

    private GuiBoard guiBoard;
    private Scene scene;
    private Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;

        buildBoard();
        buildScene();
        assembleStage();
    }

    private void buildBoard() {
        guiBoard = new GuiBoard();
        Game game = new Game(3);
        game.addObserver(guiBoard);
        game.start();
    }

    private void buildScene() {
        scene = new Scene(guiBoard.getNode(), 300, 300);
    }

    private void assembleStage() {
        primaryStage.setTitle("Tic Tac Toe");
        primaryStage.show();
        primaryStage.setScene(scene);
    }
}
