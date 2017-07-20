import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.regex.Pattern;

public class GameStatusUI {

    private final Scanner scanner;
    private final PrintStream out;

    public GameStatusUI(InputStream in, PrintStream out) {
        scanner = new Scanner(in);
        this.out = out;
    }

    public void reportWinner(PlayerSymbol winner) {
        out.println(String.format(GameStatusMessages.REPORT_WINNER, winner));
    }

    public void reportDraw() {
        out.println(GameStatusMessages.REPORT_DRAW);
    }

    public void printPlayAgainRequest() {
        out.println(GameStatusMessages.REQUEST_PLAY_AGAIN);
    }

    public boolean promptForPlayAgain() {
        String input = scanner.nextLine();
        return input.trim().toLowerCase().matches("y");
    }
}
