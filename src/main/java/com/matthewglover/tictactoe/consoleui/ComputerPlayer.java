package com.matthewglover.tictactoe.consoleui;

import com.matthewglover.tictactoe.core.AlphaBeta;
import com.matthewglover.tictactoe.core.Game;
import com.matthewglover.tictactoe.core.Player;
import com.matthewglover.tictactoe.core.PlayerSymbol;

public class ComputerPlayer extends Player {

    private static final int MAX_SEARCH_DEPTH = 6;
    private final int withDelay;
    private ComputerPlayerUI computerPlayerUI;

    public ComputerPlayer(ComputerPlayerUI computerPlayerUI, PlayerSymbol playerSymbol, int withDelay) {
        super(playerSymbol);
        this.computerPlayerUI = computerPlayerUI;
        this.withDelay = withDelay;
    }

    @Override
    protected void makeMove(Game game) {
        if (computerPlayerUI != null) {
            computerPlayerUI.printBoard(game.getBoard());
        }
        sleep();
        game.move(AlphaBeta.run(game, MAX_SEARCH_DEPTH).getMove());
    }

    private void sleep() {
        try {
            Thread.sleep(withDelay);
        } catch (Exception e) {
        }
    }
}
