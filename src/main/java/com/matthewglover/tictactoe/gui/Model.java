package com.matthewglover.tictactoe.gui;

import com.matthewglover.tictactoe.core.*;
import javafx.application.Platform;
import javafx.concurrent.Task;

import java.util.Observable;
import java.util.Observer;

public class Model extends Observable {
    private Game game;
    private GameType gameType;
    private int computerMoveDelay = 0;
    private SimplePlayer playerX;
    private SimplePlayer playerO;

    public PlayerType getNextPlayerType() {
        return getPlayerType(getNextPlayerSymbol());
    }

    public int getBoardSize() {
        return game.getBoardSize();
    }

    public PlayerSymbol getBoardSquare(int squareNumber) {
        return game.getBoardSquare(squareNumber);
    }

    public void setComputerMoveDelay(int withDelay) {
        this.computerMoveDelay = withDelay;
    }

    public void move(int squareNumber) {
        game.move(squareNumber);
    }

    public void setGameType(GameType gameType) {
        this.gameType = gameType;
        playerX = createPlayer(PlayerSymbol.X, getPlayerXType());
        playerO = createPlayer(PlayerSymbol.O, getPlayerOType());
        notifyUpdate(ModelUpdate.SET_GAME_TYPE);
    }

    public void createGame(int boardSize) {
        game = new Game(boardSize);
        GameObserver gameObserver = new GameObserver(this);
        game.addObserver(gameObserver);
        game.start();
        notifyUpdate(ModelUpdate.CREATE_GAME);
    }

    private SimplePlayer createPlayer(PlayerSymbol playerSymbol, PlayerType playerType) {
        return (playerType == PlayerType.HUMAN)
                ? new SimpleHumanPlayer(playerSymbol)
                : new SimpleComputerPlayer(playerSymbol);
    }

    private void notifyUpdate(ModelUpdate modelUpdate) {
        setChanged();
        notifyObservers(modelUpdate);
    }

    public GameType getGameType() {
        return gameType;
    }

    public PlayerType getPlayerXType() {
        return gameType.getPlayer1();
    }

    public PlayerType getPlayerOType() {
        return gameType.getPlayer2();
    }

    public PlayerType getPlayerType(PlayerSymbol playerSymbol) {
        return gameType.getPlayerType(playerSymbol);
    }

    public PlayerSymbol getNextPlayerSymbol() {
        return game.getNextPlayerSymbol();
    }

    public PlayerSymbol getGameWinner() {
        return game.getWinner();
    }

    public boolean isGameOver() {
        return game.isOver();
    }

    public void reset() {
        game = null;
        gameType = null;
        playerX = null;
        playerO = null;
        notifyUpdate(ModelUpdate.START_NEW_GAME);
    }

    private void notifyPlayer() {
        SimplePlayer nextPlayer = getNextPlayerSymbol() == PlayerSymbol.X
                ? playerX
                : playerO;

        if (nextPlayer.isComputer()) {
            runMove(nextPlayer);
        }
    }

    private void runMove(SimplePlayer nextPlayer) {
        Task<Void> task = new Task<Void>() {

            @Override
            protected Void call() throws Exception {
                try {
                    Thread.sleep(computerMoveDelay);
                } catch (Exception e) {
                }

                Platform.runLater(() -> move(nextPlayer.getMove(game)));
                return null;
            }
        };

        new Thread(task).start();
    }

    public boolean isGameWinner() {
        return game.isWinner();
    }

    private class GameObserver implements Observer {

        private final Model model;

        public GameObserver(Model model) {
            this.model = model;
        }
        @Override
        public void update(Observable o, Object arg) {
            if (game.isOver()) {
                model.notifyUpdate(ModelUpdate.GAME_OVER);
            } else {
                model.notifyUpdate(ModelUpdate.MOVE);
                model.notifyPlayer();
            }
        }

    }
}
