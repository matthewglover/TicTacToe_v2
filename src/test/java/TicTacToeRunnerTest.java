import org.junit.Test;

import static org.junit.Assert.*;

public class TicTacToeRunnerTest {

    @Test
    public void requestsBoardSizeOnStartGame() {
        TestUI testUI = new TestUI(new int[]{ 1, 2, 3, 4, 5, 6, 7, 8, 9 });
        TicTacToeRunner ticTacToeRunner = new TicTacToeRunner(testUI);
        ticTacToeRunner.execute();
        assertEquals(1, testUI.promptForBoardSizeCalled);
    }

    @Test
    public void reportsWinner() {
        TestUI testUI = new TestUI(new int[]{ 1, 2, 3, 4, 5, 6, 7, 8, 9 });
        TicTacToeRunner ticTacToeRunner = new TicTacToeRunner(testUI);
        ticTacToeRunner.execute();
        assertEquals(1, testUI.reportWinnerCallCount);
    }

    @Test
    public void reportsDraw() {
        TestUI testUI = new TestUI(new int[]{ 1, 2, 3, 5, 4, 7, 8, 9, 6 });
        TicTacToeRunner ticTacToeRunner = new TicTacToeRunner(testUI);
        ticTacToeRunner.execute();
        assertEquals(1, testUI.reportDrawCallCount);
    }

    @Test
    public void promptsToPlayAgainOnGameCompletion() {
        TestUI testUI = new TestUI(new int[]{ 1, 2, 3, 4, 5, 6, 7 });
        TicTacToeRunner ticTacToeRunner = new TicTacToeRunner(testUI);
        ticTacToeRunner.execute();
        assertEquals(1, testUI.promptPlayAgainCount);
    }

    @Test
    public void playAgainStartsNewGame() {
        TestUI testUI = new TestUI(new int[]{ 1, 2, 3, 4, 5, 6, 7, 1, 2, 3, 4, 5, 6, 7 });
        testUI.setPlayAgain(true);
        TicTacToeRunner ticTacToeRunner = new TicTacToeRunner(testUI);
        ticTacToeRunner.execute();
        assertEquals(2, testUI.promptPlayAgainCount);
    }

    private class TestUI implements GameUI {
        private final int[] moves;
        private boolean playAgain;
        private int currentMove;
        public int promptForMoveCallCount;
        public int reportDrawCallCount;
        public int reportWinnerCallCount;
        public Player lastPlayerPromptedForMove;
        public int promptPlayAgainCount;
        public int promptForBoardSizeCalled;

        public TestUI(int[] moves) {
            this.moves = moves;
        }

        public int promptForMove(Player player, Board board) {
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

        public void setPlayAgain(boolean playAgain) {
            this.playAgain = playAgain;
        }
    }
}