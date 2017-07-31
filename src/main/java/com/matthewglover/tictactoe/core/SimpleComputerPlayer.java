package com.matthewglover.tictactoe.core;

public class SimpleComputerPlayer extends SimplePlayer {
    private static final int MAX_SEARCH_DEPTH = 6;
    private final int withDelay;

    public SimpleComputerPlayer(PlayerSymbol playerSymbol, int withDelay) {
        super(playerSymbol);
        this.withDelay = withDelay;
    }

    @Override
    public boolean isComputer() {
        return true;
    }

    @Override
    public int getMove(Game game) {
        sleep();
        return AlphaBeta.run(game, MAX_SEARCH_DEPTH).getMove();
    }

    private void sleep() {
        try {
            Thread.sleep(withDelay);
        } catch (Exception e) {
        }
    }
}
