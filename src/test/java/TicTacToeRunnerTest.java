import org.junit.Test;

import static org.junit.Assert.*;

public class TicTacToeRunnerTest {

    @Test
    public void configuresAndExecutesGame() {
        GameOptionsUIBuilder optionsBuilder = new GameOptionsUIBuilder("3\n1\n");
        PlayerUIBuilder playerBuilder = new PlayerUIBuilder("1\n4\n2\n5\n3\n");
        GameStatusUIBuilder gameStatusBuilder = new GameStatusUIBuilder("n\n");
        TicTacToeRunner runner = new TicTacToeRunner(
                optionsBuilder.getGameOptionsUI(),
                playerBuilder.getPlayerUI(),
                gameStatusBuilder.getGameStatusUI());
        runner.execute();
        assertEquals(PlayerSymbol.X, runner.getWinner());
        assertFalse(runner.isActive());
    }

    @Test
    public void startsNewGameOnUserInput() {
        GameOptionsUIBuilder optionsBuilder = new GameOptionsUIBuilder("3\n1\n");
        PlayerUIBuilder playerBuilder = new PlayerUIBuilder("1\n4\n2\n5\n3\n");
        GameStatusUIBuilder gameStatusBuilder = new GameStatusUIBuilder("y\n");
        TicTacToeRunner runner = new TicTacToeRunner(
                optionsBuilder.getGameOptionsUI(),
                playerBuilder.getPlayerUI(),
                gameStatusBuilder.getGameStatusUI());
        runner.execute();
        assertTrue(runner.isActive());
    }
}