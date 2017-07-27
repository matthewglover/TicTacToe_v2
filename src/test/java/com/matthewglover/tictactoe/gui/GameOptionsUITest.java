package com.matthewglover.tictactoe.gui;

import com.matthewglover.tictactoe.core.GameType;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;

import static org.junit.Assert.assertEquals;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.hasChildren;

public class GameOptionsUITest extends ApplicationTest {

    private Parent mainNode;
    private TestObserver testObserver;

    @Override
    public void start(Stage stage) throws Exception {
        GameOptionsUI gameOptionsUI = new GameOptionsUI();
        this.testObserver = new TestObserver();
        TestObserver testObserver = this.testObserver;
        gameOptionsUI.addObserver(testObserver);

        mainNode = gameOptionsUI.getNode();
        buildStage(stage);
    }

    @Test
    public void displaysAllGameTypes() {
        verifyThat(mainNode, hasChildren(GameType.values().length, ".button"));
        for (int i = 0; i < GameType.values().length; i++) {
            Button currentButton = from(mainNode).lookup(".button").nth(i).query();
            assertEquals(GameType.values()[i].getDescription(), currentButton.getText());
        }
    }

    @Test
    public void gameButtonClickNotifiesObserver() {
        for (int i = 0; i < GameType.values().length; i++) {
            Button currentButton = from(mainNode).lookup(".button").nth(i).query();
            clickOn(currentButton);
            assertEquals(GameType.values()[i], testObserver.getGameType());
        }
    }

    private void buildStage(Stage stage) {
        stage.setScene(new Scene(mainNode, 100, 100));
        stage.show();
        stage.toFront();
    }

    private class TestObserver implements Observer {
        private GameType gameType;

        @Override
        public void update(Observable o, Object arg) {
            GameType gameType = (GameType) arg;
            this.gameType = gameType;
        }

        public GameType getGameType() {
            return gameType;
        }
    }
}
