package com.matthewglover.tictactoe.core;

import java.util.Observable;
import java.util.Observer;

public abstract class ModelObserver implements Observer {
    protected final TicTacToeModel ticTacToeModel;

    public ModelObserver(TicTacToeModel ticTacToeModel) {
        this.ticTacToeModel = ticTacToeModel;
        ticTacToeModel.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        update((ModelUpdate) arg);
    }

    protected abstract void update(ModelUpdate modelUpdate);
}
