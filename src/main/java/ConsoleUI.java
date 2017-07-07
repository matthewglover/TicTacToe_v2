import java.io.InputStream;
import java.io.PrintStream;
import java.util.Collections;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ConsoleUI implements GameUI {

    private PrintStream output;
    private Scanner scanner;

    public ConsoleUI(InputStream in, PrintStream output) {
        this.output = output;
        this.scanner = new Scanner(in);
    }

    public int promptForMove(Player player, BoardReader board) {
        output.println(formatBoard(board));
        output.print(formatPlayerPrompt(player));
        return getBoardMove(board);
    }

    public void reportDraw() {
        output.print("It's a draw!");
    }

    public void reportWinner(Player player) {
        output.print(String.format("%s Wins!!", player.toString()));
    }

    public boolean promptPlayAgain() {
        output.print("Type <N> for a new game or any other key to exit: ");
        return getPlayAgain();
    }

    private int getBoardMove(BoardReader board) {
        String input = scanner.nextLine();
        if (isValidBoardMove(input, board)) {
            return Integer.parseInt(input);
        }
        output.print("\nOops, invalid input. Try again: ");
        return getBoardMove(board);
    }

    private boolean getPlayAgain() {
        String input = scanner.nextLine();
        return input.equals("N");
    }

    private boolean isValidBoardMove(String input, BoardReader board) {
        if (!input.matches("^\\d+$")) {
            return false;
        }
        int move = Integer.parseInt(input);
        return isMoveInBounds(move, board) && board.isEmptySquare(move);
    }

    private boolean isMoveInBounds(int move, BoardReader board) {
        return move >= 1 && move <= board.getTotalSquares();
    }

    private String formatPlayerPrompt(Player player) {
        return String.format("Player %s: ", player.toString());
    }

    private String formatBoard(BoardReader board) {
        return board
                .getRowsOfSquareNumbers()
                .map(rowOfSquareNumbers -> formatRow(rowOfSquareNumbers, board))
                .collect(Collectors.joining("\n" + formatRowDivider(board) + "\n"));
    }

    private String formatRow(Stream<Integer> rowOfSquareNumbers, BoardReader board) {
        return rowOfSquareNumbers
                .map(squareNumber -> formatSquare(squareNumber, board))
                .collect(Collectors.joining("|"));
    }

    private String formatSquare(Integer squareNumber, BoardReader board) {
        Player square = board.getSquare(squareNumber);
        String squareString = (square.isEmpty()) ? squareNumber.toString() : square.toString();
        return String.format(" %s ", squareString);
    }

    private String formatRowDivider(BoardReader board) {
        return String.join(" ", Collections.nCopies(board.getGridSize(), "---"));
    }
}
