public class Game {
    private Board board = new Board();
    private Player currentPlayer = Player.X;

    public Board getBoard() {
        return board;
    }

    public void move(int position) {
        if (!board.isEmptySquare(position)) {
            return;
        }
        board.setSquare(position, getCurrentPlayer());
        toggleCurrentPlayer();
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    private void toggleCurrentPlayer() {
        currentPlayer = (currentPlayer == Player.X) ? Player.O : Player.X;
    }

    public Player getWinner() {
        if (board.isWinningLine(Player.X)) {
            return Player.X;
        } else if (board.isWinningLine(Player.O)) {
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
}
