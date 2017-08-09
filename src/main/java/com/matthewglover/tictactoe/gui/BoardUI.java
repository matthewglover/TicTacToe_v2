package com.matthewglover.tictactoe.gui;

import com.matthewglover.tictactoe.core.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;


public class BoardUI extends UI {
    private final FlowPane flowPane = new FlowPane();
    private final Pane pane = new Pane();

    public BoardUI(TicTacToeModel ticTacToeModel) {
        super(ticTacToeModel);
        setRootNode(flowPane);
        addClasses();
        flowPane.getChildren().add(pane);
    }

    private void addClasses() {
        flowPane.getStyleClass().add(CENTER_CSS_CLASS);
    }

    @Override
    public void update(ModelUpdate modelUpdate) {
        if (modelUpdate.isBoardChange()) {
            buildBoard();
        }
    }

    private void buildBoard() {
        pane.getChildren().clear();
        pane.setMinSize(400, 400);
        pane.setMaxSize(400, 400);
        pane.setPrefSize(400, 400);

        for (int rowIndex = 1; rowIndex <= getBoardSize(); rowIndex++) {
            addRow(rowIndex);
        }
    }

    private int getBoardSize() {
        return ticTacToeModel.getBoard().getSize();
    }

    private void addRow(int row) {
        for (int column = 1; column <= getBoardSize(); column++) {
            addTile(row, column);
        }
    }

    private void addTile(int row, int column) {
        TileUI tileUI = new TileUI(ticTacToeModel, row, column);
        if (isActiveBoard()) {
            tileUI.setClickHandler();
        }
        pane.getChildren().add(tileUI.getNode());
    }

    private boolean isActiveBoard() {
        return !ticTacToeModel.getGame().isOver() && !ticTacToeModel.isBoardLocked();
    }
}
