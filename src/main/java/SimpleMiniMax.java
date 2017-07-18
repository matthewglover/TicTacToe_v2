public class SimpleMiniMax extends MiniMax {

    public static SimpleMiniMax run(Game game) {
        SimpleMiniMax miniMax = new SimpleMiniMax(game, 0, true);
        miniMax.execute();
        return miniMax;
    }

    public SimpleMiniMax(Game game, int depth, boolean isMaximising) {
        super(game, depth, isMaximising);

        selectedScore = isMaximising ? Integer.MIN_VALUE : Integer.MAX_VALUE;
    }

    @Override
    public void execute() {
        for (Game gameMove : game.getNextMoves()) {
            int currentScore = calculateMoveScore(gameMove);
            int currentMove = gameMove.getCurrentMove();

            if (isBetterScore(currentScore)) {
                updateSelected(currentScore, currentMove);
            }
        }
    }

    @Override
    protected int calculateInterimMoveScore(Game gameMove) {
        SimpleMiniMax miniMax = new SimpleMiniMax(gameMove, nextDepth(), isNextMaximising());
        miniMax.execute();
        return miniMax.getScore();
    }

    @Override
    protected boolean isBetterScore(int currentScore) {
        return isMaximising
                ? currentScore > selectedScore
                : currentScore < selectedScore;
    }
}
