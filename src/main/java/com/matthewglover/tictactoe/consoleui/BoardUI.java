package com.matthewglover.tictactoe.consoleui;

import com.matthewglover.tictactoe.core.*;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;


public class BoardUI extends ModelObserver {
    private final Scanner scanner;
    private final PrintStream out;
    private final TicTacToeModel ticTacToeModel;

    public BoardUI(InputStream in, PrintStream out, TicTacToeModel ticTacToeModel) {
        super(ticTacToeModel);
        scanner = new Scanner(in);
        this.out = out;
        this.ticTacToeModel = ticTacToeModel;
    }

    @Override
    protected void update(ModelUpdate modelUpdate) {
        if (modelUpdate == ModelUpdate.MOVE) {
            run();
        }
    }

    private void run() {
        printBoard();
        if (ticTacToeModel.getNextPlayerType().isHuman()) {
            printMoveRequest();
            ticTacToeModel.gameMove(promptForMove());
        }
    }

    private void printBoard() {
        out.println(new BoardFormatter(getBoard()).format());
    }

    private Board getBoard() {
        return ticTacToeModel.getCurrentGame().getBoard();
    }

    private void printMoveRequest() {
        PlayerSymbol playerSymbol = ticTacToeModel.getCurrentGame().getNextPlayerSymbol();
        String moveRequest = String.format(PlayerMessages.MOVE_REQUEST, playerSymbol);
        out.println(moveRequest);
    }

    public int promptForMove() {
        String input = scanner.nextLine();

        if (input.matches("^\\d+$")) {
            return Integer.parseInt(input);
        } else {
            printInvalidInput();
            return promptForMove();
        }
    }

    public void printInvalidInput() {
        out.println(PlayerMessages.INVALID_INPUT);
    }
}
