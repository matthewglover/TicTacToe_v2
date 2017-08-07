package com.matthewglover.tictactoe.core;

public class CurrentGameModel {
    private final TicTacToeModel ticTacToeModel;
    private Game game;

    public CurrentGameModel(TicTacToeModel ticTacToeModel) {
        this.ticTacToeModel = ticTacToeModel;
    }

    public Game getGame() {
        return game;
    }

    public void move(int squareNumber) {
        game.move(squareNumber);

        if (game.isOver()) {
            ticTacToeModel.notifyUpdate(ModelUpdate.GAME_OVER);
        } else {
            ticTacToeModel.notifyUpdate(ModelUpdate.MAKE_MOVE);
        }
    }

    public void createGame(int boardSize) {
        game = new Game(new Board(boardSize));
        ticTacToeModel.notifyUpdate(ModelUpdate.CREATE_GAME);
    }
}
