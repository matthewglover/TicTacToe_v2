package com.matthewglover.tictactoe.consoleui;

import com.matthewglover.tictactoe.core.GameType;
import com.matthewglover.tictactoe.core.ModelUpdate;
import com.matthewglover.tictactoe.core.TicTacToeModel;

import java.io.InputStream;
import java.io.PrintStream;

public class GameTypeUI extends UI {

    public GameTypeUI(InputStream in, PrintStream out, TicTacToeModel ticTacToeModel) {
        super(in, out, ticTacToeModel);
    }

    @Override
    protected void update(ModelUpdate modelUpdate) {
        if (modelUpdate == ModelUpdate.SETUP_NEW_GAME) {
            run();
        }
    }

    @Override
    protected void run() {
        clearScreen();
        printRequestGameType();
        ticTacToeModel.setGameType(promptForGameType());
    }

    private void printRequestGameType() {
        out.println(GameOptionsMessages.REQUEST_GAME_TYPE_INTRO);
        int counter = 1;
        for (GameType gameType : GameType.getSelectableValues()) {
            out.println("(" + counter + ") " + gameType.getDescription());
            counter++;
        }
    }

    private GameType promptForGameType() {
        String input = scanner.nextLine();

        if (isValidGameType(input)) {
            int index = Integer.parseInt(input) - 1;
            return GameType.values()[index];
        } else {
            printInvalidInput();
            return promptForGameType();
        }
    }

    private boolean isValidGameType(String input) {
        if (!isNumericInput(input)) {
            return false;
        }

        int value = Integer.parseInt(input) - 1;

        return value >= 0 && value < GameType.getSelectableValues().size();
    }

    private void printInvalidInput() {
        out.println(GameOptionsMessages.INVALID_INPUT);
    }
}
