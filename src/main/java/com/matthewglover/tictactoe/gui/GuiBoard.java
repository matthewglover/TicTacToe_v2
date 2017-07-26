package com.matthewglover.tictactoe.gui;

import com.matthewglover.tictactoe.core.PlayerSymbol;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.util.Observable;

public class GuiBoard extends Observable {
    private final GridPane grid = new GridPane();
    private final int gridSize;

    public GuiBoard(int gridSize) {
        this.gridSize = gridSize;
        formatGrid();
        buildGrid();
    }

    private void formatGrid() {
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
    }

    public GridPane getNode() {
        return grid;
    }

    private void buildGrid() {
        for (int rowIndex = 1; rowIndex <= gridSize; rowIndex++) {
            addRow(rowIndex);
        }
    }

    private void addRow(int row) {
        for (int column = 1; column <= gridSize; column++) {
            addSquare(row, column);
        }
    }

    private void addSquare(int row, int column) {
        Button button = new Button();
        button.setId("square_" + calcSquareNumber(row, column));
        button.setOnAction(squareClickHandler(row, column));

        grid.add(button, column, row);
    }

    private EventHandler<ActionEvent> squareClickHandler(int row, int column) {
        return (ActionEvent event) -> {
            setChanged();
            notifyObservers(calcSquareNumber(row, column));
        };
    }

    private int calcSquareNumber(int row, int column) {
        int squareOffset = (row - 1) * gridSize;
        return squareOffset + column;
    }

    public void setSquare(int squareNumber, PlayerSymbol playerSymbol) {
        Button button = (Button) grid.lookup("#square_" + squareNumber);
        button.setText(playerSymbol.toString());
        button.setDisable(true);
    }
}
