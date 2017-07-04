//import org.junit.Test;
//
//import static org.junit.Assert.*;
//
//public class GameTest {
//
//    private Game game = new Game();
//    private Board board = game.getBoard();
//
//    @Test
//    public void moveUpdatesBoardWithPlayerXforFirstMove() {
//        game.move(1);
//        assertEquals(Player.X, board.getSquareValue(1));
//    }
//
//    @Test
//    public void moveUpdatesCurrentPlayer() {
//        game.move(1);
//        assertEquals(Player.O, game.getCurrentPlayer());
//    }
//
//    @Test
//    public void moveUpdatesBoardWithPlayerOforSecondMove() {
//        game.move(1);
//        game.move(2);
//        assertEquals(Player.X, board.getSquareValue(1));
//        assertEquals(Player.O, board.getSquareValue(2));
//    }
//
//    @Test
//    public void moveDoesNotUpdateSquareOrToggleUserIfAlreadyTaken() {
//        game.move(1);
//        game.move(1);
//
//        // TODO: pass error state to renderer
//        assertEquals(Player.X, board.getSquareValue(1));
//        assertEquals(Player.O, game.getCurrentPlayer());
//    }
//
//    @Test
//    public void getWinnerReturnsNeitherIfNoWinner() {
//        assertEquals(Player.NEITHER, game.getWinner());
//    }
//
//    @Test
//    public void getWinnerReturnsPlayerWhenHasWinningRow() {
//        game.move(1);
//        game.move(5);
//        game.move(2);
//        game.move(6);
//        game.move(3);
//        assertEquals(Player.X, game.getWinner());
//    }
//
//    @Test
//    public void getWinnerReturnsPlayerWhenHasWinningCol() {
//        game.move(1);
//        game.move(2);
//        game.move(4);
//        game.move(3);
//        game.move(7);
//        assertEquals(Player.X, game.getWinner());
//    }
//
//    @Test
//    public void getWinnerReturnsPlayerWhenHasWinningDiagonal() {
//        game.move(1);
//        game.move(2);
//        game.move(5);
//        game.move(3);
//        game.move(9);
//        assertEquals(Player.X, game.getWinner());
//    }
//
//    @Test
//    public void isWinnerReturnsFalseWhenNoWinner() {
//        assertFalse(game.isWinner());
//    }
//
//    @Test
//    public void isWinnerReturnsTrueWhenWinner() {
//        game.move(1);
//        game.move(2);
//        game.move(5);
//        game.move(3);
//        game.move(9);
//        assertTrue(game.isWinner());
//    }
//
//    @Test
//    public void isGameOverReturnsFalseIfIncomplete() {
//        assertFalse(game.isGameOver());
//    }
//
//
////    @Test
////    public void isGameOverReturnsTrue
//}