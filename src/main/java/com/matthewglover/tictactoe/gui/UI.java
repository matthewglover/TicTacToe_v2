package com.matthewglover.tictactoe.gui;

import javafx.scene.Parent;

import java.util.Observable;
import java.util.Observer;

public abstract class UI implements Observer {
    protected final TicTacToeModel ticTacToeModel;
    protected final Parent rootNode;

    public UI(TicTacToeModel ticTacToeModel, Parent rootNode) {
        this.ticTacToeModel = ticTacToeModel;
        this.rootNode = rootNode;
        ticTacToeModel.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        update((ModelUpdate) arg);
    }

    public Parent getNode() {
        return rootNode;
    }

    protected abstract void update(ModelUpdate modelUpdate);
}
