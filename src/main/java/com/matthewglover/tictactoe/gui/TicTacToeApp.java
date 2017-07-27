package com.matthewglover.tictactoe.gui;

import com.matthewglover.tictactoe.core.Game;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TicTacToeApp extends Application {

    private GuiGame guiGame;
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
        guiGame = new GuiGame();
        Game game = new Game(3);
        game.addObserver(guiGame);
        game.start();
    }

    private void buildScene() {
        scene = new Scene(guiGame.getNode(), 300, 300);
    }

    private void assembleStage() {
        primaryStage.setTitle("Tic Tac Toe");
        primaryStage.show();
        primaryStage.setScene(scene);
    }
}
