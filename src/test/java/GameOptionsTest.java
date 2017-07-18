import org.junit.Test;

import static org.junit.Assert.*;

public class GameOptionsTest {

    @Test
    public void getBoardSizeReturnsSetValue() {
        GameOptions gameOptions = new GameOptions();
        gameOptions.setBoardSize(3);
        assertEquals(3, gameOptions.getBoardSize());
    }

    @Test
    public void getGameTypeReturnsSetValue() {
        GameOptions gameOptions = new GameOptions();
        assertEquals(null, gameOptions.getGameType());
        gameOptions.setGameType(GameType.HUMAN_HUMAN);
        assertEquals(GameType.HUMAN_HUMAN, gameOptions.getGameType());
    }
}