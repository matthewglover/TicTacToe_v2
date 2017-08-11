package com.matthewglover.tictactoe.gui;

import com.matthewglover.tictactoe.core.GameType;
import com.matthewglover.tictactoe.core.ImmediateRunner;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.assertEquals;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.hasChildren;

public class GameTypeUITest extends ApplicationTest {

    private Parent mainNode;
    private TicTacToeModel ticTacToeModel = new TicTacToeModel(new ImmediateRunner());

    @Override
    public void start(Stage stage) throws Exception {
        GameTypeUI gameTypeUI = new GameTypeUI(ticTacToeModel);

        ticTacToeModel.setupNewGame();
        mainNode = gameTypeUI.getNode();
        buildStage(stage);
    }

    @Test
    public void displaysAllGameTypes() {
        verifyThat(mainNode, hasChildren(GameType.getSelectableValues().size(), ".button"));
        for (int i = 0; i < GameType.getSelectableValues().size(); i++) {
            Button currentButton = from(mainNode).lookup(".button").nth(i).query();
            assertEquals(GameType.values()[i].getDescription(), currentButton.getText());
        }
    }

    @Test
    public void gameButtonClickNotifiesModel() {
        int counter = 0;
        for (GameType gameType : GameType.getSelectableValues()) {
            Button currentButton = from(mainNode).lookup(".button").nth(counter).query();
            clickOn(currentButton);
            assertEquals(gameType, ticTacToeModel.getGameTypeModel().getGameType());
            counter++;
        }
    }

    private void buildStage(Stage stage) {
        stage.setScene(new Scene(mainNode, 100, 100));
        stage.show();
        stage.toFront();
    }
}
