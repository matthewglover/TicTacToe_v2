package com.matthewglover.tictactoe.gui;

import com.matthewglover.tictactoe.core.*;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;


public class BoardUI extends UI {
    private final FlowPane flowPane = new FlowPane();
    private final Pane pane = new Pane();

    public BoardUI(TicTacToeModel ticTacToeModel) {
        super(ticTacToeModel);
        setRootNode(flowPane);
        buildFlowPane();
    }

    private void buildFlowPane() {
        flowPane.getChildren().add(pane);
        flowPane.setHgap(10);
        flowPane.setVgap(10);
        flowPane.setOrientation(Orientation.VERTICAL);
        flowPane.setAlignment(Pos.CENTER);
    }

    @Override
    public void update(ModelUpdate modelUpdate) {
        if (modelUpdate.isBoardChange()) {
            buildBoard();
        }
    }

    private void buildBoard() {
        pane.getChildren().clear();
        pane.setPrefSize(300, 300);

        for (int rowIndex = 1; rowIndex <= getBoardSize(); rowIndex++) {
            addRow(rowIndex);
        }
    }

    private int getBoardSize() {
        return ticTacToeModel.getCurrentBoard().getSize();
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
        return !ticTacToeModel.getCurrentGame().isOver() && !ticTacToeModel.isBoardLocked();
    }
}
