package com.matthewglover.tictactoe.gui;

import javafx.application.Platform;

public class TicTacToeModel extends com.matthewglover.tictactoe.core.TicTacToeModel {
    protected Runnable getRunComputerMove() {
        return () -> Platform.runLater(() -> gameMove(getNextPlayer().getMove(getCurrentGameModel().getGame())));
    }
}
