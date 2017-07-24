import com.matthewglover.tictactoe.consoleui.BoardFormatter;
import com.matthewglover.tictactoe.consoleui.GameStatus;
import com.matthewglover.tictactoe.consoleui.GameStatusMessages;
import com.matthewglover.tictactoe.core.Game;
import com.matthewglover.tictactoe.core.PlayerSymbol;
import org.junit.Test;

import java.util.Observable;
import java.util.Observer;

import static org.junit.Assert.*;

public class GameStatusTest {

    @Test
    public void printsFinalBoardState() {
        GameStatusObserver gameStatusObserver = new GameStatusObserver();
        GameStatusUIBuilder builder = new GameStatusUIBuilder("n\n");
        GameStatus gameStatus = new GameStatus(builder.getGameStatusUI());
        Game game = new Game(3);

        gameStatus.addObserver(gameStatusObserver);
        game.addObserver(gameStatus);

        GameTestHelper.runGame(game, new int[]{1, 4, 6, 5, 3, 2, 8, 9, 7});
        String[] lines = builder.getIoTestHelper().getOutContentAsLines();
        assertEquals(new BoardFormatter(game.getBoard()).format(),
                IOTestHelper.removeClearLine(lines[0]) + "\n" + lines[1] + "\n" + lines[2] + "\n" + lines[3] + "\n" + lines[4]);
    }

    @Test
    public void reportsWinnerOnGameOver() {
        GameStatusUIBuilder builder = new GameStatusUIBuilder("n\n");
        GameStatus gameStatus = new GameStatus(builder.getGameStatusUI());
        int boardSize = 3;
        Game game = new Game(boardSize);
        int boardLineOffset = boardSize + 2;
        game.addObserver(gameStatus);
        // x x x
        // o o 6
        // 7 8 9
        GameTestHelper.runGame(game, new int[]{1, 4, 2, 5, 3});
        String winningMessage = String.format(GameStatusMessages.REPORT_WINNER, PlayerSymbol.X);
        String[] lines = builder.getIoTestHelper().getOutContentAsLines();
        assertEquals(winningMessage, lines[0 + boardLineOffset]);
        assertEquals(GameStatusMessages.REQUEST_PLAY_AGAIN, lines[1 + boardLineOffset]);
    }

    @Test
    public void reportsDrawOnGameOver() {
        GameStatusUIBuilder builder = new GameStatusUIBuilder("n\n");
        GameStatus gameStatus = new GameStatus(builder.getGameStatusUI());
        int boardSize = 3;
        Game game = new Game(boardSize);
        int boardLineOffset = boardSize + 2;
        game.addObserver(gameStatus);
        // x o x
        // o o x
        // x x o
        GameTestHelper.runGame(game, new int[]{1, 4, 6, 5, 3, 2, 8, 9, 7});
        String[] lines = builder.getIoTestHelper().getOutContentAsLines();
        assertEquals(GameStatusMessages.REPORT_DRAW, lines[0 + boardLineOffset]);
        assertEquals(GameStatusMessages.REQUEST_PLAY_AGAIN, lines[1 + boardLineOffset]);
    }

    @Test
    public void notifiesNewGameRequestOnUserInputOf() {
        GameStatusObserver gameStatusObserver = new GameStatusObserver();
        GameStatusUIBuilder builder = new GameStatusUIBuilder("y\n");
        GameStatus gameStatus = new GameStatus(builder.getGameStatusUI());
        Game game = new Game(3);

        gameStatus.addObserver(gameStatusObserver);
        game.addObserver(gameStatus);

        GameTestHelper.runGame(game, new int[]{1, 4, 6, 5, 3, 2, 8, 9, 7});
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

        GameTestHelper.runGame(game, new int[]{1, 4, 6, 5, 3, 2, 8, 9, 7});
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

        GameTestHelper.runGame(game, new int[]{1, 4, 6, 5, 3, 2, 8, 9, 7});
        assertFalse(gameStatusObserver.newGameRequested());
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