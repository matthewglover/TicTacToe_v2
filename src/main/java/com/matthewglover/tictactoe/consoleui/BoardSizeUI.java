package com.matthewglover.tictactoe.consoleui;

import com.matthewglover.tictactoe.core.Board;
import com.matthewglover.tictactoe.core.ModelUpdate;
import com.matthewglover.tictactoe.core.TicTacToeModel;

import java.io.InputStream;
import java.io.PrintStream;

public class BoardSizeUI extends UI {

    public BoardSizeUI(InputStream in, PrintStream out, TicTacToeModel ticTacToeModel) {
        super(in, out, ticTacToeModel);
    }

    @Override
    protected void update(ModelUpdate modelUpdate) {
        if (modelUpdate == ModelUpdate.SET_GAME_TYPE) {
            run();
        }
    }

    @Override
    protected void run() {
        clearScreen();
        printRequestBoardSize();
        setBoardSize();
    }

    private void printRequestBoardSize() {
        out.println(GameOptionsMessages.REQUEST_BOARD_SIZE);
    }

    private void setBoardSize() {
        ticTacToeModel.setCurrentBoard(promptForBoardSize());
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
        if (!isNumericInput(input)) {
            return false;
        }

        int value = Integer.parseInt(input);

        return value >= Board.MIN_SIZE && value <= Board.MAX_SIZE;
    }

    private void printInvalidInput() {
        out.println(GameOptionsMessages.INVALID_INPUT);
    }

}
