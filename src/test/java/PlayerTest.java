import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {
    @Test
    public void printBoardPrintsCurrentBoard() {
        Game game = new Game(3);
        PlayerUIBuilder builder = new PlayerUIBuilder("");
        Player player = new Player(builder.getPlayerUI(), PlayerSymbol.X);
        player.printBoard(game.getBoard());

        String printableBoard = new BoardFormatter(game.getBoard()).format();
        assertEquals(printableBoard + "\n", builder.getIoTestHelper().getOutContentString());
    }

    @Test
    public void printMoveRequestPrintsMessageForPlayer() {
        PlayerUIBuilder builder = new PlayerUIBuilder("");
        PlayerSymbol currentPlayerSymbol = PlayerSymbol.X;
        Player player = new Player(builder.getPlayerUI(), currentPlayerSymbol);
        player.printMoveRequest();

        String message = String.format(PlayerMessages.MOVE_REQUEST, currentPlayerSymbol);
        assertEquals(message + "\n", builder.getIoTestHelper().getOutContentString());
    }

    @Test
    public void obtainMoveParsesAndReturnsValidInput() {
        Game game = new Game(3);
        PlayerUIBuilder builder = new PlayerUIBuilder("3\n");
        Player player = new Player(builder.getPlayerUI(), PlayerSymbol.X);
        game.move(1);
        game.move(2);

        assertEquals(3, player.obtainMove(game.getBoard()));
    }

    @Test
    public void obtainMoveReportsErrorAndRequestsValidInputWhenNonIntegerMove() {
        Game game = new Game(3);
        PlayerUIBuilder builder = new PlayerUIBuilder("invalid move\n3\n");
        Player player = new Player(builder.getPlayerUI(), PlayerSymbol.X);

        int move = player.obtainMove(game.getBoard());
        assertEquals(PlayerMessages.INVALID_INPUT + "\n", builder.getIoTestHelper().getOutContentString());
        assertEquals(3, move);
    }

    @Test
    public void obtainMoveReportsErrorAndRequestsValidInputWhenMoveAlreadyTaken() {
        Game game = new Game(3);
        PlayerUIBuilder builder = new PlayerUIBuilder("1\n3\n");
        Player player = new Player(builder.getPlayerUI(), PlayerSymbol.X);
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
        Player player = new Player(builder.getPlayerUI(), PlayerSymbol.X);

        int move = player.obtainMove(game.getBoard());
        assertEquals(PlayerMessages.INVALID_INPUT + "\n", builder.getIoTestHelper().getOutContentString());
        assertEquals(3, move);
    }

    @Test
    public void playerXObtainsMoveOnGameStart() {
        Game game = new Game(3);
        PlayerUIBuilder builder = new PlayerUIBuilder("2\n5\n");
        Player player = new Player(builder.getPlayerUI(), PlayerSymbol.X);
        game.addObserver(player);
        game.start();
        assertEquals(PlayerSymbol.X, game.getBoard().getSquare(2));
    }

    @Test
    public void playersObtainMovesInTurnUntilGameOver() {
        Game game = new Game(3);
        PlayerUIBuilder builder = new PlayerUIBuilder("1\n4\n2\n5\n3\n");
        Player playerX = new Player(builder.getPlayerUI(), PlayerSymbol.X);
        Player playerO = new Player(builder.getPlayerUI(), PlayerSymbol.O);
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