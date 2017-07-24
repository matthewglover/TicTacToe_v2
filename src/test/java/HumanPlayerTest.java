import com.mattheglover.tictactoe.core.Game;
import com.mattheglover.tictactoe.core.PlayerSymbol;
import org.junit.Test;

import static org.junit.Assert.*;

public class HumanPlayerTest {
    @Test
    public void printBoardPrintsCurrentBoard() {
        Game game = new Game(3);
        PlayerUIBuilder builder = new PlayerUIBuilder("");
        HumanPlayer player = new HumanPlayer(builder.getHumanPlayerUI(), PlayerSymbol.X);
        player.printBoard(game.getBoard());

        String printableBoard = new BoardFormatter(game.getBoard()).format();
        assertEquals(printableBoard + "\n", builder.getIoTestHelper().getOutContentString());
    }

    @Test
    public void printMoveRequestPrintsMessageForPlayer() {
        PlayerUIBuilder builder = new PlayerUIBuilder("");
        PlayerSymbol currentPlayerSymbol = PlayerSymbol.X;
        HumanPlayer player = new HumanPlayer(builder.getHumanPlayerUI(), currentPlayerSymbol);
        player.printMoveRequest();

        String message = String.format(PlayerMessages.MOVE_REQUEST, currentPlayerSymbol);
        assertEquals(message + "\n", builder.getIoTestHelper().getOutContentString());
    }

    @Test
    public void obtainMoveParsesAndReturnsValidInput() {
        Game game = new Game(3);
        PlayerUIBuilder builder = new PlayerUIBuilder("3\n");
        HumanPlayer player = new HumanPlayer(builder.getHumanPlayerUI(), PlayerSymbol.X);
        game.move(1);
        game.move(2);

        assertEquals(3, player.obtainMove(game.getBoard()));
    }

    @Test
    public void obtainMoveReportsErrorAndRequestsValidInputWhenNonIntegerMove() {
        Game game = new Game(3);
        PlayerUIBuilder builder = new PlayerUIBuilder("invalid move\n3\n");
        HumanPlayer player = new HumanPlayer(builder.getHumanPlayerUI(), PlayerSymbol.X);

        int move = player.obtainMove(game.getBoard());
        assertEquals(PlayerMessages.INVALID_INPUT + "\n", builder.getIoTestHelper().getOutContentString());
        assertEquals(3, move);
    }

    @Test
    public void obtainMoveReportsErrorAndRequestsValidInputWhenMoveAlreadyTaken() {
        Game game = new Game(3);
        PlayerUIBuilder builder = new PlayerUIBuilder("1\n3\n");
        HumanPlayer player = new HumanPlayer(builder.getHumanPlayerUI(), PlayerSymbol.X);
        game.move(1);
        game.move(2);

        int move = player.obtainMove(game.getBoard());
        assertEquals(PlayerMessages.INVALID_INPUT + "\n", builder.getIoTestHelper().getOutContentString());
        assertEquals(3, move);
    }

    @Test
    public void obtainMoveReportsErrorAndRequestsValidInputWhenMoveOutOfBounds() {
        Game game = new Game(3);
        PlayerUIBuilder builder = new PlayerUIBuilder("11\n3\n");
        HumanPlayer player = new HumanPlayer(builder.getHumanPlayerUI(), PlayerSymbol.X);

        int move = player.obtainMove(game.getBoard());
        assertEquals(PlayerMessages.INVALID_INPUT + "\n", builder.getIoTestHelper().getOutContentString());
        assertEquals(3, move);
    }

    @Test
    public void playerXObtainsMoveOnGameStart() {
        Game game = new Game(3);
        PlayerUIBuilder builder = new PlayerUIBuilder("2\n5\n");
        HumanPlayer player = new HumanPlayer(builder.getHumanPlayerUI(), PlayerSymbol.X);
        game.addObserver(player);
        game.start();
        assertEquals(PlayerSymbol.X, game.getBoard().getSquare(2));
    }

    @Test
    public void playersObtainMovesInTurnUntilGameOver() {
        Game game = new Game(3);
        PlayerUIBuilder builder = new PlayerUIBuilder("1\n4\n2\n5\n3\n");
        HumanPlayer playerX = new HumanPlayer(builder.getHumanPlayerUI(), PlayerSymbol.X);
        HumanPlayer playerO = new HumanPlayer(builder.getHumanPlayerUI(), PlayerSymbol.O);
        game.addObserver(playerX);
        game.addObserver(playerO);
        game.start();
        assertEquals(PlayerSymbol.X, game.getBoard().getSquare(1));
        assertEquals(PlayerSymbol.O, game.getBoard().getSquare(4));
        assertEquals(PlayerSymbol.X, game.getBoard().getSquare(2));
        assertEquals(PlayerSymbol.O, game.getBoard().getSquare(5));
        assertEquals(PlayerSymbol.X, game.getBoard().getSquare(3));
    }
}