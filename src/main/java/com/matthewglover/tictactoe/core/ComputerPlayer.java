package com.matthewglover.tictactoe.core;


public class ComputerPlayer extends Player {

    private static final int MAX_SEARCH_DEPTH = 6;
    private final int withDelay;

    public ComputerPlayer(PlayerSymbol playerSymbol, int withDelay) {
        super(playerSymbol);
        this.withDelay = withDelay;
    }

    @Override
    protected void makeMove(Game game) {
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
