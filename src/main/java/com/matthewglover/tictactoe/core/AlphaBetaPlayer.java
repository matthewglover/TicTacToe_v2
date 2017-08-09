package com.matthewglover.tictactoe.core;


public class AlphaBetaPlayer extends ComputerPlayer {

    public AlphaBetaPlayer(PlayerSymbol playerSymbol) {
        super(playerSymbol);
    }

    @Override
    public int getMove(Game game) {
        StrategyRunner strategyRunner = new StrategyRunner(game);
        return strategyRunner.runAlphaBeta();
    }
}
