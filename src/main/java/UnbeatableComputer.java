public class UnbeatableComputer {
    private final Game game;
    private Game selectedGameState;

    public UnbeatableComputer(Game game) {
        this.game = game;
    }

    public void execute() {
        selectedGameState = game.getNextMoves()
                .stream()
                .map(UnbeatableComputer::runMove)
                .reduce(MiniMax::getMaxScore)
                .get()
                .getGame();
    }

    public int getMove() {
        return selectedGameState.getLastMove();
    }

    private static MiniMax runMove(Game game) {
        MiniMax miniMax = new MiniMax(game, 0, true);
        miniMax.execute();
        return miniMax;
    }
}
