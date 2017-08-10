package com.matthewglover.tictactoe.core;

import java.util.List;
import java.util.function.IntPredicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ReplayPlayer extends ComputerPlayer {
    private final IntPredicate xMoveSelector = movePosition -> movePosition % 2 == 0;
    private final IntPredicate oMoveSelector = movePosition -> movePosition % 2 != 0;
    private List<Integer> playerMoves;

    public ReplayPlayer(PlayerSymbol playerSymbol) {
        super(playerSymbol);
    }

    @Override
    public int getMove(Game game) {
        return playerMoves.remove(0);
    }

    public void addMoves(List<Integer> gameMoves) {
        this.playerMoves = IntStream.range(0, gameMoves.size())
                .filter(playerSymbol.isX() ? xMoveSelector : oMoveSelector)
                .mapToObj(gameMoves::get)
                .collect(Collectors.toList());
    }
}
