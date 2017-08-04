package com.matthewglover.tictactoe.gui;

import com.matthewglover.tictactoe.core.ModelUpdate;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;


public class GameStatusUI extends UI {
    private Label label;
    private final GridPane gridPane = new GridPane();

    public GameStatusUI(TicTacToeModel ticTacToeModel) {
        super(ticTacToeModel);
        setRootNode(gridPane);
        build();
    }

    @Override
    protected void update(ModelUpdate modelUpdate) {
        if (modelUpdate == ModelUpdate.GAME_OVER) {
            displayMessage();
        }
    }

    private void build() {
        label = new Label();
        label.setId("game_result");
        gridPane.add(label, 1, 1);

        Button button = new Button();
        button.setId("new_game");
        button.setText("New Game");
        button.setOnAction(event -> {
            ticTacToeModel.startNewGame();
        });
        gridPane.add(button, 1, 2);
    }

    private void displayMessage() {
        String resultMessage = ticTacToeModel.getCurrentGame().isWinner()
                ? ticTacToeModel.getCurrentGame().getWinner() + " wins!"
                : "It's a draw!";
        label.setText(resultMessage);
    }
}
