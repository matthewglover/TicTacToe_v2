public class TicTacToeRunner {
    private GameUI UI;
    private Game game;

    public static void main(String[] args) {
        GameUI UI = new ConsoleUI(System.in, System.out);
        TicTacToeRunner ticTacToeRunner = new TicTacToeRunner(UI);
        ticTacToeRunner.execute();
    }

    public TicTacToeRunner(GameUI UI) {
        this.UI = UI;
    }

    public void execute() {
        int boardSize = promptForBoardSize();
        startNewGame(boardSize);
        runGame();
        reportGameResult();
        if (checkPlayAgain()) {
            execute();
        }
        ;
    }

    private int promptForBoardSize() {
        return UI.promptForBoardSize(Board.MIN_SIZE, Board.MAX_SIZE);
    }

    private void startNewGame(int boardSize) {
        game = new Game(boardSize);
    }

    private void runGame() {
        int squareNumber = UI.promptForMove(game.getNextPlayer(), game.getBoard());
        game.move(squareNumber);

        if (!game.isOver()) {
            runGame();
        }
    }

    private void reportGameResult() {
        if (game.isWinner()) {
            UI.reportWinner(game.getWinner());
        } else {
            UI.reportDraw();
        }
    }

    private boolean checkPlayAgain() {
        return UI.promptPlayAgain();
    }
}
