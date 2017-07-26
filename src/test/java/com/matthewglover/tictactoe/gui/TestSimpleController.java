package com.matthewglover.tictactoe.gui;

import com.matthewglover.tictactoe.core.Game;
import com.matthewglover.tictactoe.core.PlayerSymbol;
import javafx.application.Platform;
import javafx.scene.control.Button;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.hasChildren;
import org.testfx.framework.junit.ApplicationTest;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Observable;
import java.util.Observer;


public class TestSimpleController extends ApplicationTest {
    private TestObservable testObserver = new TestObservable();
    private GuiBoard guiBoard;
    private Parent mainNode;
    private Game game;

    @Override
    public void start(Stage stage) throws Exception {
        game = new Game(3);
        guiBoard = new GuiBoard();
        game.addObserver(guiBoard);
        game.start();
        guiBoard.addObserver(testObserver);
        mainNode = guiBoard.getNode();
        stage.setScene(new Scene(mainNode, 100, 100));
        stage.show();
        stage.toFront();
    }


    @Test
    public void gridHasNineButtons(){
        verifyThat(mainNode, hasChildren(9, ".button"));
    }

    @Test
    public void clickOnSquareNotifiesObserver() {
        clickOn("#square_1");
        assertEquals(1, testObserver.getSquareNumber());
    }

    @Test
    public void setSquareIsTaken() {
        Platform.runLater(() -> {
            guiBoard.setSquare(1, PlayerSymbol.O);
            assertTrue(getSquare(1).isDisable());
            assertEquals(PlayerSymbol.O.toString(), getSquare(1).getText());
        });
    }

    private Button getSquare(int squareNumber) {
        return from(mainNode).lookup("#square_" + squareNumber).query();
    }

    private class TestObservable implements Observer {

        private int squareNumber = -1;

        @Override
        public void update(Observable o, Object arg) {
            squareNumber = (int) arg;
        }

        public int getSquareNumber() {
            return squareNumber;
        }
    }
}
