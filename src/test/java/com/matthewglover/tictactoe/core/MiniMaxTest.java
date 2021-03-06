package com.matthewglover.tictactoe.core;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class MiniMaxTest {
    @Test
    public void selectsWinningMoveOverLosingMove() {
        // x o x
        // x o o
        // 7 8 x
        Game game = new Game(3);
        GameTestHelper.runGame(game, new int[]{1, 2, 3, 5, 4, 6, 9});
        SimpleMiniMax miniMax = SimpleMiniMax.run(game);
        assertEquals(8, miniMax.getMove());
    }

    @Test
    public void selectsDrawingMoveOverLosingMove() {
        // x o x
        // 4 5 6
        // 7 o 9
        Game game = new Game(3);
        GameTestHelper.runGame(game, new int[]{1, 2, 3, 8});
        SimpleMiniMax miniMax = SimpleMiniMax.run(game);
        assertEquals(5, miniMax.getMove());
    }

    @Test
    public void maximisingPlayerWithTwoWinningMovesSelectsFirstMove() {
        // x o 3
        // 4 x 6
        // 7 8 o
        Game game = new Game(3);
        GameTestHelper.runGame(game, new int[]{1, 2, 5, 9});
        SimpleMiniMax miniMax = SimpleMiniMax.run(game);
        assertEquals(4, miniMax.getMove());
    }
}
