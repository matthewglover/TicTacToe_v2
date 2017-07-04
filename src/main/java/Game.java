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
        board.setSquareValue(position, getCurrentPlayer());
        togglePlayer();
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    private void togglePlayer() {
        currentPlayer = (currentPlayer == Player.X) ? Player.O : Player.X;
    }

    public Player getWinner() {
        if (board.isAnyWinningLine(Player.X)) {
            return Player.X;
        }
        return Player.NEITHER;
    }

    public boolean isWinner() {
        return getWinner() != Player.NEITHER;
    }

    public boolean isGameOver() {
        return false;
    }
}
