package com.matthewglover.tictactoe.consoleui;

import com.matthewglover.tictactoe.core.TicTacToeModel;

import java.io.InputStream;
import java.io.PrintStream;

public class TicTacToeApp {
    private final TicTacToeModel ticTacToeModel;

    public static void main(String[] args) {
        TicTacToeModel ticTacToeModel = new TicTacToeModel();
        TicTacToeApp ticTacToeApp = new TicTacToeApp(System.in, System.out, ticTacToeModel);
        ticTacToeApp.execute();
    }

    public TicTacToeApp(InputStream in, PrintStream out, TicTacToeModel ticTacToeModel) {
        this.ticTacToeModel = ticTacToeModel;
        new GameTypeUI(in, out, ticTacToeModel);
        new BoardSizeUI(in, out, ticTacToeModel);
        new BoardUI(in, out, ticTacToeModel);
        new GameStatusUI(in, out, ticTacToeModel);
    }

    public void execute() {
        ticTacToeModel.reset();
    }
}
