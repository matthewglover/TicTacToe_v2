import java.util.List;
import java.util.function.Function;
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
                .map(makeMove())
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
            Player crntSquare = board.getSquare(i);
            if (!crntSquare.isEmpty()) {
                duplicateBoard.setSquare(i, crntSquare);
            }
        }
        duplicateGame.currentPlayer = currentPlayer;
        duplicateGame.lastMove = lastMove;
        return duplicateGame;
    }

    private List<Integer> getEmptySquares() {
        return board.getEmptySquares();
    }

    private Function<Integer, Game> makeMove() {
        return (Integer squareNumber) -> {
            Game newGame = duplicate();
            newGame.move(squareNumber);
            return newGame;
        };
    }

    private void toggleCurrentPlayer() {
        currentPlayer = (currentPlayer == Player.X) ? Player.O : Player.X;
    }

    private void buildBoard(int boardSize) {
        board = new Board(boardSize);
    }
}
