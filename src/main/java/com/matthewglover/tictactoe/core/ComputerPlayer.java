package com.matthewglover.tictactoe.core;


public class ComputerPlayer extends Player {

    public ComputerPlayer(PlayerSymbol playerSymbol) {
        super(playerSymbol);
    }

    @Override
    public boolean isComputer() {
        return true;
    }

    public int getMove(Game game) {
        StrategyRunner strategyRunner = new StrategyRunner(game);
        return strategyRunner.runAlphaBeta();
    }
}
