package com.matthewglover.tictactoe.core;

public abstract class ComputerPlayer extends Player {
    public ComputerPlayer(PlayerSymbol playerSymbol) {
        super(playerSymbol);
    }

    @Override
    public boolean isComputer() {
        return true;
    }

    @Override
    public PlayerType getType() {
        return PlayerType.COMPUTER;
    }

    public abstract int getMove(Game game);
}
