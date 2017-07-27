package com.matthewglover.tictactoe.gui;

import com.matthewglover.tictactoe.core.PlayerType;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GuiGameTest {
    @Test
    public void tracksGameAndPlayers() {
        GuiGame guiGame = new GuiGame(3, PlayerType.HUMAN, PlayerType.COMPUTER);
        assertEquals(PlayerType.HUMAN, guiGame.getNextPlayerType());
        guiGame.move(1);
        assertEquals(PlayerType.COMPUTER, guiGame.getNextPlayerType());
    }
}
