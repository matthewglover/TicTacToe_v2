package com.matthewglover.tictactoe.core;


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

    public CurrentGameTypeModel getCurrentGameTypeModel() {
        return currentGameTypeModel;
    }

    public CurrentGameModel getCurrentGameModel() {
        return currentGameModel;
    }

    public Board getCurrentBoard() {
        return getCurrentGame().getBoard();
    }

    public void setCurrentBoardSize(int boardSize) {
        currentGameModel.createGame(boardSize);
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

    public void startNewGame() {
        currentGameModel.reset();
        currentGameTypeModel.reset();
        notifyUpdate(ModelUpdate.SETUP_NEW_GAME);
    }

    public Player getNextPlayer() {
        PlayerSymbol nextPlayerSymbol = getCurrentGame().getNextPlayerSymbol();
        return currentGameTypeModel.getPlayer(nextPlayerSymbol);
    }

    protected void notifyUpdate(ModelUpdate modelUpdate) {
        setChanged();
        notifyObservers(modelUpdate);

        if (isComputerPlayersMove(modelUpdate)) {
            DelayedRunner.run(computerMoveDelay, getRunComputerMove());
        }
    }

    private boolean isComputerPlayersMove(ModelUpdate modelUpdate) {
        return modelUpdate.isGameMove() && getNextPlayer().isComputer();
    }

    protected Runnable getRunComputerMove() {
        return () -> gameMove(getNextPlayer().getMove(currentGameModel.getGame()));
    }
}
