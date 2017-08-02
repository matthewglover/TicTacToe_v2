package com.matthewglover.tictactoe.gui;

import com.matthewglover.tictactoe.core.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;


public class BoardUI extends ModelObserver implements UI {
    private final GridPane rootNode;

    public BoardUI(TicTacToeModel ticTacToeModel) {
        super(ticTacToeModel);
        this.rootNode = new GridPane();
        formatBoard();
    }

    @Override
    public void update(ModelUpdate modelUpdate) {
        if (isBoardStateChange(modelUpdate)) {
            buildBoard();
        }
    }

    @Override
    public Parent getNode() {
        return rootNode;
    }

    private void formatBoard() {
        rootNode.setAlignment(Pos.CENTER);
        rootNode.setHgap(10);
        rootNode.setVgap(10);
        rootNode.setPadding(new Insets(25, 25, 25, 25));
    }

    private boolean isBoardStateChange(ModelUpdate modelUpdate) {
        return modelUpdate == ModelUpdate.CREATE_GAME ||
                modelUpdate == ModelUpdate.MOVE ||
                modelUpdate == ModelUpdate.GAME_OVER;
    }

    private void buildBoard() {
        rootNode.getChildren().clear();

        for (int rowIndex = 1; rowIndex <= getBoardSize(); rowIndex++) {
            addRow(rowIndex);
        }
    }

    private int getBoardSize() {
        return getBoard().getSize();
    }

    private void addRow(int row) {
        for (int column = 1; column <= getBoardSize(); column++) {
            addSquare(row, column);
        }
    }

    private void addSquare(int row, int column) {
        int squareNumber = calcSquareNumber(row, column);
        PlayerSymbol square = getBoardSquare(squareNumber);
        Button button = buildSquareButton(square, squareNumber);
        rootNode.add(button, column, row);
    }

    private Board getBoard() {
        return ticTacToeModel.getCurrentGame().getBoard();
    }

    private PlayerSymbol getBoardSquare(int squareNumber) {
        return getBoard().getSquare(squareNumber);
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
        return !ticTacToeModel.getCurrentGame().isOver();
    }

    private boolean isActiveSquare(PlayerSymbol square) {
        return ticTacToeModel.getNextPlayerType() == PlayerType.HUMAN
                && square.isEmpty();
    }

    private int calcSquareNumber(int row, int column) {
        int squareOffset = (row - 1) * getBoardSize();
        return squareOffset + column;
    }

    private void move(int squareNumber) {
        ticTacToeModel.gameMove(squareNumber);
    }
}
