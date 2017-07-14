public class UnbeatableComputer {
    private final Game game;
    private Game selectedGameState;
    private int currentBestScore = Integer.MIN_VALUE;

    public UnbeatableComputer(Game game) {
        this.game = game;
    }

    public int getMove() {
        return selectedGameState.getLastMove();
    }

    public void execute() {
        for (Game gameMove : game.getNextMoves()) {
            tryGameStrategy(gameMove);
        }
    }

    private void tryGameStrategy(Game gameMove) {
        MiniMax miniMax = new MiniMax(gameMove, 0, true);
        miniMax.execute();
        if (miniMax.getScore() > currentBestScore) {
            currentBestScore = miniMax.getScore();
            selectedGameState = miniMax.getGame();
        }
    }
}
