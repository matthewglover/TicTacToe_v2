import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class MiniMaxTest {
    @Test
    public void selectsWinningMoveOverLosingMove() {
        // x o x
        // x o o
        // 7 8 x
        Game game = makeMoves(new int[]{1, 2, 3, 5, 4, 6, 9});
        MiniMax miniMax = MiniMax.run(game);
        assertEquals(8, miniMax.run());
    }

    @Test
    public void selectsDrawingMoveOverLosingMove() {
        // x o x
        // 4 5 6
        // 7 o 9
        Game game = makeMoves(new int[]{1, 2, 3, 8});
        MiniMax miniMax = MiniMax.run(game);
        assertEquals(5, miniMax.run());
    }

    @Test
    public void maximisingPlayerWinInTwoMovesSelectsFirstMove() {
        // x o 3
        // 4 x 6
        // 7 8 o
        Game game = makeMoves(new int[]{1, 2, 5, 9});
        MiniMax miniMax = MiniMax.run(game);
        assertEquals(4, miniMax.run());
    }

    private Game makeMoves(int[] moves) {
        Game game = new Game(3);
        for (int squareNumber : moves) {
            game.move(squareNumber);
        }
        return game;
    }
}
