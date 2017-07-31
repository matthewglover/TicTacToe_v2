package com.matthewglover.tictactoe.gui;

import com.matthewglover.tictactoe.core.GameType;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;

import java.util.concurrent.FutureTask;
import java.util.function.Supplier;

import static org.junit.Assert.assertEquals;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.hasChildren;

public class SceneSelectorTest extends ApplicationTest {

    private final Model model = new Model();
    private SceneSelector sceneSelector;

    @Override
    public void start(Stage stage) throws Exception {
        sceneSelector = new SceneSelector(model, 0);
        stage.setScene(sceneSelector.getScene());
        stage.show();
        stage.toFront();
    }

    @Test
    public void initialSceneIsGameOptions() {
        Parent rootNode = sceneSelector.getScene().getRoot();
        verifyThat(rootNode, hasChildren(GameType.values().length, ".button"));
        for (int i = 0; i < GameType.values().length; i++) {
            Button currentButton = from(rootNode).lookup(".button").nth(i).query();
            assertEquals(GameType.values()[i].getDescription(), currentButton.getText());
        }
    }

    @Test
    public void sceneIsBoardAfterGameTypeSelected() {
        model.setGameType(GameType.HUMAN_HUMAN);
        Parent rootNode = sceneSelector.getScene().getRoot();
        verifyThat(rootNode, hasChildren(9, ".button"));
    }

    @Test
    public void sceneIsGameStatusAfterGameOver() throws Exception {
        FutureTask<Void> query = new FutureTask<>(() -> {
            model.setGameType(GameType.HUMAN_HUMAN);
            model.move(1);
            model.move(4);
            model.move(2);
            model.move(5);
            model.move(3);
            return null;
        });

        Platform.runLater(query);
        query.get();

        String text = runDelayed(50, () -> {
            Parent rootNode = sceneSelector.getScene().getRoot();
            Label label = from(rootNode).lookup("#game_result").query();
            return label.getText();
        });

        assertEquals("X wins!", text);
    }

    @Test
    public void sceneIsGameOptionsAfterClickNewGame() throws Exception {
        FutureTask<Void> query = new FutureTask<>(() -> {
            model.setGameType(GameType.HUMAN_HUMAN);
            model.move(1);
            model.move(4);
            model.move(2);
            model.move(5);
            model.move(3);
            return null;
        });

        Platform.runLater(query);
        query.get();

        Parent rootNode = runDelayed(50, () -> {
            clickOn("#new_game");
            return sceneSelector.getScene().getRoot();
        });

        verifyThat(rootNode, hasChildren(GameType.values().length, ".button"));
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
