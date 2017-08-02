package com.matthewglover.tictactoe.core;

import javafx.application.Platform;

import java.util.Observable;

public class TicTacToeModel extends Observable {
    private int computerMoveDelay = 0;
    private final CurrentGameModel currentGameModel;
    private final CurrentGameTypeModel currentGameTypeModel;

    public TicTacToeModel() {
        super();
        currentGameModel = new CurrentGameModel(this);
        currentGameTypeModel = new CurrentGameTypeModel(this);
    }

    public Board getCurrentBoard() {
        return getCurrentGame().getBoard();
    }

    public void setCurrentBoard(int boardSize) {
        currentGameModel.createGame(boardSize);
    }

    public CurrentGameTypeModel getCurrentGameTypeModel() {
        return currentGameTypeModel;
    }

    public Game getCurrentGame() {
        return currentGameModel.getGame();
    }

    public PlayerType getNextPlayerType() {
        PlayerSymbol playerSymbol = getCurrentGame().getNextPlayerSymbol();
        return currentGameTypeModel.getPlayerType(playerSymbol);
    }

    public void setComputerMoveDelay(int computerMoveDelay) {
        this.computerMoveDelay = computerMoveDelay;
    }

    public void setCurrentGameType(GameType gameType) {
        currentGameTypeModel.setGameType(gameType);
    }

    public void createGame(int boardSize) {
        currentGameModel.createGame(boardSize);
    }

    public void gameMove(int squareNumber) {
        currentGameModel.move(squareNumber);
    }

    public void reset() {
        currentGameModel.reset();
        currentGameTypeModel.reset();
        notifyUpdate(ModelUpdate.START_NEW_GAME);
    }

    protected void notifyUpdate(ModelUpdate modelUpdate) {
        setChanged();
        notifyObservers(modelUpdate);

        if (isComputerPlayersMove(modelUpdate)) {
            DelayedRunner.run(computerMoveDelay, getRunComputerMove());
        }
    }

    private SimplePlayer getNextPlayer() {
        PlayerSymbol nextPlayerSymbol = getCurrentGame().getNextPlayerSymbol();
        return currentGameTypeModel.getPlayer(nextPlayerSymbol);
    }

    private boolean isComputerPlayersMove(ModelUpdate modelUpdate) {
        return modelUpdate == ModelUpdate.MOVE && getNextPlayer().isComputer();
    }

    private Runnable getRunComputerMove() {
        return () -> Platform.runLater(() -> gameMove(getNextPlayer().getMove(currentGameModel.getGame())));
    }
}