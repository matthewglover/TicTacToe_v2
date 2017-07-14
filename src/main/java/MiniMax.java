public class MiniMax {

    private final Game game;
    private final int depth;
    private final boolean isMaximisingPlayer;
    private final int DRAW_SCORE = 0;
    private final int WINNING_SCORE = 10;
    private int score;


    public MiniMax(Game game, int depth, boolean isMaximisingPlayer) {
        this.game = game;
        this.depth = depth;
        this.isMaximisingPlayer = isMaximisingPlayer;
    }

    public int execute() {
        if (game.isOver()) {
            score = calculateScore();
        }
        else {
            score = getBestScore();
        }
        return score;
    }

    public Game getGame() {
        return game;
    }

    public int getScore() {
        return score;
    }

    private int calculateScore() {
        return game.isWinner() ? getWinningScore() : DRAW_SCORE;
    }

    private int getWinningScore() {
        return getBaseScore() + getDepthOffset();
    }

    private int getBaseScore() {
        return isMaximisingPlayer ? WINNING_SCORE : -WINNING_SCORE;
    }

    private int getDepthOffset() {
        return isMaximisingPlayer ? -depth : depth;
    }

    private int getBestScore() {
        return game.getNextMoves()
                .stream()
                .map(this::getMoveScore)
                .reduce(getSeedScore(), this::getBetterScore);
    }

    private int getMoveScore(Game move) {
        MiniMax miniMax = new MiniMax(move, depth + 1, !isMaximisingPlayer);
        miniMax.execute();
        return miniMax.getScore();
    }

    private int getSeedScore() {
        return !isMaximisingPlayer ? -Integer.MAX_VALUE : Integer.MAX_VALUE;
    }

    private int getBetterScore(Integer bestScore, Integer moveScore) {
        int result = !isMaximisingPlayer ? Math.max(bestScore, moveScore) : Math.min(bestScore, moveScore);
        return result;
    }
}
