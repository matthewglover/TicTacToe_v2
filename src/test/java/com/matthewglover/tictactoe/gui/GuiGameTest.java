package com.matthewglover.tictactoe.gui;

import com.matthewglover.tictactoe.core.Game;
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

public class GuiGameTest extends ApplicationTest {
    private GuiBoard guiBoard;
    private Parent mainNode;
    private Game game;

    @Override
    public void start(Stage stage) throws Exception {
        game = new Game(3);
        guiBoard = new GuiBoard();
        mainNode = guiBoard.getNode();

        buildGameUI();
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
    public void clickOnSquareUpdatesGameState() {
        clickOn("#square_1");
        clickOn("#square_2");
        assertTrue(getSquare(2).isDisable());
        assertEquals(PlayerSymbol.O.toString(), getSquare(2).getText());
    }

    private void buildGameUI() {
        game.addObserver(guiBoard);
        game.start();
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
