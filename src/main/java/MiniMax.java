import java.util.List;

public class MiniMax {

    private Game game;
    private int depth;
    private boolean isMaximisingPlayer;


    public MiniMax(Game game, int depth, boolean isMaximisingPlayer) {
        this.game = game;
        this.depth = depth;
        this.isMaximisingPlayer = isMaximisingPlayer;
    }

    public int run() {
        if (game.isOver()) {
            return getScore();
        }

        List<Game> allMoves = game.getNextMoves();

        int bestScore = getInitialBestScore();

        for (Game move : allMoves) {
            MiniMax miniMax = new MiniMax(move, depth + 1, !isMaximisingPlayer);
            int moveScore = miniMax.run();
            bestScore = getBetterScore(bestScore, moveScore);
        }

        return bestScore;
    }

    private int getInitialBestScore() {
        return isMaximisingPlayer ? -1000 : 1000;
    }

    private int getScore() {
        int baseScore = isMaximisingPlayer ? 10 : -10;

        if (!game.isWinner()) {
            return 0;
        }


        if (isMaximisingPlayer) {
            return 10 - depth;
        }

        return baseScore + depth;
    }

    private int getBetterScore(int bestScore, int moveScore) {
        if (isMaximisingPlayer) {
            return Math.max(bestScore, moveScore);
        } else {
            return Math.min(bestScore, moveScore);
        }
    }
}
