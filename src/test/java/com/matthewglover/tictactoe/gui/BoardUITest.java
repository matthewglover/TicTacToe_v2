package com.matthewglover.tictactoe.gui;

import com.matthewglover.tictactoe.core.GameType;
import com.matthewglover.tictactoe.core.PlayerSymbol;

import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.testfx.framework.junit.ApplicationTest;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class BoardUITest extends ApplicationTest {
    private Parent mainNode;
    private Pane boardNode;
    private final TicTacToeModel ticTacToeModel = new TicTacToeModel();

    @Override
    public void start(Stage stage) throws Exception {
        BoardUI boardUI = new BoardUI(ticTacToeModel);
        mainNode = boardUI.getNode();
        boardNode = (Pane) mainNode.getChildrenUnmodifiable().get(0);
        ticTacToeModel.setupNewGame();
        ticTacToeModel.setGameType(GameType.HUMAN_COMPUTER);
        ticTacToeModel.createGame(3);
        buildStage(stage);
    }

    @Test
    public void gridHasNineButtons(){
        assertEquals(9, boardNode.getChildrenUnmodifiable().size());
    }

    @Test
    public void clickOnSquareUpdatesBoard() {
        clickOn("#square_1");
        assertEquals(PlayerSymbol.X.toString(), BoardUIHelper.getSquareTextNode(getSquare(1)).getText());
    }

    @Test
    public void computerMovesUpdateAutomatically() {
        clickOn("#square_1");
        assertEquals(PlayerSymbol.X, ticTacToeModel.getCurrentGame().getNextPlayerSymbol());
    }

    private void buildStage(Stage stage) {
        stage.setScene(new Scene(mainNode, 500, 500));
        stage.show();
        stage.toFront();
    }

    private StackPane getSquare(int squareNumber) {
        return from(boardNode).lookup("#square_" + squareNumber).query();
    }
}
