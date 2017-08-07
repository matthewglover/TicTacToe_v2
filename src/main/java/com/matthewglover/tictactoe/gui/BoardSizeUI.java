package com.matthewglover.tictactoe.gui;

import com.matthewglover.tictactoe.core.ModelUpdate;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class BoardSizeUI extends UI {

    private final GridPane gridPane = new GridPane();

    public BoardSizeUI(TicTacToeModel ticTacToeModel) {
        super(ticTacToeModel);
        setRootNode(gridPane);
        buildForm();
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
            ticTacToeModel.setCurrentBoardSize(boardSize);
        });
        gridPane.add(button, 0, buttonNumber);
    }
}
