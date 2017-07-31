package com.matthewglover.tictactoe.gui;

import com.matthewglover.tictactoe.core.PlayerSymbol;
import com.matthewglover.tictactoe.core.PlayerType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;


public class BoardUI extends UI {

    public BoardUI(Model model) {
        super(model, new GridPane());
        formatBoard();
    }

    @Override
    public void update(ModelUpdate modelUpdate) {
        if (isBoardStateChange(modelUpdate)) {
            buildBoard();
        }
    }

    private void formatBoard() {
        getBoard().setAlignment(Pos.CENTER);
        getBoard().setHgap(10);
        getBoard().setVgap(10);
        getBoard().setPadding(new Insets(25, 25, 25, 25));
    }

    private boolean isBoardStateChange(ModelUpdate modelUpdate) {
        return modelUpdate == ModelUpdate.CREATE_GAME ||
                modelUpdate == ModelUpdate.MOVE ||
                modelUpdate == ModelUpdate.GAME_OVER;
    }

    private void buildBoard() {
        getBoard().getChildren().clear();

        for (int rowIndex = 1; rowIndex <= model.getBoardSize(); rowIndex++) {
            addRow(rowIndex);
        }
    }

    private GridPane getBoard() {
        return (GridPane) rootNode;
    }

    private void addRow(int row) {
        for (int column = 1; column <= model.getBoardSize(); column++) {
            addSquare(row, column);
        }
    }

    private void addSquare(int row, int column) {
        int squareNumber = calcSquareNumber(row, column);
        PlayerSymbol square = model.getBoardSquare(squareNumber);
        Button button = buildSquareButton(square, squareNumber);
        getBoard().add(button, column, row);
    }

    private Button buildSquareButton(PlayerSymbol square, int squareNumber) {
        Button button = new Button();
        button.setId("square_" + squareNumber);

        if (isActiveGame() && isActiveSquare(square)) {
            button.setOnAction(event -> move(squareNumber));
        } else {
            button.setText(formatSquare(square));
            button.setDisable(true);
        }

        return button;
    }

    private String formatSquare(PlayerSymbol square) {
        return square.isEmpty()
                ? ""
                : square.toString();
    }

    private boolean isActiveGame() {
        return !model.isGameOver();
    }

    private boolean isActiveSquare(PlayerSymbol square) {
        return model.getNextPlayerType() == PlayerType.HUMAN
                && square.isEmpty();
    }

    private int calcSquareNumber(int row, int column) {
        int squareOffset = (row - 1) * model.getBoardSize();
        return squareOffset + column;
    }

    private void move(int squareNumber) {
        model.move(squareNumber);
    }
}
