import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

import static java.util.Arrays.*;

public class Board {

    private int gridSize = 3;
    private Player[] grid;

    public Board() {
        grid = new Player[gridSize * gridSize];
        fill(grid, Player.NEITHER);
    }

    public Player getSquare(int squareNumber) {
        return grid[squareNumber - 1];
    }

    public void setSquare(int squareNumber, Player player) {
        grid[squareNumber - 1] = player;
    }

    public boolean isEmptySquare(int squareNumber) {
        return getSquare(squareNumber) == Player.NEITHER;
    }
    public boolean isFull() {
        return stream(grid).allMatch(square -> square != Player.NEITHER);
    }

    public boolean isAnyWinningLine(Player player) {
        return getLines().anyMatch(line -> isWinningLine(line, player));
    }

    private boolean isWinningLine(Stream<Player> line, Player player) {
        return line.allMatch(square -> square == player);
    }

    private Stream<Stream<Player>> getLines() {
        return Stream.concat(getRows(), Stream.concat(getColumns(), getDiagonals()));
    }

    public Stream<Stream<Player>> getRows() {
        Function<Integer, Stream<Player>> createRow =
                firstItem -> Stream.iterate(firstItem, d -> d + 1)
                                    .limit(gridSize)
                                    .map(this::getSquare);

        return Stream.iterate(1, d -> d + gridSize)
                .limit(gridSize)
                .map(createRow);
    }

    public Stream<Stream<Player>> getColumns() {
        Function<Integer, Stream<Player>> createColumn =
                firstItem -> Stream.iterate(firstItem, d -> d + gridSize)
                                    .map(this::getSquare)
                                    .limit(gridSize);

        return Stream.iterate(1, d -> d + 1)
                .limit(gridSize)
                .map(createColumn);
    }

    private Stream<Stream<Player>>getDiagonals() {
        Stream<Player> topLeft = Stream.of(getSquare(1), getSquare(5), getSquare(9));
        Stream<Player> topRight = Stream.of(getSquare(3), getSquare(5), getSquare(7));
        return Stream.of(topLeft, topRight);
    }

    public int[] test() {
        int totalSquares = gridSize * gridSize; UnaryOperator<Integer> rowStarter = number -> number + gridSize;
        return Stream
                .iterate(1, rowStarter)
                .limit(gridSize)
                .mapToInt(Integer::intValue)
                .toArray();
    }

    public Player[][] test2() {
        Function<Integer, Player[]> createRow =
                firstItem -> Stream.iterate(firstItem, d -> d + 1)
                                    .limit(3)
                                    .map(this::getSquare)
                                    .toArray(Player[]::new);

        return Stream.iterate(1, d -> d + 3)
                .limit(3)
                .map(createRow)
                .toArray(Player[][]::new);
    }
}
