package com.matthewglover.tictactoe.core;


public class GameTypeModel {
    private final GameType gameType;
    private final Player playerX;
    private final Player playerO;

    public GameTypeModel(GameType gameType) {
        this.gameType = gameType;
        playerX = configurePlayer(PlayerSymbol.X).build();
        playerO = configurePlayer(PlayerSymbol.O).build();
    }

    private PlayerBuilder configurePlayer(PlayerSymbol playerSymbol) {
        PlayerType playerType = getPlayerType(playerSymbol);
        return new PlayerBuilder().withPlayerSymbol(playerSymbol).withPlayerType(playerType);
    }

    public PlayerType getPlayerType(PlayerSymbol playerSymbol) {
        return gameType.getPlayerType(playerSymbol);
    }

    public Player getPlayer(PlayerSymbol playerSymbol) {
        return playerSymbol == PlayerSymbol.X
                ? playerX
                : playerO;
    }

    public GameType getGameType() {
        return gameType;
    }
}
