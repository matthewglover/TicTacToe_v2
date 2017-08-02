package com.matthewglover.tictactoe.consoleui;

import com.matthewglover.tictactoe.core.Board;
import com.matthewglover.tictactoe.core.TicTacToeModel;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class BoardSizeUI {
    private final Scanner scanner;
    private final PrintStream out;
    private final TicTacToeModel ticTacToeModel;

    public BoardSizeUI(InputStream in, PrintStream out, TicTacToeModel ticTacToeModel) {
        scanner = new Scanner(in);
        this.out = out;
        this.ticTacToeModel = ticTacToeModel;
    }

    public void run() {
        printRequestBoardSize();
        ticTacToeModel.setCurrentBoard(promptForBoardSize());
    }

    private void printRequestBoardSize() {
        out.println(GameOptionsMessages.REQUEST_BOARD_SIZE);
    }

    private int promptForBoardSize() {
        String input = scanner.nextLine();

        if (isValidBoardSize(input)) {
            return Integer.parseInt(input);
        } else {
            printInvalidInput();
            return promptForBoardSize();
        }
    }

    private boolean isValidBoardSize(String input) {
        if (!input.matches("^\\d$")) {
            return false;
        }

        int value = Integer.parseInt(input);

        return value >= Board.MIN_SIZE && value <= Board.MAX_SIZE;
    }

    private void printInvalidInput() {
        out.println(GameOptionsMessages.INVALID_INPUT);
    }
}
