import org.junit.Test;

import static org.junit.Assert.*;

public class TicTacToeRunnerTest {

    @Test
    public void configuresAndExecutesGame() {
        GameOptionsUIBuilder optionsBuilder = new GameOptionsUIBuilder("3\n1\n");
        PlayerUIBuilder playerBuilder = new PlayerUIBuilder("1\n4\n2\n5\n3\n");
        TicTacToeRunner runner = new TicTacToeRunner(
                optionsBuilder.getGameOptionsUI(),
                playerBuilder.getPlayerUI());
        runner.execute();
        assertEquals(PlayerSymbol.X, runner.getWinner());
    }
}