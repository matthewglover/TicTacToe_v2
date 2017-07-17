public class MiniMax {
    private static final int MINIMUM_ALPHA = Integer.MIN_VALUE;
    private static final int MAXIMUM_BETA = Integer.MAX_VALUE;
    private static final int DRAW_SCORE = 0;
    private static final int WINNING_SCORE = 10;

    private final Game game;
    private final int depth;
    private final boolean isMaximising;

    private int alpha;
    private int beta;
    private int selectedScore;
    private int selectedMove;

    public static MiniMax run(Game game) {
        MiniMax strategies = new MiniMax(game, 0, true, MINIMUM_ALPHA, MAXIMUM_BETA);
        strategies.execute();
        return strategies;
    }

    public MiniMax(Game game, int depth, boolean isMaximising, int alpha, int beta) {
        this.game = game;
        this.depth = depth;
        this.isMaximising = isMaximising;
        this.alpha = alpha;
        this.beta = beta;

        selectedScore = isMaximising ? MINIMUM_ALPHA : MAXIMUM_BETA;
    }

    public int run() {
        return selectedMove;
    }

    public int getScore() {
        return selectedScore;
    }

    public void execute() {
        for (Game gameMove : game.getNextMoves()) {
            int currentMoveScore = calculateMoveScore(gameMove);

            if (isBetterScore(currentMoveScore)) {
                selectedScore = currentMoveScore;
                selectedMove = gameMove.getLastMove();
                updateAlphaBeta(currentMoveScore);
            }

            if (beta <= alpha) {
                break;
            }
        }
    }

    private int calculateMoveScore(Game gameMove) {
        return gameMove.isOver() ? calculateFinalMoveScore(gameMove) : calculateInterimMoveScore(gameMove);
    }

    private int calculateFinalMoveScore(Game gameMove) {
        if (gameMove.isWinner()) {
            return getBaseScore() + getDepthOffset();
        } else {
            return DRAW_SCORE;
        }
    }

    private int getBaseScore() {
        return isMaximising ? WINNING_SCORE : -WINNING_SCORE;
    }

    private int getDepthOffset() {
        return isMaximising ? -depth : depth;
    }

    private int calculateInterimMoveScore(Game gameMove) {
        MiniMax miniMax = new MiniMax(gameMove, nextDepth(), isNextMaximising(), alpha, beta);
        miniMax.execute();
        return miniMax.getScore();
    }

    private int nextDepth() {
        return depth + 1;
    }

    private boolean isNextMaximising() {
        return !isMaximising;
    }

    private boolean isBetterScore(int currentScore) {
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
