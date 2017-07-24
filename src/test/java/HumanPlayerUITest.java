import com.mattheglover.tictactoe.core.Game;
import com.mattheglover.tictactoe.core.PlayerSymbol;
import org.junit.Test;

import static org.junit.Assert.*;

public class HumanPlayerUITest {
    @Test
    public void printBoardPrintsCurrentBoard() {
        Game game = new Game(3);
        game.move(1);
        game.move(2);
        PlayerUIBuilder builder = new PlayerUIBuilder("");
        builder.getHumanPlayerUI().printBoard(game.getBoard());

        String printableBoard = new BoardFormatter(game.getBoard()).format();
        assertEquals(printableBoard + "\n", builder.getIoTestHelper().getOutContentString());
    }

    @Test
    public void printMoveRequestPrintsMessage() {
        PlayerSymbol currentPlayerSymbol = PlayerSymbol.X;
        PlayerUIBuilder builder = new PlayerUIBuilder("");
        builder.getHumanPlayerUI().printMoveRequest(currentPlayerSymbol);

        String message = String.format(PlayerMessages.MOVE_REQUEST, currentPlayerSymbol);
        assertEquals(message + "\n", builder.getIoTestHelper().getOutContentString());
    }

    @Test
    public void promptForMoveReportsErrorAndPromptsForValidInputWithNonIntegerValues() {
        PlayerUIBuilder builder = new PlayerUIBuilder("invalid input\n3\n");

        int move = builder.getHumanPlayerUI().promptForMove();
        assertEquals(3, move);
        assertEquals(PlayerMessages.INVALID_INPUT + "\n", builder.getIoTestHelper().getOutContentString());
    }
}
