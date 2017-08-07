package com.matthewglover.tictactoe.core;


import java.util.Observable;

public class TicTacToeModel extends Observable {
    private int computerMoveDelay = 0;
    private CurrentGameTypeModel currentGameTypeModel;
    private CurrentGameModel currentGameModel;

    public TicTacToeModel() {
        super();
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

    public void setComputerMoveDelay(int computerMoveDelay) {
        this.computerMoveDelay = computerMoveDelay;
    }

    public void startNewGame() {
        currentGameTypeModel = new CurrentGameTypeModel(this);
        currentGameModel = new CurrentGameModel(this);
        notifyUpdate(ModelUpdate.SETUP_NEW_GAME);
    }

    public void setCurrentBoardSize(int boardSize) {
        currentGameModel.createGame(boardSize);
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

    protected void notifyUpdate(ModelUpdate modelUpdate) {
        setChanged();
        notifyObservers(modelUpdate);

        if (isComputerPlayersMove(modelUpdate)) {
            DelayedRunner.run(computerMoveDelay, getRunComputerMove());
        }
    }

    protected Runnable getRunComputerMove() {
        return () -> {
            ComputerPlayer computerPlayer = (ComputerPlayer) getNextPlayer();
            int nextMove = computerPlayer.getMove(currentGameModel.getGame());
            gameMove(nextMove);
        };
    }

    private boolean isComputerPlayersMove(ModelUpdate modelUpdate) {
        return modelUpdate.isGameMove() && getNextPlayer().isComputer();
    }
}
