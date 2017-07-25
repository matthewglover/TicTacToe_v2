package com.matthewglover.tictactoe.consoleui;

import com.matthewglover.tictactoe.core.Board;

import java.io.PrintStream;

public class PlayerUI {
    protected final PrintStream out;

    public PlayerUI(PrintStream out) {
        this.out = out;
    }

    public void printBoard(Board board) {
        String printableBoard = new BoardFormatter(board).format();
        out.println(printableBoard);
    }

    public void clearScreen() {
        out.print("\033[H\033[2J");
    }
}
