package com.matthewglover.tictactoe.core;

import java.util.List;

public class ReplayPlayer extends ComputerPlayer {
    private List<Integer> moveSequence;

    public ReplayPlayer(PlayerSymbol playerSymbol) {
        super(playerSymbol);
    }

    @Override
    public int getMove(Game game) {
        return moveSequence.remove(0);
    }

    public void addMoves(List<Integer> moveSequence) {
        this.moveSequence = moveSequence;
    }
}
