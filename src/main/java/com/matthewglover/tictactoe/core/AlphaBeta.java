package com.matthewglover.tictactoe.core;

public class AlphaBeta extends MiniMax {
    private static final int MINIMUM_ALPHA = Integer.MIN_VALUE;
    private static final int MAXIMUM_BETA = Integer.MAX_VALUE;

    private int alpha;
    private int beta;


    public static AlphaBeta run(Game game, int maxSearchDepth) {
        AlphaBeta alphaBeta = new AlphaBeta(game, maxSearchDepth, 0, true, MINIMUM_ALPHA, MAXIMUM_BETA);
        alphaBeta.execute();
        return alphaBeta;
    }

    public AlphaBeta(Game game, int maxSearchDepth, int depth, boolean isMaximising, int alpha, int beta) {
        super(game, maxSearchDepth, depth, isMaximising);
        this.alpha = alpha;
        this.beta = beta;
    }

    @Override
    public void execute() {
        if (depth == maxSearchDepth) {
            int currentScore = 0;
            int currentMove = game.getNextMoves().get(0).getCurrentMove();
            updateSelected(currentScore, currentMove);
            updateAlphaBeta(currentScore);
            return;
        }
        for (Game gameMove : game.getNextMoves()) {
            int currentScore = calculateMoveScore(gameMove);
            int currentMove = gameMove.getCurrentMove();

            if (isBetterScore(currentScore)) {
                updateSelected(currentScore, currentMove);
                updateAlphaBeta(currentScore);
            }

            if (beta <= alpha) {
                break;
            }
        }
    }

    @Override
    protected int calculateInterimMoveScore(Game gameMove) {
        AlphaBeta alphaBeta = new AlphaBeta(gameMove, maxSearchDepth,nextDepth(), isNextMaximising(), alpha, beta);
        alphaBeta.execute();
        return alphaBeta.getScore();
    }

    @Override
    protected boolean isBetterScore(int currentScore) {
        return isMaximising
                ? currentScore > alpha
                : currentScore < beta;
    }

    private void updateAlphaBeta(int score) {
        if (isMaximising) {
            alpha = score;
        } else {
            beta = score;
        }
    }
}
