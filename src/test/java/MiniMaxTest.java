import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class MiniMaxTest {

    @Test
    public void maximisingPlayerWinningGameScores10() {
        Game game = makeMoves(new int[]{1, 4, 2, 5, 3});
        MiniMax miniMax = new MiniMax(game, 0, true);
        int result = miniMax.run();
        assertEquals(10, result);
    }

    @Test
    public void minimisingPlayerWinningGameScoresMinus10() {
        Game game = makeMoves(new int[]{1, 4, 2, 5, 3});
        MiniMax miniMax = new MiniMax(game, 0, false);
        int result = miniMax.run();
        assertEquals(-10, result);
    }

    @Test
    public void drawnGameScores0() {
        Game game = makeMoves(new int[]{1, 4, 2, 5, 6, 3, 7, 8, 9});
        MiniMax miniMax = new MiniMax(game, 0, false);
        int result = miniMax.run();
        assertEquals(0, result);
    }

    @Test
    public void maximisingPlayerWinInOneMoveScores9() {
        Game game = makeMoves(new int[]{1, 3, 2, 5, 7, 6, 9, 8});
        MiniMax miniMax = new MiniMax(game, 0, false);
        int result = miniMax.run();
        assertEquals(9, result);
    }

    @Test
    public void minimisingPlayerWinInOneMoveScoresMinus9() {
        Game game = makeMoves(new int[]{1, 3, 2, 5, 7, 6, 9, 8});
        MiniMax miniMax = new MiniMax(game, 0, true);
        int result = miniMax.run();
        assertEquals(-9, result);
    }

    private Game makeMoves(int[] moves) {
        Game game = new Game(3);
        for (int squareNumber : moves) {
            game.move(squareNumber);
        }
        return game;
    }
}