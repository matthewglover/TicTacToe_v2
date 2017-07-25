package com.matthewglover.tictactoe.core;

import com.matthewglover.tictactoe.consoleui.BoardFormatter;
import com.matthewglover.tictactoe.consoleui.ComputerPlayer;
import com.matthewglover.tictactoe.consoleui.ComputerPlayerUI;
import com.matthewglover.tictactoe.consoleui.IOTestHelper;
import org.junit.Test;

import static org.junit.Assert.*;

public class ComputerPlayerTest {
    @Test
    public void takesWinningMoveWhenAvailable() {
        Game game = new Game(3);
        ComputerPlayer computerPlayer = new ComputerPlayer(PlayerSymbol.X, 0);
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
        Game game = new Game(3);
        IOTestHelper ioTestHelper = new IOTestHelper();
        ComputerPlayerUI computerPlayerUI = new ComputerPlayerUI(ioTestHelper.getInputStream(), ioTestHelper.getOutputStream());
        ComputerPlayer computerPlayer = new ComputerPlayer(computerPlayerUI, PlayerSymbol.X, 0);
        game.addObserver(computerPlayer);
        String initialBoard = new BoardFormatter(game.getBoard()).format();
        game.start();
        String output = ioTestHelper.getOutContentString();
        assertEquals(initialBoard + "\n", output);
    }
}