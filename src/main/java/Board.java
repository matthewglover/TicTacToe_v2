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

    private Stream<Stream<Player>> getRows() {
        Stream<Player> top = Stream.of(getSquare(1), getSquare(2), getSquare(3));
        Stream<Player> middle = Stream.of(getSquare(4), getSquare(5), getSquare(6));
        Stream<Player> bottom = Stream.of(getSquare(7), getSquare(8), getSquare(9));
        return Stream.of(top, middle, bottom);
    }

    private Stream<Stream<Player>>getColumns() {
        Stream<Player> left = Stream.of(getSquare(1), getSquare(4), getSquare(7));
        Stream<Player> middle = Stream.of(getSquare(2), getSquare(5), getSquare(8));
        Stream<Player> right = Stream.of(getSquare(3), getSquare(6), getSquare(9));
        return Stream.of(left, middle, right);
    }

    private Stream<Stream<Player>>getDiagonals() {
        Stream<Player> topLeft = Stream.of(getSquare(1), getSquare(5), getSquare(9));
        Stream<Player> topRight = Stream.of(getSquare(3), getSquare(5), getSquare(7));
        return Stream.of(topLeft, topRight);
    }

    public int[] test() {
        int totalSquares = gridSize * gridSize;
        UnaryOperator<Integer> rowStarter = number -> number + gridSize;
        return Stream
                .iterate(1, rowStarter)
                .limit(gridSize)
                .mapToInt(Integer::intValue)
                .toArray();
    }
}
