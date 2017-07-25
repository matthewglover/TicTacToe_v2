package com.matthewglover.tictactoe.consoleui;

import java.io.InputStream;
import java.io.PrintStream;

public class ComputerPlayerUI extends PlayerUI {
    public ComputerPlayerUI(InputStream in, PrintStream out) {
        super(out);
    }
}
