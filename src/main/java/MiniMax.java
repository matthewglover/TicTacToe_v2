public class MiniMax {
    private static final int DRAW_SCORE = 0;
    private static final int WINNING_SCORE = 10;

    private final Game game;
    private final int depth;
    private final boolean isMaximising;

    private int selectedScore;
    private int selectedMove;

    public static MiniMax run(Game game) {
        MiniMax miniMax = new MiniMax(game, 0, true);
        miniMax.execute();
        return miniMax;
    }

    public MiniMax(Game game, int depth, boolean isMaximising) {
        this.game = game;
        this.depth = depth;
        this.isMaximising = isMaximising;

        selectedScore = isMaximising ? Integer.MIN_VALUE : Integer.MAX_VALUE;
    }

    public int getMove() {
        return selectedMove;
    }

    public int getScore() {
        return selectedScore;
    }

    public void execute() {
        for (Game gameMove : game.getNextMoves()) {
            int currentMoveScore = calculateMoveScore(gameMove);

            if (isBetterScore(currentMoveScore)) {
                selectedScore = currentMoveScore;
                selectedMove = gameMove.getCurrentMove();
            }
        }
    }

    private int calculateMoveScore(Game gameMove) {
        return gameMove.isOver() ? calculateFinalMoveScore(gameMove) : calculateInterimMoveScore(gameMove);
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
        MiniMax miniMax = new MiniMax(gameMove, nextDepth(), isNextMaximising());
        miniMax.execute();
        return miniMax.getScore();
    }

    private int nextDepth() {
        return depth + 1;
    }

    private boolean isNextMaximising() {
        return !isMaximising;
    }

    private boolean isBetterScore(int currentScore) {
        return isMaximising
                ? currentScore > selectedScore
                : currentScore < selectedScore;
    }
}
