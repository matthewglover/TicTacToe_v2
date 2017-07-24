import com.mattheglover.tictactoe.core.Board;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class GameOptionsUI {

    private final Scanner scanner;
    private final PrintStream out;

    public GameOptionsUI(InputStream in, PrintStream out) {
        scanner = new Scanner(in);
        this.out = out;
    }

    public void printRequestBoardSize() {
       out.println(GameOptionsMessages.REQUEST_BOARD_SIZE);
    }

    public void printRequestGameType() {
        out.println(GameOptionsMessages.REQUEST_GAME_TYPE_INTRO);
        int counter = 1;
        for (GameType gameType : GameType.values()) {
            out.println("(" + counter + ") " + gameType.getDescription());
            counter++;
        }
    }

    public int promptForBoardSize() {
        String input = scanner.nextLine();

        if (isValidBoardSize(input)) {
            return Integer.parseInt(input);
        } else {
            printInvalidInput();
            return promptForBoardSize();
        }
    }

    public GameType promptForGameType() {
        String input = scanner.nextLine();

        if (isValidGameType(input)) {
            int index = Integer.parseInt(input) - 1;
            return GameType.values()[index];
        } else {
            printInvalidInput();
            return promptForGameType();
        }
    }

    private boolean isValidBoardSize(String input) {
        if (!input.matches("^\\d$")) {
            return false;
        }

        int value = Integer.parseInt(input);

        return value >= Board.MIN_SIZE && value <= Board.MAX_SIZE;
    }

    private boolean isValidGameType(String input) {
        if (!input.matches("^\\d$")) {
            return false;
        }

        int value = Integer.parseInt(input) - 1;

        return value >= 0 && value < GameType.values().length;
    }

    private void printInvalidInput() {
        out.println(GameOptionsMessages.INVALID_INPUT);
    }

    public void clearScreen() {
        out.print("\033[H\033[2J");
    }
}
