import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class UnbeatableComputerTest {
    @Test
    public void selectsWinningMoveOverLosingMove() {
        Game game = makeMoves(new int[]{1, 2, 3, 5, 4, 6, 9});
        UnbeatableComputer unbeatableComputer = new UnbeatableComputer(game);
        unbeatableComputer.execute();
        assertEquals(8, unbeatableComputer.getMove());
    }

    private Game makeMoves(int[] moves) {
        Game game = new Game(3);
        for (int squareNumber : moves) {
            game.move(squareNumber);
        }
        return game;
    }
}
