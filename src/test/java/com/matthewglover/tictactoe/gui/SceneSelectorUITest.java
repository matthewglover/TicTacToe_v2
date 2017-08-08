package com.matthewglover.tictactoe.gui;

import com.matthewglover.tictactoe.core.GameType;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import java.util.concurrent.FutureTask;
import java.util.function.Supplier;

import static org.junit.Assert.assertEquals;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.hasChildren;

public class SceneSelectorUITest extends ApplicationTest {

    private final TicTacToeModel ticTacToeModel = new TicTacToeModel();
    private SceneSelectorUI sceneSelectorUI;

    @Override
    public void start(Stage stage) throws Exception {
        int gameStatusDelay = 0;
        sceneSelectorUI = new SceneSelectorUI(ticTacToeModel, gameStatusDelay);
        stage.setScene(sceneSelectorUI.getScene());
        stage.show();
        stage.toFront();
    }

    @Test
    public void initialSceneIsGameOptions() {
        verifyGameOptionsScene();
    }

    @Test
    public void sceneIsBoardSizeAfterGameOptionsSelected() throws Exception {
        FutureTask<Void> query = new FutureTask<>(() -> {
            ticTacToeModel.startNewGame();
            ticTacToeModel.setCurrentGameType(GameType.HUMAN_HUMAN);
            return null;
        });

        Platform.runLater(query);
        query.get();
        verifyBoardSizeScene();
    }

    @Test
    public void sceneIsBoardAfterGameTypeSelected() throws Exception {
        FutureTask<Void> query = new FutureTask<>(() -> {
            ticTacToeModel.startNewGame();
            ticTacToeModel.setCurrentGameType(GameType.HUMAN_HUMAN);
            ticTacToeModel.setCurrentBoardSize(3);
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

        runDelayed(500, () -> {
            clickOn("#new_game");
            return null;
        });

        verifyGameOptionsScene();
    }

    private void verifyBoardSizeScene() {
        Parent mainNode = sceneSelectorUI.getScene().getRoot();
        verifyThat(mainNode, hasChildren(2, ".button"));
        for (int i = 3; i < 4; i++) {
            Button currentButton = from(mainNode).lookup(".button").nth(i - 3).query();
            assertEquals(i + " X " + i, currentButton.getText());
        }
    }

    private void verifyGameOptionsScene() {
        verifyThat(getRootNode(), hasChildren(GameType.values().length, ".button"));
        for (int i = 0; i < GameType.values().length; i++) {
            Button currentButton = from(getRootNode()).lookup(".button").nth(i).query();
            assertEquals(GameType.values()[i].getDescription(), currentButton.getText());
        }
    }

    private void verifyBoardScene() {
        Pane boardRoot = (Pane) getRootNode().getChildrenUnmodifiable().get(0);
        assertEquals(9, boardRoot.getChildren().size());
    }

    private void verifyGameStatusScene() {
        String text = runDelayed(50, () -> {
            Text textNode = from(getRootNode()).lookup("#game_result").query();
            return textNode.getText();
        });

        assertEquals("X wins!", text);
    }

    private Parent getRootNode() {
        return sceneSelectorUI.getScene().getRoot();
    }

    private FutureTask<Void> runWinningGameForX() {
        return new FutureTask<>(() -> {
            ticTacToeModel.startNewGame();
            ticTacToeModel.setCurrentGameType(GameType.HUMAN_HUMAN);
            ticTacToeModel.setCurrentBoardSize(3);
            // x x x
            // o o 6
            // 7 8 9
            GameMoveHelper.runMoves(ticTacToeModel, new int[]{1, 4, 2, 5, 3});
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
