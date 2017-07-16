import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Stream;

public class MiniMax {

    private static final int DRAW_SCORE = 0;
    private static final int WINNING_SCORE = 10;

    private final Game game;
    private final int depth;
    private final int nextDepth;
    private final boolean isMaximisingPlayer;
    private final boolean isNextMaximisingPlayer;
    private int score;


    public MiniMax(Game game, int depth, boolean isMaximisingPlayer) {
        this.game = game;
        this.depth = depth;
        nextDepth = depth + 1;
        this.isMaximisingPlayer = isMaximisingPlayer;
        isNextMaximisingPlayer = !isMaximisingPlayer;
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
        score = getNextMoveScores()
                .reduce(selectBetterScore())
                .get();
    }

    private Stream<Integer> getNextMoveScores() {
        return game.getNextMoves()
                .stream()
                .map(getMiniMaxScore());
    }

    private Function<Game, Integer> getMiniMaxScore() {
        return (Game move) -> {
            MiniMax miniMax = new MiniMax(move, nextDepth, isNextMaximisingPlayer);
            miniMax.execute();
            return miniMax.getScore();
        };
    }

    private BinaryOperator<Integer> selectBetterScore() {
        return (Integer bestScore, Integer moveScore) ->
                isNextMaximisingPlayer
                        ? Math.max(bestScore, moveScore)
                        : Math.min(bestScore, moveScore);
    }
}
