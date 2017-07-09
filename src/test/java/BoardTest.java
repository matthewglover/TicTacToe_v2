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
        assertTrue(board.isEmptySquare(1));
    }

    @Test
    public void squareIsNotEmptyWhenItsTakenByX() {
        board.setSquare(1, Player.X);
        assertFalse(board.isEmptySquare(1));
    }

    @Test
    public void squareIsNotEmptyWhenItsTakenByO() {
        board.setSquare(1, Player.O);
        assertFalse(board.isEmptySquare(1));
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
    public void isWinningLineWhenWinningRow() {
        board.setSquare(1, Player.O);
        board.setSquare(2, Player.O);
        board.setSquare(3, Player.O);
        assertTrue(board.isAnyWinningLine(Player.O));
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
        board.setSquare(1, Player.O);
        board.setSquare(5, Player.O);
        board.setSquare(9, Player.O);
        assertTrue(board.isAnyWinningLine(Player.O));
    }

    @Test
    public void winningLineWhenWinningDiagonalTopRightToBottomLeft(){
        board.setSquare(3, Player.X);
        board.setSquare(5, Player.X);
        board.setSquare(7, Player.X);
        assertTrue(board.isAnyWinningLine(Player.X));
    }

    @Test
    public void boardSizeCanBeSelected() {
        board = new Board(4);
        assertEquals(4, board.getSize());
        assertEquals(16, board.getTotalSquares());
    }

    @Test
    public void winningLineWhenWinningRowFor4x4Board() {
        board = new Board(4);
        board.setSquare(1, Player.X);
        board.setSquare(2, Player.X);
        board.setSquare(3, Player.X);
        board.setSquare(4, Player.X);
        assertTrue(board.isAnyWinningLine(Player.X));
    }

    @Test
    public void winningLineWhenWinningColumnFor4x4Board() {
        board = new Board(4);
        board.setSquare(1, Player.X);
        board.setSquare(5, Player.X);
        board.setSquare(9, Player.X);
        board.setSquare(13, Player.X);
        assertTrue(board.isAnyWinningLine(Player.X));
    }

    @Test
    public void winningLineWhenWinningDiagonalTopLeftFor4x4Board() {
        board = new Board(4);
        board.setSquare(1, Player.X);
        board.setSquare(6, Player.X);
        board.setSquare(11, Player.X);
        board.setSquare(16, Player.X);
        assertTrue(board.isAnyWinningLine(Player.X));
    }

    @Test
    public void winningLineWhenWinningDiagonalTopRightFor4x4Board() {
        board = new Board(4);
        board.setSquare(4, Player.X);
        board.setSquare(7, Player.X);
        board.setSquare(10, Player.X);
        board.setSquare(13, Player.X);
        assertTrue(board.isAnyWinningLine(Player.X));
    }

    private void fillBoard() {
        for (int squareNumber = 1; squareNumber <= 9; squareNumber++) {
            board.setSquare( squareNumber, Player.X);
        }
    }
}