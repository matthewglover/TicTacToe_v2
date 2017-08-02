package com.matthewglover.tictactoe.core;

import java.util.Observable;
import java.util.Observer;

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
    }

    public void reset() {
        this.game = null;
    }

    public void createGame(int boardSize) {
        game = new Game(boardSize);
        game.addObserver(new GameObserver(ticTacToeModel));
        game.start();
        ticTacToeModel.notifyUpdate(ModelUpdate.CREATE_GAME);
    }

    private class GameObserver implements Observer {
        private final TicTacToeModel ticTacToeModel;

        public GameObserver(TicTacToeModel ticTacToeModel) {
            this.ticTacToeModel = ticTacToeModel;
        }

        @Override
        public void update(Observable o, Object arg) {
            if (game.isOver()) {
                ticTacToeModel.notifyUpdate(ModelUpdate.GAME_OVER);
            } else {
                ticTacToeModel.notifyUpdate(ModelUpdate.MOVE);
            }
        }

    }
}
