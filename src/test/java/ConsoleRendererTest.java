import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class ConsoleRendererTest {

    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private PrintStream outputStream = new PrintStream(outContent);
//    private InputStream inputStream = new ByteArrayInputStream("test data".getBytes());
    private ConsoleRenderer renderer = new ConsoleRenderer(outputStream);
    private Board board = new Board();

    @Test
    public void promptXForMoveByXPrintsCurrentBoardAndPromptsXForMove() {
        renderer.promptForMove(Player.X, board);
        String[] expected = {
                " 1 | 2 | 3 ",
                "--- --- ---",
                " 4 | 5 | 6 ",
                "--- --- ---",
                " 7 | 8 | 9 ",
                "Player X: "
        };
        assertLinesEqual(expected);
    }

    @Test
    public void promptForMoveByOPrintsCurrentBoardAndPromptsOForMove() {
        renderer.promptForMove(Player.O, board);
        assertLinesEqual(new String[]{
                " 1 | 2 | 3 ",
                "--- --- ---",
                " 4 | 5 | 6 ",
                "--- --- ---",
                " 7 | 8 | 9 ",
                "Player O: "
        });
    }

    @Test
    public void promptForMovePrintsCurrentBoardState() {
        board.setSquare(1, Player.X);
        board.setSquare(2, Player.O);
        board.setSquare(3, Player.O);
        board.setSquare(9, Player.X);
        renderer.promptForMove(Player.X, board);
        assertLinesEqual(new String[]{
            " X | O | O ",
            "--- --- ---",
            " 4 | 5 | 6 ",
            "--- --- ---",
            " 7 | 8 | X ",
            "Player X: "
        });
    }

    @Test
    public void reportsDraw() {
       renderer.reportDraw();
       assertEquals("It's a draw!", outContent.toString());
    }

    @Test
    public void reportsWinner() {
        renderer.reportWinner(Player.X);
        assertEquals("X Wins!!", outContent.toString());
    }

    @Test
    public void reportsInputError() {
        renderer.reportMoveError();
        assertEquals("Oops, that square is already taken. Try again: ", outContent.toString());
    }

    private void assertLinesEqual(String[] expected) {
        assertEquals(String.join("\n", expected), outContent.toString());
    }
}