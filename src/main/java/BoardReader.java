import java.util.stream.Stream;

public interface BoardReader {
    Player getSquare(int squareNumber);
    boolean isEmptySquare(int squareNumber);
    boolean isFull();
    boolean isAnyWinningLine(Player player);
    int getSize();
    int getTotalSquares();
    Stream<Stream<Integer>> getRowsOfSquareNumbers();
}
