package com.matthewglover.tictactoe.gui;

import com.matthewglover.tictactoe.core.GameType;
import com.matthewglover.tictactoe.core.ImmediateRunner;
import com.matthewglover.tictactoe.core.PlayerSymbol;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;


import static org.junit.Assert.assertEquals;

public class BoardUIGameOverTest extends ApplicationTest {

    private Parent mainNode;

    @Override
    public void start(Stage stage) throws Exception {
        TicTacToeModel ticTacToeModel = new TicTacToeModel(new ImmediateRunner());
        BoardUI boardUI = new BoardUI(ticTacToeModel);
        mainNode = boardUI.getNode();
        ticTacToeModel.setupNewGame();
        ticTacToeModel.setGameType(GameType.HUMAN_HUMAN);
        ticTacToeModel.createGame(3);
        GameMoveHelper.runMoves(ticTacToeModel, new int[]{1, 4, 2, 5, 3});
        buildStage(stage);
    }

    @Test
    public void drawsFinalStateBoard() {
        assertEquals(PlayerSymbol.X.toString(), getSquare(1).getText());
    }

    private void buildStage(Stage stage) {
        stage.setScene(new Scene(mainNode, 100, 100));
        stage.show();
        stage.toFront();
    }

    private Text getSquare(int squareNumber) {
        StackPane tile = from(mainNode).lookup("#square_" + squareNumber).query();
        return BoardUIHelper.getSquareTextNode(tile);
    }

}
