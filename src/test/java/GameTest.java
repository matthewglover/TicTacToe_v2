import org.junit.Test;

import static org.junit.Assert.*;
public class GameTest {

    private Game game = new Game();
    private BoardReader board = game.getBoard();

    @Test
    public void xMovesFirst() {
        game.move(1);
        assertEquals(Player.X, board.getSquare(1));
    }

    @Test
    public void playersTakeTurns() {
        game.move(1);
        game.move(2);
        assertEquals(Player.O, board.getSquare(2));
    }

    @Test
    public void playerCanOnlyTakeEmptySquares() {
        game.move(1);
        game.move(1);
        assertEquals(Player.X, board.getSquare(1));
        assertEquals(Player.O, game.getCurrentPlayer());
    }

    @Test
    public void xWinsWhenHasWinningLine() {
        game.move(1);
        game.move(2);
        game.move(4);
        game.move(3);
        game.move(7);
        assertTrue(game.isWinner());
        assertEquals(Player.X, game.getWinner());
    }

    @Test
    public void oWinsWhenHasWinningLine() {
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
    public void gameOverWhenThereIsAWinner() {
        game.move(1);
        game.move(5);
        game.move(2);
        game.move(6);
        game.move(3);
        assertTrue(game.isOver());
    }

    @Test
    public void gameOverWhenBoardIsFullAndNoWinner() {
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

    @Test
    public void boardSizeCanBeSelected() {
        game = new Game(4);
        BoardReader board = game.getBoard();
        assertEquals(4, board.getSize());
    }

    @Test
    public void noWinnerIfLineOf3in4x4Game() {
        game = new Game(4);
        game.move(1);
        game.move(5);
        game.move(2);
        game.move(9);
        game.move(3);
        game.move(13);
        game.move(6);
        game.move(15);
        game.move(11);
        assertFalse(game.isWinner());
    }

    @Test
    public void winnerIfLineOf4in4x4Game() {
        game = new Game(4);
        game.move(1);
        game.move(5);
        game.move(2);
        game.move(9);
        game.move(3);
        game.move(13);
        game.move(4);
        assertTrue(game.isWinner());
        assertEquals(Player.X, game.getWinner());
    }
}