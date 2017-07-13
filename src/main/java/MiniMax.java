public class MiniMax {

    private Game game;
    private int depth;
    private boolean isMaximisingPlayer;


    public MiniMax(Game game, int depth, boolean isMaximisingPlayer) {
        this.game = game;
        this.depth = depth;
        this.isMaximisingPlayer = isMaximisingPlayer;
    }

    public int execute() {
        return game.isOver() ? getScore() : getBestScore();
    }

    private int getScore() {
        if (!game.isWinner()) {
            return 0;
        }
        return getBaseScore() + getDepthOffset();
    }

    private int getBaseScore() {
        return isMaximisingPlayer ? 10 : -10;
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
        return miniMax.execute();
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
