package com.matthewglover.tictactoe.core;

public class SimpleHumanPlayer extends SimplePlayer {

    public SimpleHumanPlayer(PlayerSymbol playerSymbol) {
        super(playerSymbol);
    }

    @Override
    public boolean isComputer() {
        return false;
    }

    @Override
    public int getMove(Game game) {
        return -1;
    }
}
