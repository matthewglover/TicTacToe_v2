package com.matthewglover.tictactoe.consoleui;

import com.matthewglover.tictactoe.core.PlayerSymbol;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class HumanPlayerUI extends PlayerUI {

    private final Scanner scanner;

    public HumanPlayerUI(InputStream in, PrintStream out) {
        super(out);
        scanner = new Scanner(in);
    }

    public void printMoveRequest(PlayerSymbol playerSymbol) {
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
