import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class UnbeatableComputer {
    private final Game game;
    private Game selectedGameState;

    public UnbeatableComputer(Game game) {
        this.game = game;
    }

    public void run() {
        List<Game> nextGameStates = game.getNextMoves();
        List<Integer> nextGameStateScores = nextGameStates
                .stream()
                .map(UnbeatableComputer::runMove)
                .collect(Collectors.toList());

        int bestScore = Collections.max(nextGameStateScores);
        selectedGameState = nextGameStates.get(nextGameStateScores.indexOf(bestScore));
    }

    public int getMove() {
        return selectedGameState.getLastMove();
    }

    private static int runMove(Game game) {
        MiniMax miniMax = new MiniMax(game, 0, true);
        return miniMax.run();
    }
}
