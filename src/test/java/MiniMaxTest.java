import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

public class MiniMaxTest {
    @Test
    public void returnsLastSquareNumberWhenLastMove() {
        Game game = new Game(3);
        makeMoves(game, new int[]{1, 5, 3, 2, 8, 4, 6, 9});
        MiniMax miniMax = new MiniMax(game);
        assertEquals(7, miniMax.run());
    }

    @Test
    public void returnsWinningSquareNumberWhenTwoEmptySquares() {
        Game game = new Game(3);
        makeMoves(game, new int[]{2, 1, 5, 3, 6, 4, 9});
        MiniMax miniMax = new MiniMax(game);
        assertEquals(7, miniMax.run());
    }

    private void makeMoves(Game game, int[] moves) {
        for (int squareNumber : moves) {
            game.move(squareNumber);
        }
    }
}