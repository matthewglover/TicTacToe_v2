import org.junit.Test;

import static org.junit.Assert.*;

public class TicTacToeRunnerTest {
    TicTacToeRunner ticTacToeRunner;
    TestUI testUI;

    @Test
    public void runsFirstGameAndReportsWinner() {
        createAppRunner(new int[]{ 1, 2, 3, 4, 5, 6, 7, 8, 9 });
        assertEquals(1, testUI.reportWinnerCallCount);
    }



    private void createAppRunner(int[] moves) {
        testUI = new TestUI(moves);
        ticTacToeRunner = new TicTacToeRunner(testUI);
    }

    private class TestUI implements GameUI {
        private final int[] moves;
        private int crntMove;
        public int promptForMoveCallCount = 0;
        public int reportDrawCallCount = 0;
        public int reportMoveErrorCallCount = 0;
        public int reportWinnerCallCount = 0;
        public Player lastPlayerPromptedForMove;

        public TestUI(int[] moves) {
            this.moves = moves;
        }
        public int promptForMove(Player player, BoardReader board) {
            promptForMoveCallCount += 1;
            lastPlayerPromptedForMove = player;
            return moves[crntMove++];
        }

        public void reportDraw() {
            reportDrawCallCount = 1;
        }

        public void reportMoveError() {
            reportMoveErrorCallCount = 1;
        }

        public void reportWinner(Player player) {
            reportWinnerCallCount = 1;
        }
    }
}