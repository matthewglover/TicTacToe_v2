package com.matthewglover.tictactoe.core;


public class CurrentGameTypeModel {
    private final TicTacToeModel ticTacToeModel;
    private GameType gameType;
    private SimplePlayer playerX;
    private SimplePlayer playerO;

    public CurrentGameTypeModel(TicTacToeModel ticTacToeModel) {
        this.ticTacToeModel = ticTacToeModel;
    }

    public void setGameType(GameType gameType) {
        this.gameType = gameType;
        playerX = createPlayer(PlayerSymbol.X);
        playerO = createPlayer(PlayerSymbol.O);
        ticTacToeModel.notifyUpdate(ModelUpdate.SET_GAME_TYPE);
    }

    public PlayerType getPlayerType(PlayerSymbol playerSymbol) {
        return gameType.getPlayerType(playerSymbol);
    }

    public void reset() {
        gameType = null;
        playerX = null;
        playerO = null;
    }

    public SimplePlayer getPlayer(PlayerSymbol playerSymbol) {
        return playerSymbol == PlayerSymbol.X
                ? playerX
                : playerO;
    }

    public GameType getGameType() {
        return gameType;
    }

    private SimplePlayer createPlayer(PlayerSymbol playerSymbol) {
        return (gameType.getPlayerType(playerSymbol) == PlayerType.HUMAN)
                ? new SimpleHumanPlayer(playerSymbol)
                : new SimpleComputerPlayer(playerSymbol);
    }
}
