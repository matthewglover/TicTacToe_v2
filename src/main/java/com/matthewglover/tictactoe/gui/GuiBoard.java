package com.matthewglover.tictactoe.gui;

import com.matthewglover.tictactoe.core.Game;
import com.matthewglover.tictactoe.core.PlayerSymbol;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;


public class GuiBoard {
    private final GridPane grid = new GridPane();
    private final GuiGame guiGame;

    public GuiBoard(GuiGame guiGame) {
        this.guiGame = guiGame;
        formatGrid();
    }

    public GridPane getNode() {
        return grid;
    }

    public void buildGrid(Game game) {
        grid.getChildren().clear();

        for (int rowIndex = 1; rowIndex <= game.getBoardSize(); rowIndex++) {
            addRow(game, rowIndex);
        }
    }

    private void formatGrid() {
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
    }

    private void addRow(Game game, int row) {
        for (int column = 1; column <= game.getBoardSize(); column++) {
            addSquare(game, row, column);
        }
    }

    private void addSquare(Game game, int row, int column) {
        int squareNumber = calcSquareNumber(game.getBoardSize(), row, column);
        PlayerSymbol square = game.getBoardSquare(squareNumber);

        Button button = new Button();
        button.setId("square_" + squareNumber);

        if (square.isEmpty()) {
            button.setOnAction(event -> guiGame.makeMove(game, squareNumber));
        } else {
            button.setText(square.toString());
            button.setDisable(true);
        }

        grid.add(button, column, row);
    }

    private int calcSquareNumber(int gridSize, int row, int column) {
        int squareOffset = (row - 1) * gridSize;
        return squareOffset + column;
    }
}
