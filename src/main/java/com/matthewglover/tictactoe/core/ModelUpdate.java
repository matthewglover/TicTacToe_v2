package com.matthewglover.tictactoe.core;

public enum ModelUpdate {
    SETUP_NEW_GAME,
    SET_GAME_TYPE,
    CREATE_GAME,
    LOCK_BOARD,
    MAKE_MOVE,
    GAME_OVER;

    public boolean isBoardChange() {
        return isGameMove() || isGameOver() || isLockBoard();
    }

    public boolean isGameMove() {
        return this == ModelUpdate.MAKE_MOVE ||
                this == ModelUpdate.CREATE_GAME;
    }

    private boolean isGameOver() {
        return this == GAME_OVER;
    }

    private boolean isLockBoard() {
        return this == LOCK_BOARD;
    }
}
