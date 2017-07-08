import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.Arrays.*;

public class Board implements BoardReader {

    private int gridSize;
    private Player[] grid;

    public Board() {
        setup(3);
    }

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

    public int getGridSize() {
        return gridSize;
    }

    public int getTotalSquares() {
        return gridSize * gridSize;
    }

    public Stream<Stream<Integer>> getRowsOfSquareNumbers() {
        return getGroupOfLinesOfSquareNumbers(gridSize, 1);
    }

    private void setup(int size) {
        gridSize = size;
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
        return getGroupOfLinesOfSquareNumbers(1, gridSize)
                    .map(line -> line.map(this::getSquare));
    }

    private Stream<Stream<Player>>getDiagonals() {
        return Stream.of(getDiagonalTopLeft(), getDiagonalTopRight());
    }

    private Stream<Player> getDiagonalTopLeft() {
        return getDiagonal(1, gridSize + 1);
    }

    private Stream<Player> getDiagonalTopRight() {
        return getDiagonal(gridSize, gridSize - 1);
    }

    private Stream<Player> getDiagonal(int startSquare, int squareIncrementer) {
        return getLineOfSquareNumbers(squareIncrementer).apply(startSquare).map(this::getSquare);
    }

    private Stream<Stream<Integer>> getGroupOfLinesOfSquareNumbers(int lineIncrementer, int squareIncrementer) {
        return Stream
                .iterate(1, d -> d + lineIncrementer)
                .limit(gridSize)
                .map(getLineOfSquareNumbers(squareIncrementer));
    }

    private Function<Integer, Stream<Integer>> getLineOfSquareNumbers(int increment) {
        return firstItem -> Stream
                                .iterate(firstItem, d -> d + increment)
                                .limit(gridSize);
    }
}
