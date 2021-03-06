package com.matthewglover.tictactoe.core;

public class HumanPlayer extends Player {

    public HumanPlayer(PlayerSymbol playerSymbol) {
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
