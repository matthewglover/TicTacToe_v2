import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class ConsoleUI implements GameUI {

    private PrintStream out;
    private Scanner scanner;
    private static final int FIRST_SQUARE_NUMBER = 1;
    private static final int MIN_BOARD_SIZE = 3;
    private static final int MAX_BOARD_SIZE = 4;

    public ConsoleUI(InputStream in, PrintStream out) {
        this.out = out;
        this.scanner = new Scanner(in);
    }

    public int promptForMove(Player player, BoardReader board) {
        BoardFormatter boardFormatter = new BoardFormatter(board);
        out.println(boardFormatter.format());
        out.print(formatPlayerPrompt(player));
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

    public int promptForBoardSize() {
        out.print(UIMessages.SELECT_BOARD_SIZE_PROMPT);
        return getBoardSize();
    }

    private int getBoardSize() {
        String input = scanner.nextLine();
        if (isValidBoardSize(input)) {
            return Integer.parseInt(input);
        }
        promptInvalidInput();
        return getBoardSize();
    }

    private int getBoardMove(BoardReader board) {
        String input = scanner.nextLine();
        if (isValidBoardMove(input, board)) {
            return Integer.parseInt(input);
        }
        promptInvalidInput();
        return getBoardMove(board);
    }

    private void promptInvalidInput() {
        out.print("\n" + UIMessages.INVALID_INPUT_PROMPT);
    }

    private boolean getPlayAgain() {
        String input = scanner.nextLine();
        return input.equals(UIMessages.DONT_PLAY_AGAIN_INPUT);
    }

    private boolean isValidBoardMove(String input, BoardReader board) {
        if (!input.matches("^\\d+$")) {
            return false;
        }
        int move = Integer.parseInt(input);
        return isMoveInBounds(move, board) && board.isEmptySquare(move);
    }

    private boolean isValidBoardSize(String input) {
        if(!input.matches("^\\d+$")) {
            return false;
        }
        int boardSize = Integer.parseInt(input);
        return isBoardSizeInBounds(boardSize);
    }

    private boolean isMoveInBounds(int move, BoardReader board) {
        return move >= FIRST_SQUARE_NUMBER && move <= board.getTotalSquares();
    }

    private boolean isBoardSizeInBounds(int boardSize) {
        return boardSize >= MIN_BOARD_SIZE && boardSize <= MAX_BOARD_SIZE;
    }

    private String formatPlayerPrompt(Player player) {
        return String.format(UIMessages.PLAYER_MOVE_PROMPT, player.toString());
    }
}
