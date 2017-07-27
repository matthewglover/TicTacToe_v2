package com.matthewglover.tictactoe.gui;

import com.matthewglover.tictactoe.core.PlayerSymbol;
import com.matthewglover.tictactoe.core.PlayerType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.util.Observable;
import java.util.Observer;


public class BoardUI implements Observer {
    private final GridPane grid = new GridPane();

    public BoardUI() {
        formatGrid();
    }

    public GridPane getNode() {
        return grid;
    }

    @Override
    public void update(Observable o, Object arg) {
        GuiGame game = (GuiGame) o;
        buildGrid(game);
    }

    private void formatGrid() {
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
    }

    private void buildGrid(GuiGame game) {
        grid.getChildren().clear();

        for (int rowIndex = 1; rowIndex <= game.getBoardSize(); rowIndex++) {
            addRow(game, rowIndex);
        }
    }

    private void addRow(GuiGame game, int row) {
        for (int column = 1; column <= game.getBoardSize(); column++) {
            addSquare(game, row, column);
        }
    }

    private void addSquare(GuiGame game, int row, int column) {
        int squareNumber = calcSquareNumber(game.getBoardSize(), row, column);
        PlayerSymbol square = game.getBoardSquare(squareNumber);
        Button button = buildSquareButton(game, square, squareNumber);
        grid.add(button, column, row);
    }

    private Button buildSquareButton(GuiGame game, PlayerSymbol square, int squareNumber) {
        Button button = new Button();
        button.setId("square_" + squareNumber);

        if (isHumanPlayersTurn(game) && square.isEmpty()) {
            button.setOnAction(event -> makeMove(game, squareNumber));
        } else {
            button.setText(square.toString());
            button.setDisable(true);
        }

        return button;
    }

    private boolean isHumanPlayersTurn(GuiGame game) {
        return game.getNextPlayerType() == PlayerType.HUMAN;
    }

    private int calcSquareNumber(int gridSize, int row, int column) {
        int squareOffset = (row - 1) * gridSize;
        return squareOffset + column;
    }

    private void makeMove(GuiGame game, int squareNumber) {
        game.move(squareNumber);
    }
}
