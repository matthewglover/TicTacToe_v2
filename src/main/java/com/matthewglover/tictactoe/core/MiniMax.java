package com.matthewglover.tictactoe.core;

public abstract class MiniMax {
    protected final int DRAW_SCORE = 0;
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

    public void execute() {
        if (isOutOfDepth()) {
            setOutOfDepthScore();
        } else {
            calculateBestScore();
        }
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

    protected void setOutOfDepthScore() {
        int currentScore = DRAW_SCORE;
        int currentMove = game.getNextMoves().get(0).getCurrentMove();
        updateSelected(currentScore, currentMove);
    }

    protected void updateSelected(int score, int move) {
        selectedScore = score;
        selectedMove = move;
    }

    protected abstract void calculateBestScore();

    protected abstract int calculateInterimMoveScore(Game gameMove);

    protected abstract boolean isBetterScore(int currentScore);

    private int calculateFinalMoveScore(Game gameMove) {
        if (gameMove.isWinner()) {
            return getBaseScore() + getDepthOffset();
        } else {
            return DRAW_SCORE;
        }
    }

    private boolean isOutOfDepth() {
        return depth >= maxSearchDepth;
    }

    private int getBaseScore() {
        return isMaximising ? winningScore : -winningScore;
    }

    private int getDepthOffset() {
        return isMaximising ? -depth : depth;
    }
}
