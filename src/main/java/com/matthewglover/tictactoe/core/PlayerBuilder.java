package com.matthewglover.tictactoe.core;


public class PlayerBuilder {
    private PlayerSymbol playerSymbol;
    private PlayerType playerType;

    public PlayerBuilder withPlayerSymbol(PlayerSymbol playerSymbol) {
        this.playerSymbol = playerSymbol;
        return this;
    }

    public PlayerBuilder withPlayerType(PlayerType playerType) {
        this.playerType = playerType;
        return this;
    }

    public Player build() {
        switch (playerType) {
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
}
