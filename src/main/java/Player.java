import java.util.Observable;
import java.util.Observer;

public class Player implements Observer {
    private final PlayerSymbol playerSymbol;
    private PlayerUI playerUI;

    public Player(PlayerUI playerUI, PlayerSymbol playerSymbol) {
        this.playerUI = playerUI;
        this.playerSymbol = playerSymbol;
    }

    @Override
    public void update(Observable o, Object arg) {
        Game game = (Game) o;
        PlayerSymbol playerSymbol = (PlayerSymbol) arg;

        if (game.isOver()) {
            return;
        }

        if (playerSymbol == this.playerSymbol) {
            makeMove(game);
        }
    }

    private void makeMove(Game game) {
        Board board = game.getBoard();
        printBoard(board);
        printMoveRequest();
        game.move(obtainMove(board));
    }

    public void printBoard(Board board) {
        playerUI.printBoard(board);
    }

    public void printMoveRequest() {
        playerUI.printMoveRequest(playerSymbol);
    }

    public int obtainMove(Board board) {
        int move = playerUI.promptForMove();
        if (isValidMove(move, board)) {
            return move;
        } else {
            playerUI.printInvalidInput();
            return obtainMove(board);
        }
    }

    private boolean isValidMove(int move, Board board) {
        return isMoveInBounds(move, board) && isMoveSquareEmpty(move, board);
    }

    private boolean isMoveInBounds(int move, Board board) {
        return move >= board.FIRST_SQUARE_NUMBER &&
                move <= board.getTotalSquares();
    }

    private boolean isMoveSquareEmpty(int move, Board board) {
        return board.isEmptySquare(move);
    }
}
