package com.matthewglover.tictactoe.consoleui;

import com.matthewglover.tictactoe.core.GameType;
import com.matthewglover.tictactoe.core.TicTacToeModel;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class GameTypeUI {
    private final Scanner scanner;
    private final PrintStream out;
    private final TicTacToeModel ticTacToeModel;

    public GameTypeUI(InputStream in, PrintStream out, TicTacToeModel ticTacToeModel) {
        scanner = new Scanner(in);
        this.out = out;
        this.ticTacToeModel = ticTacToeModel;
    }

    public void run() {
        printRequestGameType();
        ticTacToeModel.setCurrentGameType(promptForGameType());
    }

    private void printRequestGameType() {
        out.println(GameOptionsMessages.REQUEST_GAME_TYPE_INTRO);
        int counter = 1;
        for (GameType gameType : GameType.values()) {
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
        if (!input.matches("^\\d$")) {
            return false;
        }

        int value = Integer.parseInt(input) - 1;

        return value >= 0 && value < GameType.values().length;
    }

    private void printInvalidInput() {
        out.println(GameOptionsMessages.INVALID_INPUT);
    }
}