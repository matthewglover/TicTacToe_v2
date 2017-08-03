package com.matthewglover.tictactoe.core;


public class ComputerPlayer extends Player {
    private static final int MAX_SEARCH_DEPTH = 6;

    public ComputerPlayer(PlayerSymbol playerSymbol) {
        super(playerSymbol);
    }

    @Override
    public boolean isComputer() {
        return true;
    }

    @Override
    public int getMove(Game game) {
        return AlphaBeta.run(game, MAX_SEARCH_DEPTH).getMove();
    }

}
