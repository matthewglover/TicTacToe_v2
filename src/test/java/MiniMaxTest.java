import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class MiniMaxTest {

    @Test
    public void maximisingPlayerWinningGameScores10() {
        Game game = makeMoves(new int[]{1, 4, 2, 5, 3});
        assertEquals(10, runMiniMax(game, true));
    }

    @Test
    public void minimisingPlayerWinningGameScoresMinus10() {
        Game game = makeMoves(new int[]{1, 4, 2, 5, 3});
        assertEquals(-10, runMiniMax(game, false));
    }

    @Test
    public void drawnGameScores0() {
        Game game = makeMoves(new int[]{1, 4, 2, 5, 6, 3, 7, 8, 9});
        assertEquals(0, runMiniMax(game, false));
    }

    @Test
    public void maximisingPlayerWinInOneMoveScores9() {
        Game game = makeMoves(new int[]{1, 3, 2, 5, 7, 6, 9, 8});
        assertEquals(9, runMiniMax(game, false));
    }

    @Test
    public void minimisingPlayerWinInOneMoveScoresMinus9() {
        Game game = makeMoves(new int[]{1, 3, 2, 5, 7, 6, 9, 8});
        assertEquals(-9, runMiniMax(game, true));
    }

    @Test
    public void maximisingPlayerWinInTwoMovesScores8() {
        Game game = makeMoves(new int[]{1, 2, 7, 6, 9});
        assertEquals(8, runMiniMax(game, true));
    }

    @Test
    public void maximisingPlayerLosesOnAllNextMoves() {
        // x o 3
        // 4 5 o
        // x 8 x
        Game game = makeMoves(new int[]{1, 2, 7, 6, 9});
        assertEquals(-9, runMove(game, 3));
        assertEquals(-9, runMove(game, 5));
        assertEquals(-9, runMove(game, 4));
        assertEquals(-9, runMove(game, 8));
    }

    @Test
    public void maximisingPlayerWinsOnSomeNextMoves() {
        // x o x
        // 4 5 o
        // 7 8 x
        Game game = makeMoves(new int[]{1, 2, 3, 6, 9});
        assertEquals(-9, runMove(game, 4));
        assertEquals(8, runMove(game, 5));
        assertEquals(-9, runMove(game, 7));
        assertEquals(-9, runMove(game, 8));
    }

    @Test
    public void maximisingPlayerWinsOnTwoNextMove() {
        // x o 3
        // 4 x 6
        // 7 8 o
        Game game = makeMoves(new int[]{1, 2, 5, 9});
        assertEquals(0, runMove(game, 3));
        assertEquals(8, runMove(game, 4));
        assertEquals(0, runMove(game, 6));
        assertEquals(8, runMove(game, 7));
        assertEquals(0, runMove(game, 8));
    }

    private Game makeMoves(int[] moves) {
        Game game = new Game(3);
        for (int squareNumber : moves) {
            game.move(squareNumber);
        }
        return game;
    }

    private int runMove(Game game, int squareNumber) {
        Game duplicate = game.duplicate();
        duplicate.move(squareNumber);
        return runMiniMax(duplicate, true);
    }

    private int runMiniMax(Game game, boolean isMaximisingPlayer) {
        MiniMax miniMax = new MiniMax(game, 0, isMaximisingPlayer);
        miniMax.execute();
        return miniMax.getScore();
    }
}