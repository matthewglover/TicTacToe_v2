package com.matthewglover.tictactoe.consoleui;

import com.matthewglover.tictactoe.core.ModelObserver;
import com.matthewglover.tictactoe.core.ModelUpdate;
import com.matthewglover.tictactoe.core.PlayerSymbol;
import com.matthewglover.tictactoe.core.TicTacToeModel;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class GameStatusUI extends ModelObserver {
    private final Scanner scanner;
    private final PrintStream out;
    private final TicTacToeModel ticTacToeModel;

    public GameStatusUI(InputStream in, PrintStream out, TicTacToeModel ticTacToeModel) {
        super(ticTacToeModel);
        scanner = new Scanner(in);
        this.out = out;
        this.ticTacToeModel = ticTacToeModel;
    }

    @Override
    protected void update(ModelUpdate modelUpdate) {
        if (modelUpdate == ModelUpdate.GAME_OVER) {
            run();
        }
    }

    private void run() {
        clearScreen();
        printBoard();
        if (isWinner()) {
            reportWinner();
        } else {
            reportDraw();
        }
        printPlayAgainRequest();
        if (promptForPlayAgain()) {
            ticTacToeModel.reset();
        }
    }

    private void printBoard() {
        out.println(new BoardFormatter(ticTacToeModel.getCurrentBoard()).format());
    }

    private boolean isWinner() {
        return ticTacToeModel.getCurrentGame().isWinner();
    }

    private void reportWinner() {
        out.println(String.format(GameStatusMessages.REPORT_WINNER, getWinner()));
    }

    private PlayerSymbol getWinner() {
        return ticTacToeModel.getCurrentGame().getWinner();
    }

    public void reportDraw() {
        out.println(GameStatusMessages.REPORT_DRAW);
    }

    private void printPlayAgainRequest() {
        out.println(GameStatusMessages.REQUEST_PLAY_AGAIN);
    }

    private boolean promptForPlayAgain() {
        String input = scanner.nextLine();
        return input.trim().toLowerCase().matches("y");
    }

    private void clearScreen() {
        out.print("\033[H\033[2J");
    }
}
