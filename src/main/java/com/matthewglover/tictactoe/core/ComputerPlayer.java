package com.matthewglover.tictactoe.core;


public class ComputerPlayer extends Player {

    public ComputerPlayer(PlayerSymbol playerSymbol) {
        super(playerSymbol);
    }

    @Override
    protected void makeMove(Game game) {
        game.move(AlphaBeta.run(game).getMove());
    }
}
