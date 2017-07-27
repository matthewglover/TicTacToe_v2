package com.matthewglover.tictactoe.gui;

import com.matthewglover.tictactoe.core.PlayerType;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TicTacToeApp extends Application {

    private BoardUI boardUI;
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
        boardUI = new BoardUI();
        GuiGame game = new GuiGame(3, PlayerType.HUMAN, PlayerType.HUMAN);
        game.addObserver(boardUI);
        game.start();
    }

    private void buildScene() {
        scene = new Scene(boardUI.getNode(), 300, 300);
    }

    private void assembleStage() {
        primaryStage.setTitle("Tic Tac Toe");
        primaryStage.show();
        primaryStage.setScene(scene);
    }
}
