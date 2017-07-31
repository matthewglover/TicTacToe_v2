package com.matthewglover.tictactoe.gui;

public enum ModelUpdate {
    MOVE, SET_GAME_TYPE, CREATE_GAME, GAME_OVER, START_NEW_GAME;

    public boolean isSetGameType() {
        return this == SET_GAME_TYPE;
    }
}
