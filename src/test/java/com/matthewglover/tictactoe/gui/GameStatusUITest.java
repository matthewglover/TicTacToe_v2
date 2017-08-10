package com.matthewglover.tictactoe.gui;

import com.matthewglover.tictactoe.core.GameTestHelper;
import com.matthewglover.tictactoe.core.GameType;
import com.matthewglover.tictactoe.core.ModelUpdate;
import com.matthewglover.tictactoe.core.TicTacToeModelTestObserver;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import java.util.concurrent.FutureTask;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GameStatusUITest extends ApplicationTest {
    private TicTacToeModel ticTacToeModel = new TicTacToeModel();
    private Parent mainNode;


    @Override
    public void start(Stage stage) throws Exception {
        GameStatusUI gameStatusUI = new GameStatusUI(ticTacToeModel);
        mainNode = gameStatusUI.getNode();
        buildStage(stage);
        ticTacToeModel.setupNewGame();
        ticTacToeModel.setGameType(GameType.HUMAN_HUMAN);
        ticTacToeModel.createGame(3);
    }

    @Test
    public void displaysWinningMessageForX() throws Exception {
        FutureTask<String> query = new FutureTask<>(() -> {
            // x x x
            // o o 6
            // 7 8 9
            GameMoveHelper.runMoves(ticTacToeModel, new int[]{1, 4, 2, 5, 3});
            return getTextNode().getText();
        });

        Platform.runLater(query);
        assertEquals("X wins!", query.get());
    }

    private Text getTextNode() {
        return from(mainNode).lookup("#game_result").query();
    }

    @Test
    public void displaysWinningMessageForO() throws Exception {
        FutureTask<String> query = new FutureTask<>(() -> {
            // o o o
            // x x 6
            // 7 8 x
            GameMoveHelper.runMoves(ticTacToeModel, new int[]{4, 1, 5, 2, 9, 3});
            return getTextNode().getText();
        });

        Platform.runLater(query);
        assertEquals("O wins!", query.get());
    }

    @Test
    public void displaysDrawingMessage() throws Exception {
        FutureTask<String> query = new FutureTask<>(() -> {
            // o o x
            // x x o
            // o x x
            GameMoveHelper.runMoves(ticTacToeModel, new int[]{4, 1, 5, 2, 9, 6, 3, 7, 8});
            return getTextNode().getText();
        });

        Platform.runLater(query);
        assertEquals("It's a draw!", query.get());
    }

    @Test
    public void clickOnNewGameFiresEvent() {
        TicTacToeModelTestObserver testObserver = new TicTacToeModelTestObserver(ticTacToeModel);
        ticTacToeModel.addObserver(testObserver);
        clickOn("#new_game");
        assertEquals(ModelUpdate.SETUP_NEW_GAME, testObserver.getLastUpdate());
    }

    @Test
    public void clickOnReplayGameFiresEvent() {
        ticTacToeModel.setupNewGame();
        ticTacToeModel.setGameType(GameType.HUMAN_HUMAN);
        ticTacToeModel.createGame(3);
        GameTestHelper.runGame(ticTacToeModel, new int[]{1, 4, 2, 5, 3});
        TicTacToeModelTestObserver testObserver = new TicTacToeModelTestObserver(ticTacToeModel);
        clickOn("#replay_game");
        assertTrue(testObserver.getUpdates().contains(ModelUpdate.REPLAY_GAME));
    }

    private void buildStage(Stage stage) {
        stage.setScene(new Scene(mainNode, 100, 100));
        stage.show();
        stage.toFront();
    }
}
