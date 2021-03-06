package com.matthewglover.tictactoe.core;

public enum ModelUpdate {
    SETUP_NEW_GAME,
    SET_GAME_TYPE,
    CREATE_GAME,
    MAKE_MOVE,
    GAME_OVER;

    public boolean isGameMove() {
        return this == ModelUpdate.MAKE_MOVE ||
                this == ModelUpdate.CREATE_GAME;
    }
}
