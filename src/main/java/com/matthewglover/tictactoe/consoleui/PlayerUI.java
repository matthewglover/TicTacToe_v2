package com.matthewglover.tictactoe.consoleui;

import com.matthewglover.tictactoe.core.Board;

import java.io.PrintStream;

public class PlayerUI {
    protected final PrintStream out;
    private final String CLEAR_LINE = "\033[H\033[2J";

    public PlayerUI(PrintStream out) {
        this.out = out;
    }

    public void printBoard(Board board) {
        String printableBoard = new BoardFormatter(board).format();
        clearAndPrint(printableBoard);
    }

    protected void clearAndPrint(String str) {
        clearScreen();
        out.println(str);
    }

    private void clearScreen() {
        out.print(CLEAR_LINE);
    }
}
