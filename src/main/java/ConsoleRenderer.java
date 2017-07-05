import java.io.PrintStream;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ConsoleRenderer {

    private PrintStream output;

    public ConsoleRenderer(PrintStream output) {
        this.output = output;
    }

    public void promptForMove(Player player, Board board) {
        output.println(formatBoard(board));
        output.print(formatPlayerPrompt(player));
    }

    public void reportDraw() {
        output.print("It's a draw!");
    }

    public void reportWinner(Player player) {
        output.print(String.format("%s Wins!!", player.toString()));
    }

    public void reportMoveError() {
        output.print("Oops, that square is already taken. Try again: ");
    }

    private String formatPlayerPrompt(Player player) {
        return String.format("Player %s: ", player.toString());
    }

    private String formatBoard(Board board) {
        int gridSize = board.getGridSize();
        return Stream.iterate(1, d -> d + gridSize)
                    .limit(gridSize)
                    .map(rowStartNumber -> formatRow(rowStartNumber, board))
                    .collect(Collectors.joining("\n" + formatRowDivider(board) + "\n"));
    }

    private String formatRow(Integer rowStartNumber, Board board) {
        return Stream.iterate(rowStartNumber, d -> d + 1)
                    .limit(board.getGridSize())
                    .map(squareNumber -> formatSquare(squareNumber, board))
                    .collect(Collectors.joining("|"));
    }

    private String formatSquare(Integer squareNumber, Board board) {
        Player square = board.getSquare(squareNumber);
        String squareString = (square.isEmpty()) ? squareNumber.toString() : square.toString();
        return String.format(" %s ", squareString);
    }

    private String formatRowDivider(Board board) {
        return String.join(" ", Collections.nCopies(board.getGridSize(), "---"));
    }
}
