package com.matthewglover.tictactoe.core;

import java.util.List;

public class GameTypeModel {
    private GameType gameType;
    private Player playerX;
    private Player playerO;

    public GameTypeModel(GameType gameType) {
        buildPlayers(gameType);
    }

    public GameTypeModel(GameType gameType, List<Integer> moveSequence) {
        buildPlayers(gameType);
        addMoves(moveSequence);
    }

    public GameType getGameType() {
        return gameType;
    }

    public PlayerType getPlayerType(PlayerSymbol playerSymbol) {
        return gameType.getPlayerType(playerSymbol);
    }

    public Player getPlayer(PlayerSymbol playerSymbol) {
        return playerSymbol.isX()
                ? playerX
                : playerO;
    }

    private void buildPlayers(GameType gameType) {
        this.gameType = gameType;
        playerX = buildPlayer(PlayerSymbol.X);
        playerO = buildPlayer(PlayerSymbol.O);
    }

    private Player buildPlayer(PlayerSymbol playerSymbol) {
        switch (getPlayerType(playerSymbol)) {
            case HUMAN:
                return new HumanPlayer(playerSymbol);
            case COMPUTER:
                return new AlphaBetaPlayer(playerSymbol);
            case REPLAY:
                return new ReplayPlayer(playerSymbol);
            default:
                return null;
        }
    }

    private void addMoves(List<Integer> moveSequence) {
        ((ReplayPlayer) playerX).addMoves(moveSequence);
        ((ReplayPlayer) playerO).addMoves(moveSequence);
    }
}
