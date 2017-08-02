package com.matthewglover.tictactoe.gui;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class BoardSizeUI {

    private final TicTacToeModel ticTacToeModel;
    private final GridPane rootNode;

    public BoardSizeUI(TicTacToeModel ticTacToeModel) {
        this.ticTacToeModel = ticTacToeModel;
        rootNode = new GridPane();
        buildForm();
    }

    public Parent getNode() {
        return rootNode;
    }

    private void buildForm() {
        for (int buttonNumber = 0; buttonNumber <= 1; buttonNumber++) {
            addBoardSizeButton(buttonNumber);
        }
    }

    private void addBoardSizeButton(int buttonNumber) {
        int boardSize = buttonNumber + 3;
        Button button = new Button();
        button.setText(boardSize + " X " + boardSize);
        button.setOnAction(event -> {
            ticTacToeModel.setCurrentBoard(boardSize);
        });
        rootNode.add(button, 0, buttonNumber);
    }
}
