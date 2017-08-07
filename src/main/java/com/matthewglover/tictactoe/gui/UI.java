package com.matthewglover.tictactoe.gui;

import com.matthewglover.tictactoe.core.ModelObserver;
import com.matthewglover.tictactoe.core.ModelUpdate;
import com.matthewglover.tictactoe.core.TicTacToeModel;
import javafx.scene.Parent;

public abstract class UI extends ModelObserver {
    private Parent rootNode;

    public UI(TicTacToeModel ticTacToeModel) {
        super(ticTacToeModel);
    }

    @Override
    protected abstract void update(ModelUpdate modelUpdate);

    protected void setRootNode(Parent rootNode) {
        this.rootNode = rootNode;
    }

    public Parent getNode() {
        return rootNode;
    }
}
