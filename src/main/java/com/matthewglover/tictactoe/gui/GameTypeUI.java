package com.matthewglover.tictactoe.gui;

import com.matthewglover.tictactoe.core.GameType;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;


public class GameTypeUI extends UI {

    private final FlowPane flowPane = new FlowPane();

    GameTypeUI(TicTacToeModel ticTacToeModel) {
        super(ticTacToeModel);
        setRootNode(flowPane);
        addClasses();
        buildForm();
    }

    private void addClasses() {
        flowPane.getStyleClass().add(CENTER_CSS_CLASS);
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
            ticTacToeModel.setGameType(gameType);
        });
        button.setMaxWidth(Double.MAX_VALUE);
        return button;
    }
}
