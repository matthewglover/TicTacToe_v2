public abstract class MiniMax {
    private static final int DRAW_SCORE = 0;
    private static final int WINNING_SCORE = 10;
    protected final Game game;
    protected final int depth;
    protected final boolean isMaximising;
    protected int selectedScore;
    private int selectedMove;

    public MiniMax(Game game, int depth, boolean isMaximising) {
        this.isMaximising = isMaximising;
        this.game = game;
        this.depth = depth;
    }

    public int getMove() {
        return selectedMove;
    }

    public abstract void execute();

    int calculateMoveScore(Game gameMove) {
        return gameMove.isOver()
                ? calculateFinalMoveScore(gameMove)
                : calculateInterimMoveScore(gameMove);
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

    protected abstract int calculateInterimMoveScore(Game gameMove);

    protected int nextDepth() {
        return depth + 1;
    }

    protected boolean isNextMaximising() {
        return !isMaximising;
    }

    protected int getScore() {
        return selectedScore;
    }

    void updateSelected(int score, int move) {
        selectedScore = score;
        selectedMove = move;
    }

    protected abstract boolean isBetterScore(int currentScore);
}
