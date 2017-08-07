package com.matthewglover.tictactoe.core;

import java.util.Arrays;

public class GameTestHelper {
    public static void runGame(Game game, int[] moves) {
        Arrays.stream(moves).forEach(game::move);
    }
}
