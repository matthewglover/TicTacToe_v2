import com.mattheglover.tictactoe.core.Board;
import com.mattheglover.tictactoe.core.PlayerSymbol;

import java.util.Collections;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BoardFormatter {
    private final Board board;

    public BoardFormatter(Board board) {
        this.board = board;
    }

    public String format() {
        return board
                .getRowsOfSquareNumbers()
                .map(formatRow())
                .collect(Collectors.joining("\n" + formatRowDivider() + "\n"));
    }

    private Function<Stream<Integer>, String> formatRow() {
        return (Stream<Integer> rowOfSquareNumbers) ->
                rowOfSquareNumbers
                        .map(formatSquare())
                        .collect(Collectors.joining("|"));
    }

    private Function<Integer, String> formatSquare() {
        return (Integer squareNumber) -> {
            String squareString = getSquareString(board.getSquare(squareNumber), squareNumber);
            return String.format(" %s ", getLeftPad(squareString) + squareString);
        };
    }

    private String getSquareString(PlayerSymbol square, int squareNumber) {
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
