import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BoardFormatter {
    private Board board;

    public BoardFormatter(Board board) {
        this.board = board;
    }

    public String format() {
        return board
                .getRowsOfSquareNumbers()
                .map(rowOfSquareNumbers -> formatRow(rowOfSquareNumbers))
                .collect(Collectors.joining("\n" + formatRowDivider() + "\n"));
    }

    private String formatRow(Stream<Integer> rowOfSquareNumbers) {
        return rowOfSquareNumbers
                .map(squareNumber -> formatSquare(squareNumber))
                .collect(Collectors.joining("|"));
    }

    private String formatSquare(Integer squareNumber) {
        Player square = board.getSquare(squareNumber);
        String squareString = getSquareString(square, squareNumber);
        String leftPad = getLeftPad(squareString);
        return String.format(" %s ", leftPad + squareString);
    }

    private String getSquareString(Player square, int squareNumber) {
        return (square.isEmpty()) ? Integer.toString(squareNumber) : square.toString();
    }

    private String getLeftPad(String squareString) {
        return (board.getTotalSquares() > 9 && squareString.length() == 1) ? " " : "";
    }

    private String formatRowDivider() {
        String squareDivider = (board.getTotalSquares() < 10) ? "---" : "----";
        return String.join(" ", Collections.nCopies(board.getSize(), squareDivider));
    }
}
