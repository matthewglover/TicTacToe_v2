package com.matthewglover.tictactoe.gui;

import com.matthewglover.tictactoe.core.Runner;
import javafx.application.Platform;

public class TicTacToeModel extends com.matthewglover.tictactoe.core.TicTacToeModel {
    public TicTacToeModel(Runner runner) {
        super(runner);
    }

    protected Runnable getRunComputerMove() {
        return () -> Platform.runLater(super.getRunComputerMove());
    }
}
