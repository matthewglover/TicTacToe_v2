package com.matthewglover.tictactoe.core;

import java.util.ArrayList;
import java.util.List;

public class TicTacToeModelTestObserver extends ModelObserver {
    private final List<ModelUpdate> modelUpdates = new ArrayList<>();

    public TicTacToeModelTestObserver(TicTacToeModel ticTacToeModel) {
        super(ticTacToeModel);
    }

    @Override
    public void update(ModelUpdate modelUpdate) {
        modelUpdates.add(modelUpdate);
    }

    public ModelUpdate getLastUpdate() {
        return modelUpdates.get(modelUpdates.size() - 1);
    }
}
