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
    private TicTacToeModel ticTacToeModel;

    @Override
    public void start(Stage stage) throws Exception {
        ticTacToeModel = new TicTacToeModel();
        BoardUI boardUI = new BoardUI(ticTacToeModel);
        mainNode = boardUI.getNode();
        ticTacToeModel.setCurrentGameType(GameType.HUMAN_HUMAN);
        ticTacToeModel.createGame(3);
        ticTacToeModel.gameMove(1);
        ticTacToeModel.gameMove(4);
        ticTacToeModel.gameMove(2);
        ticTacToeModel.gameMove(5);
        ticTacToeModel.gameMove(3);
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
