package com.matthewglover.tictactoe.core;

public class StrategyRunner {
    private final Game game;

    public StrategyRunner(Game game) {
        this.game = game;
    }

    public int runAlphaBeta() {
        AlphaBeta alphaBeta = new AlphaBeta(game, 0, true);
        alphaBeta.execute();
        return alphaBeta.getMove();
    }
}
