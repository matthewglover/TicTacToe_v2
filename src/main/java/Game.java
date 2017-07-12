import java.util.List;
import java.util.stream.Collectors;

public class Game {
    private Board board;
    private Player currentPlayer = Player.X;
    private int lastMove;

    public Game(int boardSize) {
        buildBoard(boardSize);
    }

    public Board getBoard() {
        return board;
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

    public int getLastMove() {
        return lastMove;
    }

    public List<Game> getNextMoves() {
        return getEmptySquares()
                .stream()
                .map(this::getState)
                .collect(Collectors.toList());
    }

    public boolean isOver() {
        return isWinner() || board.isFull();
    }

    public boolean isWinner() {
        return !getWinner().isEmpty();
    }

    public void move(int squareNumber) {
        if (board.isEmptySquare(squareNumber)) {
            lastMove = squareNumber;
            board.setSquare(squareNumber, getCurrentPlayer());
            toggleCurrentPlayer();
        }
    }

    public Game duplicate() {
        Game duplicateGame = new Game(board.getSize());
        Board duplicateBoard = duplicateGame.getBoard();
        for (int i = Board.FIRST_SQUARE_NUMBER; i <= board.getTotalSquares(); i++) {
            duplicateBoard.setSquare(i, board.getSquare(i));
            duplicateGame.toggleCurrentPlayer();
        }
        return duplicateGame;
    }

    private Game getState(int squareNumber) {
        Game newGame = duplicate();
        newGame.move(squareNumber);
        return newGame;
    }

    private List<Integer> getEmptySquares() {
        return board.getEmptySquares();
    }

    private void toggleCurrentPlayer() {
        currentPlayer = (currentPlayer == Player.X) ? Player.O : Player.X;
    }

    private void buildBoard(int boardSize) {
        board = new Board(boardSize);
    }
}
