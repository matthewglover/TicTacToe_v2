package com.matthewglover.tictactoe.gui;

import com.matthewglover.tictactoe.core.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;


public class BoardUI extends UI {
    private final GridPane gridPane = new GridPane();

    public BoardUI(TicTacToeModel ticTacToeModel) {
        super(ticTacToeModel);
        setRootNode(gridPane);
        formatBoard();
    }

    @Override
    public void update(ModelUpdate modelUpdate) {
        if (isBoardStateChange(modelUpdate)) {
            buildBoard();
        }
    }

    private void formatBoard() {
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
    }

    private boolean isBoardStateChange(ModelUpdate modelUpdate) {
        return modelUpdate == ModelUpdate.CREATE_GAME ||
                modelUpdate == ModelUpdate.MAKE_MOVE ||
                modelUpdate == ModelUpdate.GAME_OVER;
    }

    private void buildBoard() {
        gridPane.getChildren().clear();

        for (int rowIndex = 1; rowIndex <= getBoardSize(); rowIndex++) {
            addRow(rowIndex);
        }
    }

    private int getBoardSize() {
        return getBoard().getSize();
    }

    private Board getBoard() {
        return ticTacToeModel.getCurrentGame().getBoard();
    }

    private void addRow(int row) {
        for (int column = 1; column <= getBoardSize(); column++) {
            addSquare(row, column);
        }
    }

    private void addSquare(int row, int column) {
        int squareNumber = calcSquareNumber(row, column);
        PlayerSymbol square = getBoardSquare(squareNumber);
        Button squareButton = buildSquareButton(square, squareNumber);
        gridPane.add(squareButton, column, row);
    }

    private PlayerSymbol getBoardSquare(int squareNumber) {
        return getBoard().getSquare(squareNumber);
    }

    private Button buildSquareButton(PlayerSymbol square, int squareNumber) {
        Button button = new Button();
        button.setId("square_" + squareNumber);
        button.setText(formatSquare(square));

        if (isActiveButton(square)) {
            button.setOnAction(event -> move(squareNumber));
        } else {
            button.setDisable(true);
        }

        return button;
    }

    private boolean isActiveButton(PlayerSymbol square) {
        return isActiveGame() && isActiveSquare(square);
    }

    private boolean isActiveGame() {
        return !ticTacToeModel.getCurrentGame().isOver();
    }

    private boolean isActiveSquare(PlayerSymbol square) {
        return isNextPlayerHuman() && square.isEmpty();
    }

    private boolean isNextPlayerHuman() {
        return ticTacToeModel.getNextPlayerType().isHuman();
    }

    private String formatSquare(PlayerSymbol square) {
        return square.isEmpty()
                ? ""
                : square.toString();
    }

    private int calcSquareNumber(int row, int column) {
        int squareOffset = (row - 1) * getBoardSize();
        return squareOffset + column;
    }

    private void move(int squareNumber) {
        ticTacToeModel.gameMove(squareNumber);
    }
}
