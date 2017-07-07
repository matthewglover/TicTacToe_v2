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

    public int promptForMove(Player player, Board board) {
        output.println(formatBoard(board));
        output.print(formatPlayerPrompt(player));
        return getBoardMove(board);
    }

    private int getBoardMove(Board board) {
        String input = scanner.nextLine();
        if (isValidBoardMove(input, board)) {
            return Integer.parseInt(input);
        }
        output.println("\nOops, invalid input. Try again: ");
        return getBoardMove(board);
    }

    private boolean isValidBoardMove(String input, Board board) {
       if (!input.matches("^\\d+$")) {
           return false;
       }
       int move = Integer.parseInt(input);
       return isMoveInBounds(move, board) && board.isEmptySquare(move);
    }

    private boolean isMoveInBounds(int move, Board board) {
        return move >= 1 && move <= board.getTotalSquares();
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
        return board.getRowsOfSquareNumbers()
                    .map(rowOfSquareNumbers -> formatRow(rowOfSquareNumbers, board))
                    .collect(Collectors.joining("\n" + formatRowDivider(board) + "\n"));
    }

    private String formatRow(Stream<Integer> rowOfSquareNumbers, Board board) {
        return rowOfSquareNumbers.map(squareNumber -> formatSquare(squareNumber, board))
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
