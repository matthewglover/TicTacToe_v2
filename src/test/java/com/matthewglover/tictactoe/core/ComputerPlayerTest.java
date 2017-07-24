package com.matthewglover.tictactoe.core;

import org.junit.Test;

import static org.junit.Assert.*;

public class ComputerPlayerTest {
    @Test
    public void takesWinningMoveWhenAvailable() {
        Game game = new Game(3);
        ComputerPlayer computerPlayer = new ComputerPlayer(PlayerSymbol.X);
        game.start();
        game.move(1);
        game.move(4);
        game.move(2);
        game.addObserver(computerPlayer);
        game.move(5);
        assertTrue(game.isOver());
        assertEquals(PlayerSymbol.X, game.getWinner());
    }
}