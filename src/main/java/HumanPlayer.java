import com.mattheglover.tictactoe.core.Board;
import com.mattheglover.tictactoe.core.Game;
import com.mattheglover.tictactoe.core.PlayerSymbol;

public class HumanPlayer extends Player {
    private HumanPlayerUI humanPlayerUI;

    public HumanPlayer(HumanPlayerUI humanPlayerUI, PlayerSymbol playerSymbol) {
        super(playerSymbol);
        this.humanPlayerUI = humanPlayerUI;
    }

    @Override
    protected void makeMove(Game game) {
        Board board = game.getBoard();
        humanPlayerUI.clearScreen();
        printBoard(board);
        printMoveRequest();
        game.move(obtainMove(board));
    }

    public void printBoard(Board board) {
        humanPlayerUI.printBoard(board);
    }

    public void printMoveRequest() {
        humanPlayerUI.printMoveRequest(playerSymbol);
    }

    public int obtainMove(Board board) {
        int move = humanPlayerUI.promptForMove();
        if (isValidMove(move, board)) {
            return move;
        } else {
            humanPlayerUI.printInvalidInput();
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
