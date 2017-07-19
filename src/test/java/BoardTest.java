import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class BoardTest {

    Board board = new Board(3);

    @Test
    public void squareIsNeitherXNorOWhenItsEmpty() {
        assertEquals(PlayerSymbol.NEITHER, board.getSquare(1));
    }

    @Test
    public void squareIsXWhenTakenByX() {
        board.setSquare(1, PlayerSymbol.X);
        assertEquals(PlayerSymbol.X, board.getSquare(1));
    }

    @Test
    public void squareIsOWhenTakenByO() {
        board.setSquare(1, PlayerSymbol.O);
        assertEquals(PlayerSymbol.O, board.getSquare(1));
    }

    @Test
    public void squareIsEmptyWhenItsEmpty() {
        assertTrue(board.isEmptySquare(1));
    }

    @Test
    public void squareIsNotEmptyWhenItsTakenByX() {
        board.setSquare(1, PlayerSymbol.X);
        assertFalse(board.isEmptySquare(1));
    }

    @Test
    public void squareIsNotEmptyWhenItsTakenByO() {
        board.setSquare(1, PlayerSymbol.O);
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
        board.setSquare(2, PlayerSymbol.NEITHER);
        assertFalse(board.isFull());
    }

    @Test
    public void notWinningLineWhenEmptyBoard() {
        assertFalse(board.isAnyWinningLine(PlayerSymbol.X));
    }

    @Test
    public void isWinningLineWhenWinningRow() {
        board.setSquare(1, PlayerSymbol.O);
        board.setSquare(2, PlayerSymbol.O);
        board.setSquare(3, PlayerSymbol.O);
        assertTrue(board.isAnyWinningLine(PlayerSymbol.O));
    }

    @Test
    public void winningLineWhenWinningColumn() {
        board.setSquare(1, PlayerSymbol.X);
        board.setSquare(4, PlayerSymbol.X);
        board.setSquare(7, PlayerSymbol.X);
        assertTrue(board.isAnyWinningLine(PlayerSymbol.X));
    }

    @Test
    public void winningLineWhenWinningDiagonalTopLeftToBottomRight() {
        board.setSquare(1, PlayerSymbol.O);
        board.setSquare(5, PlayerSymbol.O);
        board.setSquare(9, PlayerSymbol.O);
        assertTrue(board.isAnyWinningLine(PlayerSymbol.O));
    }

    @Test
    public void winningLineWhenWinningDiagonalTopRightToBottomLeft() {
        board.setSquare(3, PlayerSymbol.X);
        board.setSquare(5, PlayerSymbol.X);
        board.setSquare(7, PlayerSymbol.X);
        assertTrue(board.isAnyWinningLine(PlayerSymbol.X));
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
        board.setSquare(1, PlayerSymbol.X);
        board.setSquare(2, PlayerSymbol.X);
        board.setSquare(3, PlayerSymbol.X);
        board.setSquare(4, PlayerSymbol.X);
        assertTrue(board.isAnyWinningLine(PlayerSymbol.X));
    }

    @Test
    public void winningLineWhenWinningColumnFor4x4Board() {
        board = new Board(4);
        board.setSquare(1, PlayerSymbol.X);
        board.setSquare(5, PlayerSymbol.X);
        board.setSquare(9, PlayerSymbol.X);
        board.setSquare(13, PlayerSymbol.X);
        assertTrue(board.isAnyWinningLine(PlayerSymbol.X));
    }

    @Test
    public void winningLineWhenWinningDiagonalTopLeftFor4x4Board() {
        board = new Board(4);
        board.setSquare(1, PlayerSymbol.X);
        board.setSquare(6, PlayerSymbol.X);
        board.setSquare(11, PlayerSymbol.X);
        board.setSquare(16, PlayerSymbol.X);
        assertTrue(board.isAnyWinningLine(PlayerSymbol.X));
    }

    @Test
    public void winningLineWhenWinningDiagonalTopRightFor4x4Board() {
        board = new Board(4);
        board.setSquare(4, PlayerSymbol.X);
        board.setSquare(7, PlayerSymbol.X);
        board.setSquare(10, PlayerSymbol.X);
        board.setSquare(13, PlayerSymbol.X);
        assertTrue(board.isAnyWinningLine(PlayerSymbol.X));
    }

    @Test
    public void emptySquaresAreEmpty() {
        board.setSquare(1, PlayerSymbol.X);
        board.setSquare(2, PlayerSymbol.X);
        board.setSquare(3, PlayerSymbol.X);
        board.setSquare(4, PlayerSymbol.X);
        board.setSquare(8, PlayerSymbol.X);
        List<Integer> emptySquaresList = board.getEmptySquares();
        int[] emptySquaresArray =
                emptySquaresList.stream()
                        .mapToInt(Integer::intValue)
                        .toArray();
        assertArrayEquals(new int[]{5, 6, 7, 9}, emptySquaresArray);
    }

    private void fillBoard() {
        for (int squareNumber = 1; squareNumber <= 9; squareNumber++) {
            board.setSquare(squareNumber, PlayerSymbol.X);
        }
    }
}