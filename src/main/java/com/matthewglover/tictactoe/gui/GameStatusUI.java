package com.matthewglover.tictactoe.gui;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.Observable;
import java.util.Observer;

public class GameStatusUI implements Observer {
    private final Model model;
    private final GridPane grid = new GridPane();
    private Label label;

    public GameStatusUI(Model model) {
        this.model = model;
        model.addObserver(this);
        build();
    }

    private void build() {
        label = new Label();
        label.setId("game_result");
        grid.add(label, 1, 1);

        Button button = new Button();
        button.setId("new_game");
        button.setText("New Game");
        button.setOnAction(event -> {
            model.reset();
        });
        grid.add(button, 1, 2);
    }

    @Override
    public void update(Observable o, Object arg) {
        ModelUpdate modelUpdate = (ModelUpdate) arg;

        if (modelUpdate == ModelUpdate.GAME_OVER) {
            displayMessage();
        }
    }

    private void displayMessage() {
        String resultMessage = model.isGameWinner()
                ? model.getGameWinner() + " wins!"
                : "It's a draw!";
        label.setText(resultMessage);
    }

    public Parent getNode() {
        return grid;
    }
}
