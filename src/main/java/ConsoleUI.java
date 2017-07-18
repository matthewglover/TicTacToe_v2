import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class ConsoleUI implements GameUI {

    private final PrintStream out;
    private final Scanner scanner;

    public ConsoleUI(InputStream in, PrintStream out) {
        this.out = out;
        this.scanner = new Scanner(in);
    }

    public int promptForMove(Player player, Board board) {
        printRequestMove(player, board);
        return getBoardMove(board);
    }

    public void reportDraw() {
        out.print(UIMessages.GAME_DRAWN);
    }

    public void reportWinner(Player player) {
        out.print(String.format(UIMessages.GAME_WON, player.toString()));
    }

    public boolean promptPlayAgain() {
        out.print(UIMessages.NEW_GAME_PROMPT);
        return getPlayAgain();
    }

    public int promptForBoardSize(int minBoardSize, int maxBoardSize) {
        out.print(UIMessages.SELECT_BOARD_SIZE_PROMPT);

        return getBoardSize(minBoardSize, maxBoardSize);
    }

    private void printRequestMove(Player player, Board board) {
        BoardFormatter boardFormatter = new BoardFormatter(board);
        out.println(boardFormatter.format());
        out.print(formatPlayerPrompt(player));
    }

    private int getBoardMove(Board board) {
        String input = scanner.nextLine();
        if (isValidBoardMove(input, board)) {
            return Integer.parseInt(input);
        }
        promptInvalidInput();
        return getBoardMove(board);
    }

    private int getBoardSize(int minBoardSize, int maxBoardSize) {
        String input = scanner.nextLine();
        if (isValidBoardSize(input, minBoardSize, maxBoardSize)) {
            return Integer.parseInt(input);
        }
        promptInvalidInput();
        return getBoardSize(minBoardSize, maxBoardSize);
    }

    private void promptInvalidInput() {
        out.print("\n" + UIMessages.INVALID_INPUT_PROMPT);
    }

    private boolean getPlayAgain() {
        String input = scanner.nextLine();
        return input.equals(UIMessages.DONT_PLAY_AGAIN_INPUT);
    }

    private boolean isValidBoardMove(String input, Board board) {
        if (!input.matches("^\\d+$")) {
            return false;
        }
        int move = Integer.parseInt(input);
        return isMoveInBounds(move, board) && board.isEmptySquare(move);
    }

    private boolean isValidBoardSize(String input, int minBoardSize, int maxBoardSize) {
        if (!input.matches("^\\d+$")) {
            return false;
        }
        int boardSize = Integer.parseInt(input);
        return isBoardSizeInBounds(boardSize, minBoardSize, maxBoardSize);
    }

    private boolean isMoveInBounds(int move, Board board) {
        return move >= board.FIRST_SQUARE_NUMBER && move <= board.getTotalSquares();
    }

    private boolean isBoardSizeInBounds(int boardSize, int minBoardSize, int maxBoardSize) {
        return boardSize >= minBoardSize && boardSize <= maxBoardSize;
    }

    private String formatPlayerPrompt(Player player) {
        return String.format(UIMessages.PLAYER_MOVE_PROMPT, player.toString());
    }
}
