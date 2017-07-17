import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Stream;

public class MiniMaxStrategies {
    private final Game game;
    private final int depth;
    private final boolean isNextMaximisingPlayer;
    private int alpha;
    private int beta;
    private MiniMaxStrategy selectedStrategy;

    public static MiniMaxStrategies getBestStrategy(Game game) {
        MiniMaxStrategies strategies = new MiniMaxStrategies(game, 0, true, Integer.MAX_VALUE, Integer.MIN_VALUE);
        strategies.execute();
        return strategies;
    }

    public MiniMaxStrategies(Game game, int depth, boolean isNextMaximisingPlayer, int alpha, int beta) {
        this.game = game;
        this.depth = depth;
        this.isNextMaximisingPlayer = isNextMaximisingPlayer;
        this.alpha = alpha;
        this.beta = beta;
    }

    public void execute() {
        selectedStrategy = getStrategies()
                .reduce(selectBestStrategy())
                .get();
    }

    public int getBestMove() {
        return selectedStrategy.getLastMove();
    }

    public int getBestScore() {
        return selectedStrategy.getScore();
    }

    private Stream<MiniMaxStrategy> getStrategies() {
        return game.getNextMoves()
                .stream()
                .map(getStrategy());
    }

    private Function<Game, MiniMaxStrategy> getStrategy() {
        return (Game gameMove) -> {
            MiniMaxStrategy miniMaxStrategy = new MiniMaxStrategy(gameMove, depth, isNextMaximisingPlayer, alpha, beta);
            miniMaxStrategy.execute();
            return miniMaxStrategy;
        };
    }

    private BinaryOperator<MiniMaxStrategy> selectBestStrategy() {
        return (MiniMaxStrategy bestStrategy, MiniMaxStrategy currentStrategy) ->
                selectBetterStrategy(bestStrategy, currentStrategy)
                        ? currentStrategy
                        : bestStrategy;
    }

    private boolean selectBetterStrategy(MiniMaxStrategy bestStrategy, MiniMaxStrategy currentStrategy) {
        return isNextMaximisingPlayer
                ? currentStrategy.getScore() > bestStrategy.getScore()
                : currentStrategy.getScore() < bestStrategy.getScore();
    }
}
