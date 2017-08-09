package com.matthewglover.tictactoe.gui;

import com.matthewglover.tictactoe.core.GameType;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.assertEquals;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.hasChildren;

public class BoardSizeUITest extends ApplicationTest {
    private Parent mainNode;
    private final TicTacToeModel ticTacToeModel = new TicTacToeModel();

    @Override
    public void start(Stage stage) throws Exception {
        ticTacToeModel.setupNewGame();
        ticTacToeModel.setGameType(GameType.HUMAN_HUMAN);
        BoardSizeUI boardSizeUI = new BoardSizeUI(ticTacToeModel);
        mainNode = boardSizeUI.getNode();
        buildStage(stage);
    }

    @Test
    public void displaysAllBoardSizes() {
        verifyThat(mainNode, hasChildren(2, ".button"));
        for (int i = 3; i < 4; i++) {
            Button currentButton = from(mainNode).lookup(".button").nth(i - 3).query();
            assertEquals(i + " X " + i, currentButton.getText());
        }
    }

    @Test
    public void boardSizeButtonClickUpdatesModel() {
        for (int i = 0; i <= 1; i++) {
            Button currentButton = from(mainNode).lookup(".button").nth(i).query();
            clickOn(currentButton);
            assertEquals(i + 3, ticTacToeModel.getBoard().getSize());
        }
    }

    private void buildStage(Stage stage) {
        stage.setScene(new Scene(mainNode, 100, 100));
        stage.show();
        stage.toFront();
    }
}
