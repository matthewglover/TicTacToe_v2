package com.matthewglover.tictactoe.gui;

import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;

public class BoardSizeUI extends UI {

    private final FlowPane flowPane = new FlowPane();

    public BoardSizeUI(TicTacToeModel ticTacToeModel) {
        super(ticTacToeModel);
        setRootNode(flowPane);
        addClasses();
        buildForm();
    }

    private void addClasses() {
        flowPane.getStyleClass().add(CENTER_CSS_CLASS);
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
