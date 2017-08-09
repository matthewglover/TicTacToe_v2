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
        reportResult();
        printPlayAgainRequest();
        promptForPlayAgain();
    }

    private void printBoard() {
        out.println(new BoardFormatter(ticTacToeModel.getBoard()).format());
    }

    private void reportResult() {
        if (isWinner()) {
            reportWinner();
        } else {
            reportDraw();
        }
    }

    private boolean isWinner() {
        return ticTacToeModel.getGame().isWinner();
    }

    private void reportWinner() {
        out.println(String.format(GameStatusMessages.REPORT_WINNER, getWinner()));
    }

    private PlayerSymbol getWinner() {
        return ticTacToeModel.getGame().getWinner();
    }

    public void reportDraw() {
        out.println(GameStatusMessages.REPORT_DRAW);
    }

    private void printPlayAgainRequest() {
        out.println(GameStatusMessages.REQUEST_PLAY_AGAIN);
    }

    private void promptForPlayAgain() {
        String input = scanner.nextLine();
        if (isPlayAgain(input)) {
            ticTacToeModel.setupNewGame();
        }
    }

    private boolean isPlayAgain(String input) {
        return input.trim().toLowerCase().matches("y");
    }
}
