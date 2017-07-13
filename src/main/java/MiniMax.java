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

    public static MiniMax getMaxScore(MiniMax a, MiniMax b) {
        return a.getScore() > b.getScore() ? a : b;
    }

    public int execute() {
        score = game.isOver() ? getScore() : getBestScore();
        return score;
    }

    public Game getGame() {
        return game;
    }

    private int getScore() {
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
        return new MiniMax(move, depth + 1, !isMaximisingPlayer).execute();
    }

    private int getSeedScore() {
        return isMaximisingPlayer ? -Integer.MAX_VALUE : Integer.MAX_VALUE;
    }

    private int getBetterScore(Integer bestScore, Integer moveScore) {
        return isMaximisingPlayer ? Math.max(bestScore, moveScore) : Math.min(bestScore, moveScore);
    }
}
