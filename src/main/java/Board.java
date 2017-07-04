import java.util.Arrays;

public class Board {

    private Player[][] grid;
    private int gridSize = 3;

    public Board() {
        grid = new Player[gridSize][gridSize];
        for (Player[] row : grid) {
            Arrays.fill(row, Player.NEITHER);
        }
    }

    public Player getSquareValue(int row, int col) {
        return grid[row][col];
    }

    public Player getSquareValue(int position) {
        BoardRef boardRef = convertPosition(position);
        return getSquareValue(boardRef.row, boardRef.col);
    }

    public void setSquareValue(int row, int col, Player player) {
        grid[row][col] = player;
    }

    public void setSquareValue(int position, Player player) {
        BoardRef boardRef = convertPosition(position);
        setSquareValue(boardRef.row, boardRef.col, player);
    }

    public boolean isEmptySquare(int row, int col) {
        return squareMatches(row, col, Player.NEITHER);
    }

    public boolean isEmptySquare(int position) {
        BoardRef boardRef = convertPosition(position);
        return isEmptySquare(boardRef.row, boardRef.col);
    }

    public boolean isFull() {
        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
               if (isEmptySquare(row, col)) {
                   return false;
               }
            }
        }
        return true;
    }

    public boolean isAnyWinningLine(Player player) {
        return isAnyWinningRow(player) || isAnyWinningColumn(player) || isAnyWinningDiagonal(player);
    }

    public boolean isAnyWinningRow(Player player) {
        for (int row = 0; row < gridSize; row++) {
           if (isWinningRow(row, player)) {
               return true;
           }
        }
        return false;
    }

    public boolean isAnyWinningColumn(Player player) {
        for (int col = 0; col < gridSize; col++) {
            if (isWinningColumn(col, player)) {
                return true;
            }
        }
        return false;
    }

    public boolean isAnyWinningDiagonal(Player player) {
        return isWinningDiagonalFromTopLeft(player) || isWinningDiagonalFromTopRight(player);
    }

    public int getTotalSquares() {
        return gridSize * gridSize;
    }

    public BoardRef convertPosition(int position) {
        int offsetPosition = position - 1;
        int row = offsetPosition / gridSize;
        int col = offsetPosition % gridSize;
        return new BoardRef(row, col);
    }

    public int convertBoardRef(int row, int col) {
        int rowOffset = row * gridSize;
        int colOffset = col + 1;
        return rowOffset + colOffset;
    }

    private boolean isWinningRow(int row, Player player) {
        for (int col = 0; col < gridSize; col++) {
            if (!squareMatches(row, col, player)) {
                return false;
            }
        }
        return true;
    }

    private boolean isWinningColumn(int col, Player player) {
        for (int row = 0; row < gridSize; row++) {
            if (!squareMatches(row, col, player)) {
                return false;
            }
        }
        return true;
    }

    private boolean isWinningDiagonalFromTopLeft(Player player) {
        for (int coord = 0; coord < gridSize; coord++) {
            if (!squareMatches(coord, coord, player)) {
                return false;
            }
        }
        return true;
    }

    private boolean isWinningDiagonalFromTopRight(Player player) {
        int maxIndex = gridSize - 1;
        for (int row = 0; row < gridSize; row++) {
            if (!squareMatches(row, maxIndex - row, player)) {
                return false;
            }
        }
        return true;
    }

    private boolean squareMatches(int row, int col, Player player) {
        return getSquareValue(row, col) == player;
    }
}
