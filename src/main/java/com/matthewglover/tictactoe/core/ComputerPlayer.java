package com.matthewglover.tictactoe.core;


public class ComputerPlayer extends Player {

    private static final int MAX_SEARCH_DEPTH = 6;

    public ComputerPlayer(PlayerSymbol playerSymbol) {
        super(playerSymbol);
    }

    @Override
    protected void makeMove(Game game) {
        game.move(AlphaBeta.run(game, MAX_SEARCH_DEPTH).getMove());
    }
}
