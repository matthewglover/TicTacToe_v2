import java.util.function.Function;
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

    public boolean isWinningLine(Player player) {
        return getLines().anyMatch(line -> isCurrentWinningLine(line, player));
    }

    private boolean isCurrentWinningLine(Stream<Player> line, Player player) {
        return line.allMatch(square -> square == player);
    }

    private Stream<Stream<Player>> getLines() {
        return Stream.concat(getRows(), Stream.concat(getColumns(), getDiagonals()));
    }

    public Stream<Stream<Player>> getRows() {
        return Stream.iterate(1, d -> d + gridSize)
                .map(createLine(1))
                .limit(gridSize);
    }

    public Stream<Stream<Player>> getColumns() {
        return Stream.iterate(1, d -> d + 1)
                .map(createLine(gridSize))
                .limit(gridSize);
    }

    private Stream<Stream<Player>>getDiagonals() {
        Stream<Player> topLeft = Stream.of(getSquare(1), getSquare(5), getSquare(9));
        Stream<Player> topRight = Stream.of(getSquare(3), getSquare(5), getSquare(7));
        return Stream.of(topLeft, topRight);
    }

    private Function<Integer, Stream<Player>> createLine(int increment) {
        return firstItem -> Stream.iterate(firstItem, d -> d + increment)
                .map(this::getSquare)
                .limit(gridSize);
    }
}
