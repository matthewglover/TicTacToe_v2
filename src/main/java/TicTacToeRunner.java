public class TicTacToeRunner {
    private GameUI UI;
    private Game game;

    public static void main(String[] args) {
        GameUI UI = new ConsoleUI(System.in, System.out);
        new TicTacToeRunner(UI);
    }

    public TicTacToeRunner(GameUI UI) {
       this.UI = UI;
       game = new Game();
       promptForNextMove();
    }

    private void promptForNextMove() {
        int squareNumber = UI.promptForMove(game.getCurrentPlayer(), game.getBoard());
        game.move(squareNumber);

        if (game.isOver()) {
            UI.reportWinner(game.getWinner());
        }
        else {
            promptForNextMove();
        }
    }
}
