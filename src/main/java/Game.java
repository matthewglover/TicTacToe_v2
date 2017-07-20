import java.util.List;
import java.util.Observable;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Game extends Observable {
    private Board board;
    private PlayerSymbol nextPlayerSymbol = PlayerSymbol.X;
    private int currentMove;

    public Game(int boardSize) {
        buildBoard(boardSize);
    }

    public Board getBoard() {
        return board;
    }

    public PlayerSymbol getNextPlayerSymbol() {
        return nextPlayerSymbol;
    }

    public PlayerSymbol getWinner() {
        if (board.isAnyWinningLine(PlayerSymbol.X)) {
            return PlayerSymbol.X;
        } else if (board.isAnyWinningLine(PlayerSymbol.O)) {
            return PlayerSymbol.O;
        }
        return PlayerSymbol.NEITHER;
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

    public void start() {
        notifyChange();
    }

    public void move(int squareNumber) {
        if (board.isEmptySquare(squareNumber)) {
            currentMove = squareNumber;
            board.setSquare(squareNumber, getNextPlayerSymbol());
            toggleNextPlayer();
            notifyChange();
        }
    }

    public Game duplicate() {
        Game duplicateGame = new Game(board.getSize());
        Board duplicateBoard = duplicateGame.getBoard();
        for (int i = Board.FIRST_SQUARE_NUMBER; i <= board.getTotalSquares(); i++) {
            PlayerSymbol currentSquare = board.getSquare(i);
            if (!currentSquare.isEmpty()) {
                duplicateBoard.setSquare(i, currentSquare);
            }
        }
        duplicateGame.nextPlayerSymbol = nextPlayerSymbol;
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

    private void notifyChange() {
        setChanged();
        notifyObservers(getNextPlayerSymbol());
    }

    private void toggleNextPlayer() {
        nextPlayerSymbol = (nextPlayerSymbol == PlayerSymbol.X) ? PlayerSymbol.O : PlayerSymbol.X;
    }

    private void buildBoard(int boardSize) {
        board = new Board(boardSize);
    }
}
