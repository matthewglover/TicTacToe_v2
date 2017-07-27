package com.matthewglover.tictactoe.gui;

import com.matthewglover.tictactoe.core.GameType;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.util.Observable;

public class GameOptionsUI extends Observable {

    private final GridPane grid = new GridPane();

    GameOptionsUI() {
        buildForm();
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
        button.setOnAction(event -> notifyChange(gameType));
        grid.add(button, 0, row);
    }

    private void notifyChange(GameType gameType) {
        setChanged();
        notifyObservers(gameType);
    }

    public Parent getNode() {
        return  grid;
    }
}
