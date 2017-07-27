package com.matthewglover.tictactoe.gui;

import com.matthewglover.tictactoe.core.Game;
import javafx.scene.Parent;

import java.util.Observable;
import java.util.Observer;

public class GuiGame implements Observer {

    private final GuiBoard guiBoard = new GuiBoard(this);

    @Override
    public void update(Observable o, Object arg) {
        Game game = (Game) o;
        guiBoard.buildGrid(game);
    }

    public Parent getNode() {
        return guiBoard.getNode();
    }

    public void makeMove(Game game, int squareNumber) {
        game.move(squareNumber);
    }
}
