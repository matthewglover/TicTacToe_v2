package com.matthewglover.tictactoe.core;

public abstract class Player {
    protected final PlayerSymbol playerSymbol;

    public Player(PlayerSymbol playerSymbol) {
        this.playerSymbol = playerSymbol;
    }

    public abstract boolean isComputer();

    public abstract int getMove(Game game);
}
