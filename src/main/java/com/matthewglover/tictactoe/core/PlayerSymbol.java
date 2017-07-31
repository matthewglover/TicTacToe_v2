package com.matthewglover.tictactoe.core;

public enum PlayerSymbol {
    NEITHER,
    X,
    O;

    public boolean isEmpty() {
        return this == PlayerSymbol.NEITHER;
    }

    public int getPlayerIndex() {
        switch (this) {
            case X: return 0;
            case O: return 1;
            default: return -1;
        }
    }
}
