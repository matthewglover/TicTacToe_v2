package com.matthewglover.tictactoe.consoleui;

import com.matthewglover.tictactoe.core.ModelUpdate;
import com.matthewglover.tictactoe.core.PlayerSymbol;
import com.matthewglover.tictactoe.core.TicTacToeModel;

import java.io.InputStream;
import java.io.PrintStream;

public class GameStatusUI extends UI {

    public GameStatusUI(InputStream in, PrintStream out, TicTacToeModel ticTacToeModel) {
        super(in, out, ticTacToeModel);
    }

    @Override
    protected void update(ModelUpdate modelUpdate) {
        if (modelUpdate == ModelUpdate.GAME_OVER) {
            run();
        }
    }

    @Override
    protected void run() {
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
}
