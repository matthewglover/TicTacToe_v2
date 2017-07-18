public class AlphaBeta extends MiniMax {
    private static final int MINIMUM_ALPHA = Integer.MIN_VALUE;
    private static final int MAXIMUM_BETA = Integer.MAX_VALUE;

    private int alpha;
    private int beta;

    public static AlphaBeta run(Game game) {
        AlphaBeta alphaBeta = new AlphaBeta(game, 0, true, MINIMUM_ALPHA, MAXIMUM_BETA);
        alphaBeta.execute();
        return alphaBeta;
    }

    public AlphaBeta(Game game, int depth, boolean isMaximising, int alpha, int beta) {
        super(game, depth, isMaximising);
        this.alpha = alpha;
        this.beta = beta;

        selectedScore = isMaximising ? MINIMUM_ALPHA : MAXIMUM_BETA;
    }

    @Override
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

    @Override
    protected int calculateInterimMoveScore(Game gameMove) {
        AlphaBeta alphaBeta = new AlphaBeta(gameMove, nextDepth(), isNextMaximising(), alpha, beta);
        alphaBeta.execute();
        return alphaBeta.getScore();
    }

    private void updateAlphaBeta(int score) {
        if (isMaximising) {
            alpha = score;
        } else {
            beta = score;
        }
    }

    @Override
    protected boolean isBetterScore(int currentScore) {
        return isMaximising
                ? currentScore > alpha
                : currentScore < beta;
    }
}
