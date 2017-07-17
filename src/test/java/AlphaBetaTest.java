import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class AlphaBetaTest {
    @Test
    public void selectsWinningMoveOverLosingMove() {
        // x o x
        // x o o
        // 7 8 x
        Game game = makeMoves(new int[]{1, 2, 3, 5, 4, 6, 9});
        AlphaBeta alphaBeta = AlphaBeta.getMove(game);
        assertEquals(8, alphaBeta.getMove());
    }

    @Test
    public void selectsDrawingMoveOverLosingMove() {
        // x o x
        // 4 5 6
        // 7 o 9
        Game game = makeMoves(new int[]{1, 2, 3, 8});
        AlphaBeta alphaBeta = AlphaBeta.getMove(game);
        assertEquals(5, alphaBeta.getMove());
    }

    @Test
    public void maximisingPlayerWinInTwoMovesSelectsFirstMove() {
        // x o 3
        // 4 x 6
        // 7 8 o
        Game game = makeMoves(new int[]{1, 2, 5, 9});
        AlphaBeta alphaBeta = AlphaBeta.getMove(game);
        assertEquals(4, alphaBeta.getMove());
    }

    private Game makeMoves(int[] moves) {
        Game game = new Game(3);
        for (int squareNumber : moves) {
            game.move(squareNumber);
        }
        return game;
    }
}
