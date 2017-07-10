import java.util.stream.Stream;

public interface BoardReader {
    Player getSquare(int squareNumber);
    boolean isEmptySquare(int squareNumber);
    boolean isFull();
    boolean isAnyWinningLine(Player player);
    int getSize();
    int getTotalSquares();
    Stream<Stream<Integer>> getRowsOfSquareNumbers();
    int FIRST_SQUARE_NUMBER = 1;
    int MIN_SIZE = 3;
    int MAX_BOARD_SIZE = 4;
}
