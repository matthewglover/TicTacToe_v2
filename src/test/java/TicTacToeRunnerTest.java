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
                playerBuilder.getHumanPlayerUI(),
                gameStatusBuilder.getGameStatusUI());
        runner.execute();
        assertEquals(PlayerSymbol.X, runner.getWinner());
        assertEquals(1, runner.gameCount());
    }

    @Test
    public void startsNewGameOnUserInput() {
        GameOptionsUIBuilder optionsBuilder = new GameOptionsUIBuilder("3\n1\n3\n1\n");
        PlayerUIBuilder playerBuilder = new PlayerUIBuilder("1\n4\n2\n5\n3\n1\n4\n2\n5\n3\n");
        GameStatusUIBuilder gameStatusBuilder = new GameStatusUIBuilder("y\nn\n");
        TicTacToeRunner runner = new TicTacToeRunner(
                optionsBuilder.getGameOptionsUI(),
                playerBuilder.getHumanPlayerUI(),
                gameStatusBuilder.getGameStatusUI());
        runner.execute();
        assertEquals(2, runner.gameCount());
    }

    @Test
    public void humanComputerMakesPlayer2AComputer() {
        GameOptionsUIBuilder optionsBuilder = new GameOptionsUIBuilder("3\n2\n");
        PlayerUIBuilder playerBuilder = new PlayerUIBuilder("1\n2\n3\n4\n5\n6\n7\n8\n9\n");
        GameStatusUIBuilder gameStatusBuilder = new GameStatusUIBuilder("n\n");
        TicTacToeRunner runner = new TicTacToeRunner(
                optionsBuilder.getGameOptionsUI(),
                playerBuilder.getHumanPlayerUI(),
                gameStatusBuilder.getGameStatusUI());
        runner.execute();
        assertTrue(runner.getPlayerX() instanceof HumanPlayer);
        assertTrue(runner.getPlayerO() instanceof ComputerPlayer);
    }

    @Test
    public void computerComputerMakesBothPlayersComputer() {
        GameOptionsUIBuilder optionsBuilder = new GameOptionsUIBuilder("3\n3\n");
        PlayerUIBuilder playerBuilder = new PlayerUIBuilder("");
        GameStatusUIBuilder gameStatusBuilder = new GameStatusUIBuilder("n\n");
        TicTacToeRunner runner = new TicTacToeRunner(
                optionsBuilder.getGameOptionsUI(),
                playerBuilder.getHumanPlayerUI(),
                gameStatusBuilder.getGameStatusUI());
        runner.execute();
        assertTrue(runner.getPlayerX() instanceof ComputerPlayer);
        assertTrue(runner.getPlayerO() instanceof ComputerPlayer);
    }
}