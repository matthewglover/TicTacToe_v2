public class HumanPlayer extends Player {
    private PlayerUI playerUI;

    public HumanPlayer(PlayerUI playerUI, PlayerSymbol playerSymbol) {
        super(playerSymbol);
        this.playerUI = playerUI;
    }

    @Override
    protected void makeMove(Game game) {
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
