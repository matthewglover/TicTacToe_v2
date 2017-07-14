public class UnbeatableComputer {
    private final Game game;
    private Game selectedGameState;
    private int currentBestScore = Integer.MIN_VALUE;

    public UnbeatableComputer(Game game) {
        this.game = game;
    }

    public void execute() {
        for (Game gameMove : game.getNextMoves()) {
            tryGameStrategy(gameMove);
        }
    }

    private void tryGameStrategy(Game gameMove) {
        MiniMax mm = new MiniMax(gameMove, 0, true);
        mm.execute();
        if (mm.getScore() > currentBestScore) {
            currentBestScore = mm.getScore();
            selectedGameState = mm.getGame();
        }
    }

    public int getMove() {
        return selectedGameState.getLastMove();
    }
}
