package com.matthewglover.tictactoe.core;

import java.util.Observable;

public class TicTacToeModel extends Observable {
    private int computerMoveDelay = 0;
    private CurrentGameTypeModel currentGameTypeModel;
    private CurrentGameModel currentGameModel;
    private ModelUpdate lastUpdate;

    public CurrentGameTypeModel getCurrentGameTypeModel() {
        return currentGameTypeModel;
    }

    public Board getCurrentBoard() {
        return getCurrentGame().getBoard();
    }

    public Game getCurrentGame() {
        return currentGameModel.getGame();
    }

    public PlayerType getNextPlayerType() {
        PlayerSymbol playerSymbol = getCurrentGame().getNextPlayerSymbol();
        return currentGameTypeModel.getPlayerType(playerSymbol);
    }

    public Player getNextPlayer() {
        PlayerSymbol nextPlayerSymbol = getCurrentGame().getNextPlayerSymbol();
        return currentGameTypeModel.getPlayer(nextPlayerSymbol);
    }

    public boolean isBoardLocked() {
        return lastUpdate == ModelUpdate.LOCK_BOARD;
    }

    public void setComputerMoveDelay(int computerMoveDelay) {
        this.computerMoveDelay = computerMoveDelay;
    }

    public void setupNewGame() {
        currentGameTypeModel = new CurrentGameTypeModel(this);
        currentGameModel = new CurrentGameModel(this);
        notifyUpdate(ModelUpdate.SETUP_NEW_GAME);
    }

    public void setGameType(GameType gameType) {
        currentGameTypeModel.setGameType(gameType);
    }

    public void setCurrentBoardSize(int boardSize) {
        currentGameModel.createGame(boardSize);
    }

    public void createGame(int boardSize) {
        currentGameModel.createGame(boardSize);
    }

    public void gameMove(int squareNumber) {
        currentGameModel.move(squareNumber);
    }

    public void replayGame() {
        if (getCurrentGame().isOver()) {
            notifyUpdate(ModelUpdate.REPLAY_GAME);
        }
    }

    protected void notifyUpdate(ModelUpdate modelUpdate) {
        lastUpdate = modelUpdate;
        setChanged();
        notifyObservers(modelUpdate);

        if (isComputerPlayersMove(modelUpdate)) {
            notifyUpdate(ModelUpdate.LOCK_BOARD);
            DelayedRunner.run(computerMoveDelay, getRunComputerMove());
        }
    }

    private boolean isComputerPlayersMove(ModelUpdate modelUpdate) {
        return modelUpdate.isGameMove() &&
                getNextPlayer().isComputer() &&
                !isBoardLocked();
    }

    protected Runnable getRunComputerMove() {
        return () -> {
            ComputerPlayer computerPlayer = (ComputerPlayer) getNextPlayer();
            int nextMove = computerPlayer.getMove(currentGameModel.getGame());
            gameMove(nextMove);
        };
    }
}
