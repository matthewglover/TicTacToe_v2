package com.matthewglover.tictactoe.gui;

import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;

public class BoardSizeUI extends UI {

    private final FlowPane flowPane = new FlowPane();

    public BoardSizeUI(TicTacToeModel ticTacToeModel) {
        super(ticTacToeModel);
        setRootNode(flowPane);
        setLayout();
        buildForm();
    }

    private void setLayout() {
        flowPane.setHgap(10);
        flowPane.setVgap(10);
        flowPane.setOrientation(Orientation.VERTICAL);
        flowPane.setAlignment(Pos.CENTER);
    }

    private void buildForm() {
        for (int boardSize = 3; boardSize <= 4; boardSize++) {
            flowPane.getChildren().add(buildBoardSizeButton(boardSize));
        }
    }

    private Button buildBoardSizeButton(int boardSize) {
        Button button = new Button(boardSize + " X " + boardSize);
        button.setOnAction(event -> ticTacToeModel.setCurrentBoardSize(boardSize));
        return button;
    }
}
