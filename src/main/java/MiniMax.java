import java.util.List;
import java.util.stream.Collectors;

public class MiniMax {
    private Game game;

    public MiniMax(Game game) {
        this.game = game;
    }

    public int run() {
        List<Game> bestGames = getBestGames();
        return bestGames.get(0).getLastMove();
    }

    private Game runGame(int squareNumber) {
        Game newGame = game.duplicate();
        newGame.move(squareNumber);
        return newGame;
    }

    private List<Game> getBestGames() {
        List<Game> allGames = getAllGames();
        List<Game> winningGames = getWinningGames(allGames);

        if (isSingleStrategy(allGames)) {
            return allGames;
        }


        if (isAnyStrategy(winningGames)) {
            return winningGames;
        }

        return null;
    }

    private boolean isSingleStrategy(List<Game> allGames) {
        return allGames.size() == 1;
    }

    private boolean isAnyStrategy(List<Game> winningGames) {
        return !winningGames.isEmpty();
    }

    private List<Game> getAllGames() {
        List<Integer> emptySquares = game.getEmptySquares();
        return emptySquares
                .stream()
                .map(squareNumber -> runGame(squareNumber))
                .collect(Collectors.toList());
    }

    private List<Game> getWinningGames(List<Game> allGames) {
        return allGames
                .stream()
                .filter(this::isWinningStrategy)
                .collect(Collectors.toList());
    }

    private boolean isWinningStrategy(Game nextGame) {
        return nextGame.getWinner() == game.getCurrentPlayer();
    }
}
