package com.matthewglover.tictactoe.gui;

import com.matthewglover.tictactoe.core.GameType;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import java.util.concurrent.FutureTask;
import java.util.function.Supplier;

import static org.junit.Assert.assertEquals;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.hasChildren;

public class SceneSelectorTest extends ApplicationTest {

    private final int GAME_STATUS_DELAY = 0;
    private final Model model = new Model();
    private SceneSelector sceneSelector;

    @Override
    public void start(Stage stage) throws Exception {
        sceneSelector = new SceneSelector(model, GAME_STATUS_DELAY);
        stage.setScene(sceneSelector.getScene());
        stage.show();
        stage.toFront();
    }

    @Test
    public void initialSceneIsGameOptions() {
        verifyGameOptionsScene();
    }

    @Test
    public void sceneIsBoardAfterGameTypeSelected() throws Exception {
        FutureTask<Void> query = new FutureTask<>(() -> {
            model.setGameType(GameType.HUMAN_HUMAN);
            return null;
        });

        Platform.runLater(query);
        query.get();
        verifyBoardScene();
    }

    @Test
    public void sceneIsGameStatusAfterGameOver() throws Exception {
        FutureTask<Void> query = runWinningGameForX();

        Platform.runLater(query);
        query.get();

        verifyGameStatusScene();
    }

    @Test
    public void sceneIsGameOptionsAfterClickNewGame() throws Exception {
        FutureTask<Void> query = runWinningGameForX();

        Platform.runLater(query);
        query.get();

        runDelayed(50, () -> {
            clickOn("#new_game");
            return sceneSelector.getScene().getRoot();
        });

        verifyGameOptionsScene();
    }

    private void verifyGameOptionsScene() {
        verifyThat(getRootNode(), hasChildren(GameType.values().length, ".button"));
        for (int i = 0; i < GameType.values().length; i++) {
            Button currentButton = from(getRootNode()).lookup(".button").nth(i).query();
            assertEquals(GameType.values()[i].getDescription(), currentButton.getText());
        }
    }

    private void verifyBoardScene() {
        verifyThat(getRootNode(), hasChildren(9, ".button"));
    }

    private void verifyGameStatusScene() {
        String text = runDelayed(50, () -> {
            Label label = from(getRootNode()).lookup("#game_result").query();
            return label.getText();
        });

        assertEquals("X wins!", text);
    }

    private Parent getRootNode() {
        return sceneSelector.getScene().getRoot();
    }

    private FutureTask<Void> runWinningGameForX() {
        return new FutureTask<>(() -> {
            model.setGameType(GameType.HUMAN_HUMAN);
            // x x x
            // o o 6
            // 7 8 9
            model.move(1);
            model.move(4);
            model.move(2);
            model.move(5);
            model.move(3);
            return null;
        });
    }

    private <T extends Object> T runDelayed(int milliSecondsDelay, Supplier<T> lambda) {
        FutureTask sleeper = new FutureTask<>(() -> {
            Thread.sleep(milliSecondsDelay);
            return null;
        });


        new Thread(sleeper).start();

        try {
            sleeper.get();
        } catch (Exception e) {
        }

        return lambda.get();
    }
}
