import org.junit.Test;

import static org.junit.Assert.*;

public class BoardTest {

    Board board = new Board();

    @Test
    public void getSquareValueReturnsEmptyWhenSquareEmpty() {
       assertEquals(Player.NEITHER, board.getSquareValue(0, 0));
    }

    @Test
    public void getSquareValueReturnsPlayer1WhenSquareTakenByPlayerX() {
        board.setSquareValue(0, 0, Player.X);
        assertEquals(Player.X, board.getSquareValue(0, 0));
    }

    @Test
    public void getSquareValueReturnsPlayer2WhenSquareTakenByPlayerO() {
        board.setSquareValue(0, 0, Player.O);
        assertEquals(Player.O, board.getSquareValue(0, 0));
    }

    @Test
    public void isEmptySquareReturnsTrueWhenSquareIsEmpty() {
        assertTrue(board.isEmptySquare(0, 0));
    }

    @Test
    public void isEmptySquareReturnsFalseWhenSquareIsTakenByPlayerX() {
        board.setSquareValue(0, 0, Player.X);
        assertFalse(board.isEmptySquare(0, 0));
    }

    @Test
    public void isEmptySquareReturnsFalseWhenSquareIsTakenByPlayerO() {
        board.setSquareValue(0, 0, Player.O);
        assertFalse(board.isEmptySquare(0, 0));
    }

    @Test
    public void isFullReturnsFalseWhenBoardIsEmpty() {
        assertFalse(board.isFull());
    }

    @Test
    public void isFullReturnsTrueWhenBoardIsFull() {
        fillBoard();
        assertTrue(board.isFull());
    }

    @Test
    public void isFullReturnsFalseWhenBoardPartiallyFull() {
        fillBoard();
        board.setSquareValue(2, 2, Player.NEITHER);
        assertFalse(board.isFull());
    }

    @Test
    public void isAnyWinningRowReturnsFalseWhenNone() {
        assertFalse(board.isAnyWinningRow(Player.X));
    }

    @Test
    public void isAnyWinningRowReturnsTrueWhenExists() {
        board.setSquareValue(0, 0, Player.X);
        board.setSquareValue(0, 1, Player.X);
        board.setSquareValue(0, 2, Player.X);
        assertTrue(board.isAnyWinningRow(Player.X));
    }

    @Test
    public void isAnyWinningColumnReturnsFalseWhenNone() {
        assertFalse(board.isAnyWinningColumn(Player.O));
    }

    @Test
    public void isAnyWinningColumnReturnsTrueWhenExists() {
        board.setSquareValue(0, 0, Player.X);
        board.setSquareValue(1, 0, Player.X);
        board.setSquareValue(2, 0, Player.X);
        assertTrue(board.isAnyWinningColumn(Player.X));
    }

    @Test
    public void isAnyWinningDiagonalReturnsFalseWhenNone() {
        assertFalse(board.isAnyWinningDiagonal(Player.X));
    }

    @Test
    public void isAnyWinningDiagonalReturnsTrueWhenWinningDiagonalFromTopLeft() {
        board.setSquareValue(0, 0, Player.X);
        board.setSquareValue(1, 1, Player.X);
        board.setSquareValue(2, 2, Player.X);
        assertTrue(board.isAnyWinningDiagonal(Player.X));
    }

    @Test
    public void isAnyWinningDiagonalReturnsTrueWhenWinningDiagonalFromTopRight() {
        board.setSquareValue(0, 2, Player.X);
        board.setSquareValue(1, 1, Player.X);
        board.setSquareValue(2, 0, Player.X);
        assertTrue(board.isAnyWinningDiagonal(Player.X));
    }

    @Test
    public void getTotalSquares() {
        assertEquals(9, board.getTotalSquares());
    }

    @Test
    public void convertPositionReturnsPointForTopLeftSquare() {
        BoardRef boardRef = board.convertPosition(1);
        assertEquals(0, boardRef.row);
        assertEquals(0, boardRef.col);
    }

    @Test
    public void convertPositionReturnsPointForBottomRightSquare() {
        BoardRef boardRef = board.convertPosition(9);
        assertEquals(2, boardRef.row);
        assertEquals(2, boardRef.col);
    }

    @Test
    public void convertPositionReturnsPointForRightCentreSquare() {
        BoardRef boardRef = board.convertPosition(6);
        assertEquals(1, boardRef.row);
        assertEquals(2, boardRef.col);
    }

    @Test
    public void convertBoardRefReturns1ForTopLeftSquare() {
        int position = board.convertBoardRef(0, 0);
        assertEquals(1, position);
    }

    @Test
    public void convertBoardRefReturns9ForBottomRightSquare() {
        int position = board.convertBoardRef(2, 2);
        assertEquals(9, position);
    }

    @Test
    public void convertBoardRefReturns6ForRightCentreSquare() {
        int position = board.convertBoardRef(1, 2);
        assertEquals(6, position);
    }

    private void fillBoard() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board.setSquareValue(row, col, Player.X);
            }
        }
    }
}