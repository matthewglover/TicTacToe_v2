public class TicTacToeRunner {
    private GameUI UI;
    private Game game;

    public static void main(String[] args) {
        GameUI UI = new ConsoleUI(System.in, System.out);
        new TicTacToeRunner(UI);
    }

    public TicTacToeRunner(GameUI UI) {
       this.UI = UI;
       promptForGameOptions();
    }

    private void promptForGameOptions() {
        int boardSize = UI.promptForBoardSize(Board.MIN_SIZE, Board.MAX_BOARD_SIZE);
        startNewGame(boardSize);
    }

    private void promptForNextMove() {
        int squareNumber = UI.promptForMove(game.getCurrentPlayer(), game.getBoard());
        game.move(squareNumber);

        if (game.isOver()) {
            reportGameResult();
            checkPlayAgain();
        }
        else {
            promptForNextMove();
        }
    }

    private void checkPlayAgain() {
        if (UI.promptPlayAgain()) {
            promptForGameOptions();
        }
    }

    private void startNewGame(int boardSize) {
        game = new Game(boardSize);
        promptForNextMove();
    }

    private void reportGameResult() {
        if (game.isWinner()) {
            UI.reportWinner(game.getWinner());
        } else {
            UI.reportDraw();
        }
    }
}
