public class MiniMaxStrategy {

    private static final int DRAW_SCORE = 0;
    private static final int WINNING_SCORE = 10;

    private final Game game;
    private final int depth;
    private final int nextDepth;
    private final boolean isMaximisingPlayer;
    private final boolean isNextMaximisingPlayer;
    private int alpha;
    private int beta;
    private int score;


    public MiniMaxStrategy(Game game, int depth, boolean isMaximisingPlayer, int alpha, int beta) {
        this.game = game;
        this.depth = depth;
        this.isMaximisingPlayer = isMaximisingPlayer;
        nextDepth = depth + 1;
        isNextMaximisingPlayer = !isMaximisingPlayer;
        this.alpha = alpha;
        this.beta = beta;
    }

    public void execute() {
        if (game.isOver()) {
            calculateScore();
        } else {
            calculateBestScore();
        }
    }

    public int getLastMove() {
        return game.getLastMove();
    }

    public int getScore() {
        return score;
    }

    private void calculateScore() {
        if (game.isWinner()) {
            score = getBaseScore() + getDepthOffset();
        } else {
            score = DRAW_SCORE;
        }
    }

    private int getBaseScore() {
        return isMaximisingPlayer ? WINNING_SCORE : -WINNING_SCORE;
    }

    private int getDepthOffset() {
        return isMaximisingPlayer ? -depth : depth;
    }

    private void calculateBestScore() {
        MiniMaxStrategies strategies = new MiniMaxStrategies(game, nextDepth, isNextMaximisingPlayer, alpha, beta);
        strategies.execute();
        score = strategies.getBestScore();
    }
}
