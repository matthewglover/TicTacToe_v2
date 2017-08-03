package com.matthewglover.tictactoe.consoleui;

import com.matthewglover.tictactoe.core.Game;

import java.util.Observable;
import java.util.Observer;

public class GameStatus extends Observable implements Observer {
    private final OldGameStatusUI gameStatusUI;

    public GameStatus(OldGameStatusUI gameStatusUI) {
        this.gameStatusUI = gameStatusUI;
    }

    @Override
    public void update(Observable o, Object arg) {
        Game game = (Game) o;

        if (!game.isOver()) {
            return;
        }

        gameStatusUI.clearScreen();
        gameStatusUI.printBoard(game.getBoard());
        reportCompletedGame(game);
        printPlayAgainRequest();
        promptForPlayAgain();
    }

    private void promptForPlayAgain() {
        boolean playAgain = gameStatusUI.promptForPlayAgain();
        setChanged();
        notifyObservers(playAgain);
    }

    private void reportCompletedGame(Game game) {
        if (game.isWinner()) {
            gameStatusUI.reportWinner(game.getWinner());
        } else {
            gameStatusUI.reportDraw();
        }
    }

    private void printPlayAgainRequest() {
        gameStatusUI.printPlayAgainRequest();
    }
}
