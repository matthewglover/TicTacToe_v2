package com.matthewglover.tictactoe.core;

public abstract class MiniMax {
    private static final int DRAW_SCORE = 0;
    private final int winningScore;
    protected final Game game;
    protected final int maxSearchDepth;
    protected final int depth;
    protected final boolean isMaximising;
    protected int selectedScore;
    private int selectedMove;

    public MiniMax(Game game, int maxSearchDepth, int depth, boolean isMaximising) {
        this.game = game;
        this.maxSearchDepth = maxSearchDepth;
        this.depth = depth;
        this.isMaximising = isMaximising;

        selectedScore = isMaximising ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        winningScore = game.getBoard().getTotalSquares();
    }

    public int getMove() {
        return selectedMove;
    }

    protected int calculateMoveScore(Game gameMove) {
        return gameMove.isOver()
                ? calculateFinalMoveScore(gameMove)
                : calculateInterimMoveScore(gameMove);
    }

    protected int nextDepth() {
        return depth + 1;
    }

    protected boolean isNextMaximising() {
        return !isMaximising;
    }

    protected int getScore() {
        return selectedScore;
    }

    protected void updateSelected(int score, int move) {
        selectedScore = score;
        selectedMove = move;
    }

    private int calculateFinalMoveScore(Game gameMove) {
        if (gameMove.isWinner()) {
            return getBaseScore() + getDepthOffset();
        } else {
            return DRAW_SCORE;
        }
    }

    private int getBaseScore() {
        return isMaximising ? winningScore : -winningScore;
    }

    private int getDepthOffset() {
        return isMaximising ? -depth : depth;
    }

    public abstract void execute();

    protected abstract int calculateInterimMoveScore(Game gameMove);

    protected abstract boolean isBetterScore(int currentScore);
}
