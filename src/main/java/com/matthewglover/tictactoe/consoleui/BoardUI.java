package com.matthewglover.tictactoe.consoleui;

import com.matthewglover.tictactoe.core.*;

import java.io.InputStream;
import java.io.PrintStream;


public class BoardUI extends UI {

    public BoardUI(InputStream in, PrintStream out, TicTacToeModel ticTacToeModel) {
        super(in, out, ticTacToeModel);
    }

    @Override
    protected void update(ModelUpdate modelUpdate) {
        if (modelUpdate == ModelUpdate.MAKE_MOVE) {
            run();
        }
    }

    @Override
    protected void run() {
        clearScreen();
        printBoard();
        if (isNextPlayerHuman()) {
            printMoveRequest();
            setNextPlayerMove();
        }
    }

    private void printBoard() {
        out.println(new BoardFormatter(getBoard()).format());
    }

    private Board getBoard() {
        return ticTacToeModel.getCurrentGame().getBoard();
    }

    private boolean isNextPlayerHuman() {
        return ticTacToeModel.getNextPlayerType().isHuman();
    }

    private void printMoveRequest() {
        out.println(String.format(PlayerMessages.MOVE_REQUEST, getNextPlayerSymbol()));
    }

    private PlayerSymbol getNextPlayerSymbol() {
        return ticTacToeModel.getCurrentGame().getNextPlayerSymbol();
    }

    private void setNextPlayerMove() {
        ticTacToeModel.gameMove(promptForMove());
    }

    private int promptForMove() {
        String input = scanner.nextLine();

        if (isValidInput(input)) {
            return Integer.parseInt(input);
        } else {
            printInvalidInput();
            return promptForMove();
        }
    }

    private boolean isValidInput(String input) {
        return isNumericInput(input) && isValidMove(Integer.parseInt(input));
    }

    private boolean isValidMove(int move) {
        return ticTacToeModel.getCurrentBoard().isValidMove(move);
    }

    private void printInvalidInput() {
        out.println(PlayerMessages.INVALID_INPUT);
    }
}
