import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Game {
    private Board board;
    private Player nextPlayer = Player.X;
    private int currentMove;

    public Game(int boardSize) {
        buildBoard(boardSize);
    }

    public Board getBoard() {
        return board;
    }

    public Player getNextPlayer() {
        return nextPlayer;
    }

    public Player getWinner() {
        if (board.isAnyWinningLine(Player.X)) {
            return Player.X;
        } else if (board.isAnyWinningLine(Player.O)) {
            return Player.O;
        }
        return Player.NEITHER;
    }

    public int getCurrentMove() {
        return currentMove;
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
            currentMove = squareNumber;
            board.setSquare(squareNumber, getNextPlayer());
            toggleNextPlayer();
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
        duplicateGame.nextPlayer = nextPlayer;
        duplicateGame.currentMove = currentMove;
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

    private void toggleNextPlayer() {
        nextPlayer = (nextPlayer == Player.X) ? Player.O : Player.X;
    }

    private void buildBoard(int boardSize) {
        board = new Board(boardSize);
    }
}
