import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.Arrays.*;

public class Board {

    private int size;
    private Player[] grid;
    public static final int MIN_SIZE = 3;
    public static final int MAX_SIZE = 4;
    public static final int FIRST_SQUARE_NUMBER = 1;

    public Board(int size) {
        setup(size);
    }

    public Player getSquare(int squareNumber) {
        return grid[squareNumber - 1];
    }

    public void setSquare(int squareNumber, Player player) {
        grid[squareNumber - 1] = player;
    }

    public boolean isEmptySquare(int squareNumber) {
        return getSquare(squareNumber).isEmpty();
    }

    public boolean isFull() {
        return stream(grid).allMatch(square -> !square.isEmpty());
    }

    public boolean isAnyWinningLine(Player player) {
        return getLines().anyMatch(line -> isWinningLine(line, player));
    }

    public int getSize() {
        return size;
    }

    public int getTotalSquares() {
        return size * size;
    }

    public Stream<Stream<Integer>> getRowsOfSquareNumbers() {
        return getGroupOfLinesOfSquareNumbers(size, 1);
    }

    private void setup(int size) {
        this.size = size;
        grid = new Player[getTotalSquares()];
        fill(grid, Player.NEITHER);
    }

    private boolean isWinningLine(Stream<Player> line, Player player) {
        return line.allMatch(square -> square == player);
    }

    private Stream<Stream<Player>> getLines() {
        return Stream.concat(getRows(), Stream.concat(getColumns(), getDiagonals()));
    }

    private Stream<Stream<Player>> getRows() {
        return getRowsOfSquareNumbers()
                .map(line -> line.map(this::getSquare));
    }

    private Stream<Stream<Player>> getColumns() {
        return getGroupOfLinesOfSquareNumbers(1, size)
                    .map(line -> line.map(this::getSquare));
    }

    private Stream<Stream<Player>>getDiagonals() {
        return Stream.of(getDiagonalTopLeft(), getDiagonalTopRight());
    }

    private Stream<Player> getDiagonalTopLeft() {
        return getDiagonal(1, size + 1);
    }

    private Stream<Player> getDiagonalTopRight() {
        return getDiagonal(size, size - 1);
    }

    private Stream<Player> getDiagonal(int startSquare, int squareIncrementer) {
        return getLineOfSquareNumbers(squareIncrementer).apply(startSquare).map(this::getSquare);
    }

    private Stream<Stream<Integer>> getGroupOfLinesOfSquareNumbers(int lineIncrementer, int squareIncrementer) {
        return Stream
                .iterate(1, d -> d + lineIncrementer)
                .limit(size)
                .map(getLineOfSquareNumbers(squareIncrementer));
    }

    private Function<Integer, Stream<Integer>> getLineOfSquareNumbers(int increment) {
        return firstItem -> Stream
                                .iterate(firstItem, d -> d + increment)
                                .limit(size);
    }
}
