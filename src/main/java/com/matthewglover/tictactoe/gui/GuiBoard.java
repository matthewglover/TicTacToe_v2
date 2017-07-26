package com.matthewglover.tictactoe.gui;

import com.matthewglover.tictactoe.core.Game;
import com.matthewglover.tictactoe.core.PlayerSymbol;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.util.Observable;
import java.util.Observer;

public class GuiBoard extends Observable implements Observer {
    private final GridPane grid = new GridPane();

    public GuiBoard() {
        formatGrid();
    }

    public GridPane getNode() {
        return grid;
    }

    @Override
    public void update(Observable o, Object arg) {
        Game game = (Game) o;
        buildGrid(game.getBoard().getSize());
    }

    private void formatGrid() {
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
    }

    private void buildGrid(int gridSize) {
        for (int rowIndex = 1; rowIndex <= gridSize; rowIndex++) {
            addRow(gridSize, rowIndex);
        }
    }

    private void addRow(int gridSize, int row) {
        for (int column = 1; column <= gridSize; column++) {
            addSquare(gridSize, row, column);
        }
    }

    private void addSquare(int gridSize, int row, int column) {
        Button button = new Button();
        button.setId("square_" + calcSquareNumber(gridSize, row, column));
        button.setOnAction(squareClickHandler(gridSize, row, column));

        grid.add(button, column, row);
    }

    private EventHandler<ActionEvent> squareClickHandler(int gridSize, int row, int column) {
        return (ActionEvent event) -> {
            setChanged();
            notifyObservers(calcSquareNumber(gridSize, row, column));
        };
    }

    private int calcSquareNumber(int gridSize, int row, int column) {
        int squareOffset = (row - 1) * gridSize;
        return squareOffset + column;
    }

    public void setSquare(int squareNumber, PlayerSymbol playerSymbol) {
        Button button = (Button) grid.lookup("#square_" + squareNumber);
        button.setText(playerSymbol.toString());
        button.setDisable(true);
    }
}
