public class Game {
    private Board board;
    private Player currentPlayer = Player.X;

    public Game() {
        buildBoard(Board.MIN_SIZE);
    }

    public Game(int boardSize) {
        buildBoard(boardSize);
    }

    public BoardReader getBoard() {
        return board;
    }

    public void move(int squareNumber) {
        if (board.isEmptySquare(squareNumber)) {
            board.setSquare(squareNumber, getCurrentPlayer());
            toggleCurrentPlayer();
        }
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Player getWinner() {
        if (board.isAnyWinningLine(Player.X)) {
            return Player.X;
        } else if (board.isAnyWinningLine(Player.O)) {
            return Player.O;
        }
        return Player.NEITHER;
    }

    public boolean isWinner() {
        return !getWinner().isEmpty();
    }

    public boolean isOver() {
        return isWinner() || board.isFull();
    }

    private void toggleCurrentPlayer() {
        currentPlayer = (currentPlayer == Player.X) ? Player.O : Player.X;
    }

    private void buildBoard(int boardSize) {
        board = new Board(boardSize);
    }
}
