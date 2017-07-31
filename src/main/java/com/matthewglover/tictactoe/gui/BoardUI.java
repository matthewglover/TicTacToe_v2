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
    private final GridPane board;
    private final Model model;

    public BoardUI(Model model) {
        this.model = model;
        this.model.addObserver(this);
        board = new GridPane();
        formatBoard();
    }

    public GridPane getNode() {
        return board;
    }

    @Override
    public void update(Observable o, Object arg) {
        ModelUpdate modelUpdate = (ModelUpdate) arg;

        if (isBoardStateChange(modelUpdate)) {
            buildBoard();
        }
    }

    private void formatBoard() {
        board.setAlignment(Pos.CENTER);
        board.setHgap(10);
        board.setVgap(10);
        board.setPadding(new Insets(25, 25, 25, 25));
    }

    private boolean isBoardStateChange(ModelUpdate modelUpdate) {
        return modelUpdate == ModelUpdate.CREATE_GAME ||
                modelUpdate == ModelUpdate.MOVE ||
                modelUpdate == ModelUpdate.GAME_OVER;
    }

    private void buildBoard() {
        if (!board.getChildren().isEmpty()) {
            board.getChildren().clear();
        }

        for (int rowIndex = 1; rowIndex <= model.getBoardSize(); rowIndex++) {
            addRow(rowIndex);
        }
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
        board.add(button, column, row);
    }

    private Button buildSquareButton(PlayerSymbol square, int squareNumber) {
        Button button = new Button();
        button.setId("square_" + squareNumber);

        if (isActiveGame() && isActiveSquare(square)) {
            button.setOnAction(event -> makeMove(squareNumber));
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

    private void makeMove(int squareNumber) {
        model.move(squareNumber);
    }
}
