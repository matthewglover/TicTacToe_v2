public class MiniMax {

    private Game game;
    private int depth;
    private boolean isMaximisingPlayer;
    private final int DRAW_SCORE = 0;
    private final int WINNING_SCORE = 10;


    public MiniMax(Game game, int depth, boolean isMaximisingPlayer) {
        this.game = game;
        this.depth = depth;
        this.isMaximisingPlayer = isMaximisingPlayer;
    }

    public int execute() {
        return game.isOver() ? getScore() : getBestScore();
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
        if (isMaximisingPlayer) {
            return Math.max(bestScore, moveScore);
        } else {
            return Math.min(bestScore, moveScore);
        }
    }
}
