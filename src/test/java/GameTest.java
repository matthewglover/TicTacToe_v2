import com.matthewglover.tictactoe.core.Board;
import com.matthewglover.tictactoe.core.Game;
import com.matthewglover.tictactoe.core.PlayerSymbol;
import org.junit.Test;

import java.util.Observable;
import java.util.Observer;

import static org.junit.Assert.*;

public class GameTest {

    private Game game = new Game(3);
    private Board board = game.getBoard();

    @Test
    public void xMovesFirst() {
        game.move(1);
        assertEquals(PlayerSymbol.X, board.getSquare(1));
    }

    @Test
    public void playersTakeTurns() {
        GameTestHelper.runGame(game, new int[]{1, 2});
        assertEquals(PlayerSymbol.O, board.getSquare(2));
    }

    @Test
    public void playerCanOnlyTakeEmptySquares() {
        GameTestHelper.runGame(game, new int[]{1, 1});
        assertEquals(PlayerSymbol.X, board.getSquare(1));
        assertEquals(PlayerSymbol.O, game.getNextPlayerSymbol());
    }

    @Test
    public void xWinsWhenHasWinningLine() {
        GameTestHelper.runGame(game, new int[]{1, 2, 4, 3, 7});
        assertTrue(game.isWinner());
        assertEquals(PlayerSymbol.X, game.getWinner());
    }

    @Test
    public void oWinsWhenHasWinningLine() {
        GameTestHelper.runGame(game, new int[]{5, 1, 6, 2, 7, 3});
        assertTrue(game.isWinner());
        assertEquals(PlayerSymbol.O, game.getWinner());
    }

    @Test
    public void gameNotOverWhenEmptyBoard() {
        assertFalse(game.isOver());
    }

    @Test
    public void gameOverWhenThereIsAWinner() {
        GameTestHelper.runGame(game, new int[]{1, 5, 2, 6, 3});
        assertTrue(game.isOver());
    }

    @Test
    public void gameOverWhenBoardIsFullAndNoWinner() {
        GameTestHelper.runGame(game, new int[]{1, 5, 3, 2, 4, 7, 6, 9, 8});
        assertTrue(game.isOver());
    }

    @Test
    public void boardSizeCanBeSelected() {
        game = new Game(4);
        Board board = game.getBoard();
        assertEquals(4, board.getSize());
    }

    @Test
    public void noWinnerIfLineOf3in4x4Game() {
        game = new Game(4);
        GameTestHelper.runGame(game, new int[]{1, 5, 2, 9, 3, 13, 6, 15, 11});
        assertFalse(game.isWinner());
    }

    @Test
    public void winnerIfLineOf4in4x4Game() {
        game = new Game(4);
        GameTestHelper.runGame(game, new int[]{1, 5, 2, 9, 3, 13, 4});
        assertTrue(game.isWinner());
        assertEquals(PlayerSymbol.X, game.getWinner());
    }

    @Test
    public void duplicateHasSameMovesAsOriginal() {
        GameTestHelper.runGame(game, new int[]{1, 2, 3});
        Game duplicateGame = game.duplicate();
        Board duplicateBoard = duplicateGame.getBoard();
        assertEquals(game.getNextPlayerSymbol(), duplicateGame.getNextPlayerSymbol());
        for (int i = 1; i <= board.getTotalSquares(); i++) {
            assertEquals(board.getSquare(i), duplicateBoard.getSquare(i));
        }
    }

    @Test
    public void duplicateHasSameCurrentPlayerAsOriginal() {
        GameTestHelper.runGame(game, new int[]{1, 2});
        Game duplicateGame = game.duplicate();
        assertEquals(game.getNextPlayerSymbol(), duplicateGame.getNextPlayerSymbol());
    }

    @Test
    public void startGameNotifiesObserversWithNextPlayer() {
        TestGameObserver testGameObserver = new TestGameObserver();
        game.addObserver(testGameObserver);
        game.start();
        assertEquals(game.getNextPlayerSymbol(), testGameObserver.getNextPlayerSymbol());
    }

    public static class TestGameObserver implements Observer {

        private PlayerSymbol nextPlayerSymbol;

        @Override
        public void update(Observable game, Object arg) {
            nextPlayerSymbol = (PlayerSymbol) arg;
        }

        public PlayerSymbol getNextPlayerSymbol() {
            return nextPlayerSymbol;
        }
    }
}