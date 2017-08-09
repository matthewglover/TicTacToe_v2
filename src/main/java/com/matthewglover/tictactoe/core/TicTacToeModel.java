package com.matthewglover.tictactoe.core;

import java.util.Observable;

public class TicTacToeModel extends Observable {
    private int computerMoveDelay = 0;
    private GameTypeModel gameTypeModel;
    private ModelUpdate lastUpdate;
    private Game game;

    public GameTypeModel getGameTypeModel() {
        return gameTypeModel;
    }

    public Board getBoard() {
        return game.getBoard();
    }

    public Game getGame() {
        return game;
    }

    public Player getNextPlayer() {
        return gameTypeModel.getPlayer(game.getNextPlayerSymbol());
    }

    public boolean isBoardLocked() {
        return lastUpdate == ModelUpdate.LOCK_BOARD;
    }

    public void setComputerMoveDelay(int computerMoveDelay) {
        this.computerMoveDelay = computerMoveDelay;
    }

    public void setupNewGame() {
        notifyUpdate(ModelUpdate.SETUP_NEW_GAME);
    }

    public void setGameType(GameType gameType) {
        gameTypeModel = new GameTypeModel(gameType);
        notifyUpdate(ModelUpdate.SET_GAME_TYPE);
    }

    public void createGame(int boardSize) {
        game = new Game(new Board(boardSize));
        notifyUpdate(ModelUpdate.CREATE_GAME);
    }

    public void gameMove(int squareNumber) {
        game.move(squareNumber);
        notifyGameUpdate();
    }

    public void replayGame() {
        if (game.isOver()) {
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

    private void notifyGameUpdate() {
        if (game.isOver()) {
            notifyUpdate(ModelUpdate.GAME_OVER);
        } else {
            notifyUpdate(ModelUpdate.GAME_MOVE);
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
            int nextMove = computerPlayer.getMove(game);
            gameMove(nextMove);
        };
    }
}
