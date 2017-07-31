package com.matthewglover.tictactoe.gui;

import com.matthewglover.tictactoe.core.GameType;
import com.matthewglover.tictactoe.core.PlayerSymbol;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;


import static org.junit.Assert.assertEquals;

public class BoardUIGameOverTest extends ApplicationTest {

    private Parent mainNode;
    private Model model;

    @Override
    public void start(Stage stage) throws Exception {
        model = new Model();
        BoardUI boardUI = new BoardUI(model);
        mainNode = boardUI.getNode();
        model.setGameType(GameType.HUMAN_HUMAN);
        model.createGame(3);
        model.move(1);
        model.move(4);
        model.move(2);
        model.move(5);
        model.move(3);
        buildStage(stage);
    }

    @Test
    public void drawsFinalStateBoard() {
        assertEquals(PlayerSymbol.X.toString(), getSquare(3).getText());
    }

    private Button getSquare(int squareNumber) {
        return from(mainNode).lookup("#square_" + squareNumber).query();
    }

    private void buildStage(Stage stage) {
        stage.setScene(new Scene(mainNode, 100, 100));
        stage.show();
        stage.toFront();
    }
}
