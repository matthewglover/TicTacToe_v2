package com.matthewglover.tictactoe.core;

public enum ModelUpdate {
    SETUP_NEW_GAME,
    SET_GAME_TYPE,
    CREATE_GAME,
    LOCK_BOARD,
    GAME_MOVE,
    GAME_OVER,
    REPLAY_GAME;

    public boolean isBoardChange() {
        return isGameMovePrompt() || isGameOver() || isLockBoard();
    }

    public boolean isGameMove() {
        return this == GAME_MOVE || this == GAME_OVER;
    }

    public boolean isGameMovePrompt() {
        return this == GAME_MOVE || this == CREATE_GAME;
    }

    private boolean isGameOver() {
        return this == GAME_OVER;
    }

    private boolean isLockBoard() {
        return this == LOCK_BOARD;
    }
}
