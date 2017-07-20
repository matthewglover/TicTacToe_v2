import org.junit.Test;

import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;

import static org.junit.Assert.*;

public class GameStatusTest {

    @Test
    public void reportsWinnerOnGameOver() {
        GameStatusUIBuilder builder = new GameStatusUIBuilder("n\n");
        GameStatus gameStatus = new GameStatus(builder.getGameStatusUI());
        Game game = new Game(3);
        game.addObserver(gameStatus);
        // x x x
        // o o 6
        // 7 8 9
        runGame(game, new int[]{1, 4, 2, 5, 3});
        String winningMessage = String.format(GameStatusMessages.REPORT_WINNER, PlayerSymbol.X);
        String[] lines = builder.getIoTestHelper().getOutContentAsLines();
        assertEquals(winningMessage, lines[0]);
        assertEquals(GameStatusMessages.REQUEST_PLAY_AGAIN, lines[1]);
    }

    @Test
    public void reportsDrawOnGameOver() {
        GameStatusUIBuilder builder = new GameStatusUIBuilder("n\n");
        GameStatus gameStatus = new GameStatus(builder.getGameStatusUI());
        Game game = new Game(3);
        game.addObserver(gameStatus);
        // x o x
        // o o x
        // x x o
        runGame(game, new int[]{1, 4, 6, 5, 3, 2, 8, 9, 7});
        String[] lines = builder.getIoTestHelper().getOutContentAsLines();
        assertEquals(GameStatusMessages.REPORT_DRAW, lines[0]);
        assertEquals(GameStatusMessages.REQUEST_PLAY_AGAIN, lines[1]);
    }

    @Test
    public void notifiesNewGameRequestOnUserInputOf() {
        GameStatusObserver gameStatusObserver = new GameStatusObserver();
        GameStatusUIBuilder builder = new GameStatusUIBuilder("y\n");
        GameStatus gameStatus = new GameStatus(builder.getGameStatusUI());
        Game game = new Game(3);

        gameStatus.addObserver(gameStatusObserver);
        game.addObserver(gameStatus);

        runGame(game, new int[]{1, 4, 6, 5, 3, 2, 8, 9, 7});
        assertTrue(gameStatusObserver.newGameRequested());
    }

    @Test
    public void newGameRequestInputNotCaseSensitive() {
        GameStatusObserver gameStatusObserver = new GameStatusObserver();
        GameStatusUIBuilder builder = new GameStatusUIBuilder("Y\n");
        GameStatus gameStatus = new GameStatus(builder.getGameStatusUI());
        Game game = new Game(3);

        gameStatus.addObserver(gameStatusObserver);
        game.addObserver(gameStatus);

        runGame(game, new int[]{1, 4, 6, 5, 3, 2, 8, 9, 7});
        assertTrue(gameStatusObserver.newGameRequested());
    }

    @Test
    public void notifiesQuitRequestOnUserInput() {
        GameStatusObserver gameStatusObserver = new GameStatusObserver();
        GameStatusUIBuilder builder = new GameStatusUIBuilder("n\n");
        GameStatus gameStatus = new GameStatus(builder.getGameStatusUI());
        Game game = new Game(3);

        gameStatus.addObserver(gameStatusObserver);
        game.addObserver(gameStatus);

        runGame(game, new int[]{1, 4, 6, 5, 3, 2, 8, 9, 7});
        assertFalse(gameStatusObserver.newGameRequested());
    }

    private void runGame(Game game, int[] moves) {
        game.start();
        Arrays.stream(moves).forEach(game::move);
    }

    private class GameStatusObserver implements Observer {

        private boolean isNewGameRequest;

        @Override
        public void update(Observable o, Object arg) {
            isNewGameRequest = (Boolean) arg;
        }

        public boolean newGameRequested() {
            return isNewGameRequest;
        }
    }
}