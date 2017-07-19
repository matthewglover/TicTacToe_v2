import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class PlayerUI {

    private final PrintStream out;
    private final Scanner scanner;

    public PlayerUI(InputStream in, PrintStream out) {
        this.out = out;
        scanner = new Scanner(in);
    }

    public void printBoard(Board board) {
        String printableBoard = new BoardFormatter(board).format();
        out.println(printableBoard);
    }

    public void printMoveRequest(PlayerSymbol playerSymbol) {
        String moveRequest = String.format(PlayerMessages.MOVE_REQUEST, playerSymbol);
        out.println(moveRequest);
    }

    public int promptForMove() {
        String input = scanner.nextLine();

        if (input.matches("^\\d+$")) {
            return Integer.parseInt(input);
        } else {
            printInvalidInput();
            return promptForMove();
        }
    }

    public void printInvalidInput() {
        out.println(PlayerMessages.INVALID_INPUT);
    }
}
