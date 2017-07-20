import org.junit.Ignore;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class AlphaBetaTest {
    @Test
    public void selectsWinningMoveOverLosingMove() {
        // x o x
        // x o o
        // 7 8 x
        Game game = new Game(3);
        GameTestHelper.runGame(game, new int[]{1, 2, 3, 5, 4, 6, 9});
        AlphaBeta alphaBeta = AlphaBeta.run(game);
        assertEquals(8, alphaBeta.getMove());
    }

    @Test
    public void selectsDrawingMoveOverLosingMove() {
        // x o x
        // 4 5 6
        // 7 o 9
        Game game = new Game(3);
        GameTestHelper.runGame(game, new int[]{1, 2, 3, 8});
        AlphaBeta alphaBeta = AlphaBeta.run(game);
        assertEquals(5, alphaBeta.getMove());
    }

    @Test
    public void runsBiggerGame() {
        Game game = new Game(4);
        GameTestHelper.runGame(game, new int[]{1, 5, 2, 6, 3, 7});
        AlphaBeta alphaBeta = AlphaBeta.run(game);
        assertEquals(4, alphaBeta.getMove());
    }

    @Test
    public void maximisingPlayerWinInTwoMovesSelectsFirstMove() {
        // x o 3
        // 4 x 6
        // 7 8 o
        Game game = new Game(3);
        GameTestHelper.runGame(game, new int[]{1, 2, 5, 9});
        AlphaBeta alphaBeta = AlphaBeta.run(game);
        assertEquals(4, alphaBeta.getMove());
    }

    @Test
    @Ignore
    public void runs4x4Game() {
        Game game = new Game(4);
        GameTestHelper.runGame(game, new int[]{5, 9, 6, 10, 7, 11});

        long abStartTime = System.nanoTime();
        AlphaBeta alphaBeta = AlphaBeta.run(game);
        long abEndTime = System.nanoTime();
        long abDuration = (abEndTime - abStartTime) / 1000000;

        long mmStartTime = System.nanoTime();
        SimpleMiniMax miniMax = SimpleMiniMax.run(game);
        long mmEndTime = System.nanoTime();
        long mmDuration = (mmEndTime - mmStartTime) / 1000000;

        assertEquals(8, alphaBeta.getMove());
        assertEquals(8, miniMax.getMove());
        System.out.println("AlphaBeta: " + abDuration + ", SimpleMiniMax: " + mmDuration);
    }
}
