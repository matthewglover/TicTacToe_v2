package com.matthewglover.tictactoe.gui;

import com.matthewglover.tictactoe.core.PlayerSymbol;
import com.matthewglover.tictactoe.core.TicTacToeModel;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class TileUI {
    private final int SIZE = 100;
    private final StackPane stackPane = new StackPane();
    private final Text text = new Text();
    private final TicTacToeModel ticTacToeModel;
    private final int row;
    private final int column;

    public TileUI(TicTacToeModel ticTacToeModel, int row, int column) {
        this.ticTacToeModel = ticTacToeModel;
        this.row = row;
        this.column = column;
        setBorder();
        setBoardPosition();
        setSquare();
    }

    public StackPane getNode() {
        return stackPane;
    }

    public void setClickHandler() {
        stackPane.setOnMouseClicked(event -> {
            ticTacToeModel.gameMove(getSquareNumber());
        });
    }

    private void setBorder() {
        Rectangle border = new Rectangle(SIZE, SIZE);
        border.setFill(null);
        border.setStroke(Color.WHITE);
        stackPane.setAlignment(Pos.CENTER);
        stackPane.getChildren().add(border);
    }

    private void setBoardPosition() {
        int rowOffset = row - 1;
        int columnOffset = column - 1;
        stackPane.setTranslateX(columnOffset * SIZE);
        stackPane.setTranslateY(rowOffset * SIZE);
    }

    private void setSquare() {
        stackPane.setId("square_" + getSquareNumber());
        if (!getSquare().isEmpty()) {
            text.setText(getSquare().toString());
            text.setFont(Font.font(72));
            text.setFill(Color.WHITE);
            stackPane.getChildren().add(text);
        }
    }

    private int getSquareNumber() {
        int squareOffset = (row - 1) * getBoardSize();
        return squareOffset + column;
    }

    private int getBoardSize() {
        return ticTacToeModel.getBoard().getSize();
    }

    private PlayerSymbol getSquare() {
        return ticTacToeModel.getBoard().getSquare(getSquareNumber());
    }
}
