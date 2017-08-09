package com.matthewglover.tictactoe.core;

public enum PlayerType {
    COMPUTER, HUMAN, REPLAY;

    public boolean isHuman() {
        return this == PlayerType.HUMAN;
    }
}
