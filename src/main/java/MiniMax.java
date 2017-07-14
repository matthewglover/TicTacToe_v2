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

    public Game getGame() {
        return game;
    }

    public int getScore() {
        return score;
    }

    public void execute() {
        if (game.isOver()) {
            calculateScore();
        } else {
            calculateBestScore();
        }
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
        boolean isNextMaximisingPlayer = !isMaximisingPlayer;

        score = game.getNextMoves()
                .stream()
                .map(move -> {
                    MiniMax miniMax = new MiniMax(move, depth + 1, !isMaximisingPlayer);
                    miniMax.execute();
                    return miniMax.getScore();
                })
                .reduce((bestScore, moveScore) ->
                        isNextMaximisingPlayer ? Math.max(bestScore, moveScore) : Math.min(bestScore, moveScore))
                .get();
    }
}
