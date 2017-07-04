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

    public boolean isEmpty(int squareNumber) {
        return getSquare(squareNumber) == Player.NEITHER;
    }
    public boolean isFull() {
        return stream(grid).allMatch(square -> square != Player.NEITHER);
    }

    public boolean isAnyWinningLine(Player player) {
        return Stream.concat(getRows(), Stream.concat(getColumns(), getDiagonals()))
                .anyMatch(line -> isWinningLine(line, player));
    }

    private boolean isWinningLine(Player[] line, Player player) {
        return stream(line).allMatch(square -> square == player);
    }

    private Stream<Player[]> getRows() {
        Player[] top = { getSquare(1), getSquare(2), getSquare(3) };
        Player[] middle = { getSquare(4), getSquare(5), getSquare(6) };
        Player[] bottom = { getSquare(7), getSquare(8), getSquare(9) };
        Player[][] rows = { top, middle, bottom };
        return stream(rows);
    }

    private Stream<Player[]> getColumns() {
        Player[] left = { getSquare(1), getSquare(4), getSquare(7) };
        Player[] middle = { getSquare(2), getSquare(5), getSquare(8) };
        Player[] right = { getSquare(3), getSquare(6), getSquare(9) };
        Player[][] cols = { left, middle, right };
        return stream(cols);
    }

    private Stream<Player[]> getDiagonals() {
        Player[] topLeft = { getSquare(1), getSquare(5), getSquare(9) };
        Player[] topRight = { getSquare(3), getSquare(5), getSquare(7) };
        Player[][] diagonals = { topLeft, topRight };
        return stream(diagonals);
    }
}
