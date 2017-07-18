public class AlphaBeta {
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

    public static AlphaBeta run(Game game) {
        AlphaBeta alphaBeta = new AlphaBeta(game, 0, true, MINIMUM_ALPHA, MAXIMUM_BETA);
        alphaBeta.execute();
        return alphaBeta;
    }

    public AlphaBeta(Game game, int depth, boolean isMaximising, int alpha, int beta) {
        this.game = game;
        this.depth = depth;
        this.isMaximising = isMaximising;
        this.alpha = alpha;
        this.beta = beta;

        selectedScore = isMaximising ? MINIMUM_ALPHA : MAXIMUM_BETA;
    }

    public int getMove() {
        return selectedMove;
    }

    public void execute() {
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

    private int calculateMoveScore(Game gameMove) {
        return gameMove.isOver()
                ? calculateFinalMoveScore(gameMove)
                : calculateInterimMoveScore(gameMove);
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
        AlphaBeta alphaBeta = new AlphaBeta(gameMove, nextDepth(), isNextMaximising(), alpha, beta);
        alphaBeta.execute();
        return alphaBeta.getScore();
    }

    private int nextDepth() {
        return depth + 1;
    }

    private boolean isNextMaximising() {
        return !isMaximising;
    }

    private int getScore() {
        return selectedScore;
    }

    private boolean isBetterScore(int currentScore) {
        return isMaximising
                ? currentScore > alpha
                : currentScore < beta;
    }

    private void updateSelected(int score, int move) {
        selectedScore = score;
        selectedMove = move;
    }

    private void updateAlphaBeta(int score) {
        if (isMaximising) {
            alpha = score;
        } else {
            beta = score;
        }
    }
}
