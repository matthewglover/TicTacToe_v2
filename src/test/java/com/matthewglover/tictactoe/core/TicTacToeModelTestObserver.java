package com.matthewglover.tictactoe.core;

import java.util.ArrayList;
import java.util.List;

public class TicTacToeModelTestObserver extends ModelObserver {
    private final List<ModelUpdate> modelUpdates = new ArrayList<>();
    private List<Integer> collectedMoves;

    public TicTacToeModelTestObserver(TicTacToeModel ticTacToeModel) {
        super(ticTacToeModel);
        resetCollectedMoves();
    }

    @Override
    public void update(ModelUpdate modelUpdate) {
        modelUpdates.add(modelUpdate);
        if (modelUpdate.isGameMove()) {
            collectedMoves.add(ticTacToeModel.getGame().getCurrentMove());
        }
    }

    public ModelUpdate getLastUpdate() {
        return modelUpdates.get(modelUpdates.size() - 1);
    }

    public void resetCollectedMoves() {
        collectedMoves = new ArrayList<>();
    }

    public List<Integer> getCollectedMoves() {
        return collectedMoves;
    }
}
