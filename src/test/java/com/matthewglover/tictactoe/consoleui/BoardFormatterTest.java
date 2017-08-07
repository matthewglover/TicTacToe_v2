package com.matthewglover.tictactoe.consoleui;

import com.matthewglover.tictactoe.core.Board;
import com.matthewglover.tictactoe.core.PlayerSymbol;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class BoardFormatterTest {


    @Test
    public void rendersEmpty3x3Board() {
        Board board = new Board(3);
        String expected = " 1 | 2 | 3 \n" +
                "--- --- ---\n" +
                " 4 | 5 | 6 \n" +
                "--- --- ---\n" +
                " 7 | 8 | 9 ";
        assertEquals(expected, (new BoardFormatter(board)).format());
    }

    @Test
    public void rendersEmpty4x4Board() {
        Board board = new Board(4);
        String expected = "  1 |  2 |  3 |  4 \n" +
                "---- ---- ---- ----\n" +
                "  5 |  6 |  7 |  8 \n" +
                "---- ---- ---- ----\n" +
                "  9 | 10 | 11 | 12 \n" +
                "---- ---- ---- ----\n" +
                " 13 | 14 | 15 | 16 ";
        assertEquals(expected, (new BoardFormatter(board)).format());
    }

    @Test
    public void rendersBoardWithMoves() {
        Board board = new Board(3);
        board.setSquare(1, PlayerSymbol.X);
        board.setSquare(2, PlayerSymbol.O);
        board.setSquare(3, PlayerSymbol.X);
        board.setSquare(5, PlayerSymbol.X);
        board.setSquare(6, PlayerSymbol.X);
        String expected = " X | O | X \n" +
                "--- --- ---\n" +
                " 4 | X | X \n" +
                "--- --- ---\n" +
                " 7 | 8 | 9 ";
        assertEquals(expected, (new BoardFormatter(board)).format());
    }
}