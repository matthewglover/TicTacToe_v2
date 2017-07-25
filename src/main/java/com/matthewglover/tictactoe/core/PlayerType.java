package com.matthewglover.tictactoe.core;

public enum PlayerType {
    COMPUTER, HUMAN;

    public boolean isHuman() {
        return this == PlayerType.HUMAN;
    }
}
