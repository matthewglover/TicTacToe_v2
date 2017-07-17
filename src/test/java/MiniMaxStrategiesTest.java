import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class MiniMaxStrategiesTest {
    @Test
    public void selectsWinningMoveOverLosingMove() {
        // x o x
        // x o o
        // 7 8 x
        Game game = makeMoves(new int[]{1, 2, 3, 5, 4, 6, 9});
        MiniMaxStrategies miniMaxStrategies = MiniMaxStrategies.getBestStrategy(game);
        assertEquals(8, miniMaxStrategies.getBestMove());
    }

    @Test
    public void selectsDrawingMoveOverLosingMove() {
        // x o x
        // 4 5 6
        // 7 o 9
        Game game = makeMoves(new int[]{1, 2, 3, 8});
        MiniMaxStrategies miniMaxStrategies = MiniMaxStrategies.getBestStrategy(game);
        assertEquals(5, miniMaxStrategies.getBestMove());
    }

    @Test
    public void maximisingPlayerWinInTwoMovesSelectsFirstMove() {
        // x o 3
        // 4 x 6
        // 7 8 o
        Game game = makeMoves(new int[]{1, 2, 5, 9});
        MiniMaxStrategies miniMaxStrategies = MiniMaxStrategies.getBestStrategy(game);
        assertEquals(4, miniMaxStrategies.getBestMove());
    }

    private Game makeMoves(int[] moves) {
        Game game = new Game(3);
        for (int squareNumber : moves) {
            game.move(squareNumber);
        }
        return game;
    }
}
