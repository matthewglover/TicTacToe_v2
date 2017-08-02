package com.matthewglover.tictactoe.gui;

import com.matthewglover.tictactoe.core.GameType;
import com.matthewglover.tictactoe.core.PlayerSymbol;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.hasChildren;
import org.testfx.framework.junit.ApplicationTest;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;

public class BoardUITest extends ApplicationTest {
    private Parent mainNode;
    private final TicTacToeModel ticTacToeModel = new TicTacToeModel();

    @Override
    public void start(Stage stage) throws Exception {
        BoardUI boardUI = new BoardUI(ticTacToeModel);
        mainNode = boardUI.getNode();
        ticTacToeModel.setCurrentGameType(GameType.HUMAN_COMPUTER);
        ticTacToeModel.createGame(3);
        buildStage(stage);
    }

    @Test
    public void gridHasNineButtons(){
        verifyThat(mainNode, hasChildren(9, ".button"));
    }

    @Test
    public void clickOnSquareUpdatesBoard() {
        clickOn("#square_1");
        assertTrue(getSquare(1).isDisable());
        assertEquals(PlayerSymbol.X.toString(), getSquare(1).getText());
    }

    @Test
    public void computerMovesUpdateAutomatically() {
        clickOn("#square_1");
        assertEquals(PlayerSymbol.X, ticTacToeModel.getCurrentGame().getNextPlayerSymbol());
    }

    private void buildStage(Stage stage) {
        stage.setScene(new Scene(mainNode, 100, 100));
        stage.show();
        stage.toFront();
    }

    private Button getSquare(int squareNumber) {
        return from(mainNode).lookup("#square_" + squareNumber).query();
    }
}
