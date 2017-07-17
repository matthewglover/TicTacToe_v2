public class MiniMaxStrategies {
    private final Game game;
    private final int depth;
    private final boolean isNextMaximisingPlayer;
    private static final int MINIMUM_ALPHA = Integer.MIN_VALUE;
    private static final int MAXIMUM_BETA = Integer.MAX_VALUE;

    private int alpha;
    private int beta;
    private MiniMaxStrategy selectedStrategy;

    public static MiniMaxStrategies getBestStrategy(Game game) {
        MiniMaxStrategies strategies = new MiniMaxStrategies(game, 0, true, MINIMUM_ALPHA, MAXIMUM_BETA);
        strategies.execute();
        return strategies;
    }

    public MiniMaxStrategies(Game game, int depth, boolean isNextMaximisingPlayer, int alpha, int beta) {
        this.game = game;
        this.depth = depth;
        this.isNextMaximisingPlayer = isNextMaximisingPlayer;
        this.alpha = alpha;
        this.beta = beta;
    }

    public void execute() {
        for (Game gameMove : game.getNextMoves()) {
            MiniMaxStrategy currentStrategy = new MiniMaxStrategy(gameMove, depth, isNextMaximisingPlayer, alpha, beta);
            currentStrategy.execute();

            if (isBetterScore(currentStrategy.getScore())) {
                selectedStrategy = currentStrategy;
                updateAlphaBeta(currentStrategy.getScore());
            }

            if (beta <= alpha) {
                break;
            }
        }
    }

    public int getBestMove() {
        return selectedStrategy.getLastMove();
    }

    public int getBestScore() {
        if (isPrunedNode()) {
            return isNextMaximisingPlayer ? MINIMUM_ALPHA : MAXIMUM_BETA;
        } else {
            return selectedStrategy.getScore();
        }
    }

    private boolean isBetterScore(int currentScore) {
        return isNextMaximisingPlayer
                ? currentScore > alpha
                : currentScore < beta;
    }

    private void updateAlphaBeta(int score) {
        if (isNextMaximisingPlayer) {
            alpha = score;
        } else {
            beta = score;
        }
    }

    private boolean isPrunedNode() {
        return selectedStrategy == null;
    }
}
