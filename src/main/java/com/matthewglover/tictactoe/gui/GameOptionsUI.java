package com.matthewglover.tictactoe.gui;

import com.matthewglover.tictactoe.core.GameType;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;


public class GameOptionsUI {

    private final GridPane grid = new GridPane();
    private final TicTacToeModel ticTacToeModel;

    GameOptionsUI(TicTacToeModel ticTacToeModel) {
        this.ticTacToeModel = ticTacToeModel;
        buildForm();
    }

    public Parent getNode() {
        return  grid;
    }

    private void buildForm() {
        int row = 0;
        for (GameType gameType : GameType.values()) {
            addGameTypeButton(row, gameType);
            row++;
        }
    }

    private void addGameTypeButton(int row, GameType gameType) {
        Button button = new Button();
        button.setText(gameType.getDescription());
        button.setOnAction(event -> {
            ticTacToeModel.setCurrentGameType(gameType);
        });
        grid.add(button, 0, row);
    }
}
