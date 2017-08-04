package com.matthewglover.tictactoe.gui;

import com.matthewglover.tictactoe.core.GameType;
import com.matthewglover.tictactoe.core.ModelUpdate;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;


public class GameTypeUI extends UI {

    private final GridPane gridPane = new GridPane();

    GameTypeUI(TicTacToeModel ticTacToeModel) {
        super(ticTacToeModel);
        setRootNode(gridPane);
        buildForm();
    }

    @Override
    protected void update(ModelUpdate modelUpdate) {

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
        gridPane.add(button, 0, row);
    }
}
