package com.mattheglover.tictactoe.core;

public enum PlayerSymbol {
    NEITHER,
    X,
    O;

    public boolean isEmpty() {
        return this == PlayerSymbol.NEITHER;
    }
}
