package com.matthewglover.tictactoe.consoleui;

import com.matthewglover.tictactoe.core.ModelObserver;
import com.matthewglover.tictactoe.core.ModelUpdate;
import com.matthewglover.tictactoe.core.TicTacToeModel;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public abstract class UI extends ModelObserver {
    protected final Scanner scanner;
    protected final PrintStream out;
    protected final TicTacToeModel ticTacToeModel;

    public UI(InputStream in, PrintStream out, TicTacToeModel ticTacToeModel) {
        super(ticTacToeModel);
        scanner = new Scanner(in);
        this.out = out;
        this.ticTacToeModel = ticTacToeModel;
    }

    @Override
    protected abstract void update(ModelUpdate modelUpdate);

    protected abstract void run();

    protected void clearScreen() {
        out.print("\033[H\033[2J");
    }
}
