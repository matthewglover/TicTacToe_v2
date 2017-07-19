import org.junit.Test;

import static org.junit.Assert.*;

public class GameOptionsTest {

    @Test
    public void getBoardSizeReturnsUserSetValue() {
        GameOptionsUIBuilder builder = new GameOptionsUIBuilder("3\n1\n");
        GameOptions gameOptions = new GameOptions(builder.getGameOptionsUI());
        gameOptions.execute();
        assertEquals(3, gameOptions.getBoardSize());
    }

    @Test
    public void getGameTypeReturnsUserSetValue() {
        GameOptionsUIBuilder builder = new GameOptionsUIBuilder("3\n1\n");
        GameOptions gameOptions = new GameOptions(builder.getGameOptionsUI());
        gameOptions.execute();
        assertEquals(GameType.values()[0], gameOptions.getGameType());
    }
}