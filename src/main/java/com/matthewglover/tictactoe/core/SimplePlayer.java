package com.matthewglover.tictactoe.core;

public abstract class SimplePlayer {
    protected final PlayerSymbol playerSymbol;

    public SimplePlayer(PlayerSymbol playerSymbol) {
        this.playerSymbol = playerSymbol;
    }

    public abstract boolean isComputer();

    public abstract int getMove(Game game);
}
