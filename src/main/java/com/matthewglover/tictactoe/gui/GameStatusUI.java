package com.matthewglover.tictactoe.gui;

import com.matthewglover.tictactoe.core.ModelUpdate;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class GameStatusUI extends UI {
    private Text text;
    private final FlowPane flowPane = new FlowPane();

    public GameStatusUI(TicTacToeModel ticTacToeModel) {
        super(ticTacToeModel);
        setRootNode(flowPane);
        addClasses();
        build();
    }

    @Override
    protected void update(ModelUpdate modelUpdate) {
        if (modelUpdate == ModelUpdate.GAME_OVER) {
            displayMessage();
        }
    }

    private void addClasses() {
        flowPane.getStyleClass().add(CENTER_CSS_CLASS);
    }

    private void build() {
        addResultText();
        addNewGameButton();
        addReplayGameButton();
    }

    private void addResultText() {
        text = new Text();
        text.setId("game_result");
        text.setFont(Font.font(40));
        text.setFill(Color.WHITE);
        flowPane.getChildren().add(text);
    }

    private void addNewGameButton() {
        Button button = new Button();
        button.setId("new_game");
        button.setText("New Game");
        button.setOnAction(event -> {
            ticTacToeModel.setupNewGame();
        });
        flowPane.getChildren().add(button);
    }

    private void addReplayGameButton() {
        Button button = new Button();
        button.setId("replay_game");
        button.setText("Replay Game");
        button.setOnAction(event -> {
            ticTacToeModel.replayGame();
        });
        flowPane.getChildren().add(button);
    }
    private void displayMessage() {
        String resultMessage = ticTacToeModel.getGame().isWinner()
                ? ticTacToeModel.getGame().getWinner() + " wins!"
                : "It's a draw!";
        text.setText(resultMessage);
    }
}
