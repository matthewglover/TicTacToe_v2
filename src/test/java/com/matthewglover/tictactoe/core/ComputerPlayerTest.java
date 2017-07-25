package com.matthewglover.tictactoe.core;

import com.matthewglover.tictactoe.consoleui.BoardFormatter;
import com.matthewglover.tictactoe.consoleui.ComputerPlayer;
import com.matthewglover.tictactoe.consoleui.ComputerPlayerUI;
import com.matthewglover.tictactoe.consoleui.IOTestHelper;
import org.junit.Test;

import static org.junit.Assert.*;

public class ComputerPlayerTest {

    private final IOTestHelper ioTestHelper = new IOTestHelper();
    private final ComputerPlayerUI computerPlayerUI = new ComputerPlayerUI(ioTestHelper.getInputStream(), ioTestHelper.getOutputStream());
    private final ComputerPlayer computerPlayer = new ComputerPlayer(computerPlayerUI, PlayerSymbol.X, 0);
    private final Game game = new Game(3);

    @Test
    public void takesWinningMoveWhenAvailable() {
        game.start();
        game.move(1);
        game.move(4);
        game.move(2);
        game.addObserver(computerPlayer);
        game.move(5);
        assertTrue(game.isOver());
        assertEquals(PlayerSymbol.X, game.getWinner());
    }

    @Test
    public void printsBoardBeforeMove() {
        game.addObserver(computerPlayer);
        String initialBoard = new BoardFormatter(game.getBoard()).format();
        game.start();
        String output = ioTestHelper.getOutContentString();
        assertEquals(initialBoard + "\n", output);
    }
}