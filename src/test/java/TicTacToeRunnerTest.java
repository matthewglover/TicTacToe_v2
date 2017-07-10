import org.junit.Test;

import static org.junit.Assert.*;

public class TicTacToeRunnerTest {
    TicTacToeRunner ticTacToeRunner;
    TestUI testUI;

    @Test
    public void requestsBoardSizeOnStartGame() {
        createAppRunner(new int[]{ 1, 2, 3, 4, 5, 6, 7, 8, 9 });
        assertEquals(1, testUI.promptForBoardSizeCalled);
    }

    @Test
    public void reportsWinner() {
        createAppRunner(new int[]{ 1, 2, 3, 4, 5, 6, 7, 8, 9 });
        assertEquals(1, testUI.reportWinnerCallCount);
    }

    @Test
    public void reportsDraw() {
        createAppRunner(new int[]{ 1, 2, 3, 5, 4, 7, 8, 9, 6 });
        assertEquals(1, testUI.reportDrawCallCount);
    }

    @Test
    public void promptsToPlayAgainOnGameCompletion() {
        createAppRunner(new int[]{ 1, 2, 3, 4, 5, 6, 7 });
        assertEquals(1, testUI.promptPlayAgainCount);
    }

    @Test
    public void playAgainStartsNewGame() {
        createAppRunner(new int[]{ 1, 2, 3, 4, 5, 6, 7, 1, 2, 3, 4, 5, 6, 7 }, true);
        assertEquals(2, testUI.promptPlayAgainCount);
    }

    private void createAppRunner(int[] moves) {
        createAppRunner(moves, false);
    }

    private void createAppRunner(int[] moves, boolean playAgain) {
        testUI = new TestUI(moves, playAgain);
        ticTacToeRunner = new TicTacToeRunner(testUI);
    }

    private class TestUI implements GameUI {
        private final int[] moves;
        private final boolean playAgain;
        private int currentMove;
        public int promptForMoveCallCount;
        public int reportDrawCallCount;
        public int reportWinnerCallCount;
        public Player lastPlayerPromptedForMove;
        public int promptPlayAgainCount;
        public int promptForBoardSizeCalled;

        public TestUI(int[] moves, boolean playAgain) {
            this.moves = moves;
            this.playAgain = playAgain;
        }
        public int promptForMove(Player player, BoardReader board) {
            promptForMoveCallCount += 1;
            lastPlayerPromptedForMove = player;
            return moves[currentMove++];
        }

        public void reportDraw() {
            reportDrawCallCount = 1;
        }

        public void reportWinner(Player player) {
            reportWinnerCallCount = 1;
        }

        public boolean promptPlayAgain() {
            promptPlayAgainCount++;
            return playAgain && promptPlayAgainCount < 2;
        }

        public int promptForBoardSize(int minBoardSize, int maxBoardSize) {
            promptForBoardSizeCalled++;
            return 3;
        }
    }
}