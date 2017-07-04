import org.junit.Test;

import static org.junit.Assert.*;

public class BoardTest {

    Board board = new Board();

    @Test
    public void squareIsNeitherXNorOWhenItsEmpty() {
       assertEquals(Player.NEITHER, board.getSquare(1));
    }

    @Test
    public void squareIsXWhenTakenByX() {
        board.setSquare(1, Player.X);
        assertEquals(Player.X, board.getSquare(1));
    }

    @Test
    public void squareIsOWhenTakenByO() {
        board.setSquare(1, Player.O);
        assertEquals(Player.O, board.getSquare(1));
    }

    @Test
    public void squareIsEmptyWhenItsEmpty() {
        assertTrue(board.isEmpty(1));
    }

    @Test
    public void squareIsNotEmptyWhenItsTakenByX() {
        board.setSquare(1, Player.X);
        assertFalse(board.isEmpty(1));
    }

    @Test
    public void squareIsNotEmptyWhenItsTakenByO() {
        board.setSquare(1, Player.O);
        assertFalse(board.isEmpty(1));
    }

    @Test
    public void boardIsNotFullWhenItsEmpty() {
        assertFalse(board.isFull());
    }

    @Test
    public void boardIsFullWhenItsFull() {
        fillBoard();
        assertTrue(board.isFull());
    }

    @Test
    public void boardIsNotFullWhenItsPartiallyFull() {
        fillBoard();
        board.setSquare(2, Player.NEITHER);
        assertFalse(board.isFull());
    }

    @Test
    public void notWinningLineWhenEmptyBoard() {
        assertFalse(board.isAnyWinningLine(Player.X));
    }

    @Test
    public void winningLineWhenWinningRow() {
        board.setSquare(1, Player.X);
        board.setSquare(2, Player.X);
        board.setSquare(3, Player.X);
        assertTrue(board.isAnyWinningLine(Player.X));
    }

    @Test
    public void winningLineWhenWinningColumn() {
        board.setSquare(1, Player.X);
        board.setSquare(4, Player.X);
        board.setSquare(7, Player.X);
        assertTrue(board.isAnyWinningLine(Player.X));
    }
    @Test
    public void winningLineWhenWinningDiagonalTopLeftToBottomRight() {
        board.setSquare(1, Player.X);
        board.setSquare(5, Player.X);
        board.setSquare(9, Player.X);
        assertTrue(board.isAnyWinningLine(Player.X));
    }

    @Test
    public void winningLineWhenWinningDiagonalTopRightToBottomLeft(){
        board.setSquare(3, Player.X);
        board.setSquare(5, Player.X);
        board.setSquare(7, Player.X);
        assertTrue(board.isAnyWinningLine(Player.X));
    }

    private void fillBoard() {
        for (int squareNumber = 1; squareNumber <= 9; squareNumber++) {
            board.setSquare( squareNumber, Player.X);
        }
    }
}