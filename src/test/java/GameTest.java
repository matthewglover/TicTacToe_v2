import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest {

    private Game game = new Game(new ConsoleRenderer(System.out));
    private Board board = game.getBoard();

    @Test
    public void firstMoveTakesSquareForPlayerXIfEmpty() {
        game.move(1);
        assertEquals(Player.X, board.getSquare(1));
    }

    @Test
    public void secondMoveTakesSquareForPlayerOIfEmpty() {
        game.move(1);
        game.move(2);
        assertEquals(Player.X, board.getSquare(1));
        assertEquals(Player.O, board.getSquare(2));
    }

    @Test
    public void successfulMoveTakesSquareAndUpdatesCurrentPlayer() {
        game.move(1);
        assertEquals(Player.X, board.getSquare(1));
        assertEquals(Player.O, game.getCurrentPlayer());
    }

    @Test
    public void unsuccessfulMoveDoesntTakeSquareOrUpdateCurrentPlayer() {
        game.move(1);
        game.move(1);

        // TODO: pass error state to renderer
        assertEquals(Player.X, board.getSquare(1));
        assertEquals(Player.O, game.getCurrentPlayer());
    }

    @Test
    public void neitherPlayerIsWinnerForEmptyBoard() {
        assertFalse(game.isWinner());
        assertEquals(Player.NEITHER, game.getWinner());
    }

    @Test
    public void playerXWinsWhenHasWinningLine() {
        game.move(1);
        game.move(5);
        game.move(2);
        game.move(6);
        game.move(3);
        assertTrue(game.isWinner());
        assertEquals(Player.X, game.getWinner());
    }

    @Test
    public void playerOWinsWhenHasWinningLine() {
        game.move(5);
        game.move(1);
        game.move(6);
        game.move(2);
        game.move(7);
        game.move(3);
        assertTrue(game.isWinner());
        assertEquals(Player.O, game.getWinner());
    }

    @Test
    public void gameNotOverWhenEmptyBoard() {
        assertFalse(game.isOver());
    }

    @Test
    public void gameOverWhenIsWinner() {
        game.move(1);
        game.move(5);
        game.move(2);
        game.move(6);
        game.move(3);
        assertTrue(game.isOver());
    }

    @Test
    public void gameOverWhenBoardFullAndNoWinner() {
        game.move(1);
        game.move(5);
        game.move(3);
        game.move(2);
        game.move(4);
        game.move(7);
        game.move(6);
        game.move(9);
        game.move(8);
        assertTrue(game.isOver());
    }


//    @Test
//    public void isGameOverReturnsTrue
}