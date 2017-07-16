import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Stream;

public class UnbeatableComputer {
    private final Game game;
    private Game selectedStrategy;

    public UnbeatableComputer(Game game) {
        this.game = game;
    }

    public int getMove() {
        return selectedStrategy.getLastMove();
    }

    public void execute() {
        selectedStrategy = getStrategies()
            .reduce(selectBestStrategy())
            .get()
            .getGame();
    }

    private Stream<MiniMax> getStrategies() {
        return game.getNextMoves()
            .stream()
            .map(getStrategy());
    }

    private Function<Game, MiniMax> getStrategy() {
        return (Game gameMove) -> {
            MiniMax miniMax = new MiniMax(gameMove, 0, true);
            miniMax.execute();
            return miniMax;
        };
    }

    private BinaryOperator<MiniMax> selectBestStrategy() {
        return (MiniMax bestStrategy, MiniMax currentStrategy) ->
            currentStrategy.getScore() > bestStrategy.getScore()
                    ? currentStrategy
                    : bestStrategy;
    }
}
