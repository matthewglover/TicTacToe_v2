package com.matthewglover.tictactoe.gui;

import com.matthewglover.tictactoe.core.GameType;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;


public class GameTypeUI extends UI {

    private final FlowPane flowPane = new FlowPane();

    GameTypeUI(TicTacToeModel ticTacToeModel) {
        super(ticTacToeModel);
        setRootNode(flowPane);
        setLayout();
        buildForm();
    }

    private void setLayout() {
        flowPane.setHgap(10);
        flowPane.setVgap(10);
        flowPane.setOrientation(Orientation.VERTICAL);
        flowPane.setAlignment(Pos.CENTER);
    }

    private void buildForm() {
        for (GameType gameType : GameType.values()) {
            flowPane.getChildren().add(buildButton(gameType));
        }
    }

    private Button buildButton(GameType gameType) {
        Button button = new Button();
        button.setText(gameType.getDescription());
        button.setOnAction(event -> {
            ticTacToeModel.setCurrentGameType(gameType);
        });
        button.setMaxWidth(Double.MAX_VALUE);
        return button;
    }
}
