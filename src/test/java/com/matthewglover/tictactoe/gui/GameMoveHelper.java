package com.matthewglover.tictactoe.gui;

public class GameMoveHelper {
    public static void runMoves(TicTacToeModel ticTacToeModel, int[] moves) {
        for (int move : moves) {
            ticTacToeModel.gameMove(move);
        }
    }
}
