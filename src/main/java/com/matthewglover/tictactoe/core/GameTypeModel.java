package com.matthewglover.tictactoe.core;


import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GameTypeModel {
    private final GameType gameType;
    private Player playerX;
    private Player playerO;

    public GameTypeModel(GameType gameType) {
        this.gameType = gameType;
        buildPlayers();
    }

    public GameTypeModel(GameType gameType, List<Integer> moveSequence) {
        this.gameType = gameType;
        buildPlayers();
        addMoves(moveSequence);
    }

    private void addMoves(List<Integer> moveSequence) {
        List<Integer> xMoves = getEvenItems(moveSequence);
        List<Integer> oMoves = getOddItems(moveSequence);
        ((ReplayPlayer) playerX).addMoves(xMoves);
        ((ReplayPlayer) playerO).addMoves(oMoves);
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

    private void buildPlayers() {
        playerX = configurePlayer(PlayerSymbol.X).build();
        playerO = configurePlayer(PlayerSymbol.O).build();
    }

    private List<Integer> getEvenItems(List<Integer> moveSequence) {
        return IntStream.range(0, moveSequence.size())
                .filter(n -> n % 2 == 0)
                .mapToObj(moveSequence::get)
                .collect(Collectors.toList());
    }

    private List<Integer> getOddItems(List<Integer> moveSequence) {
        return IntStream.range(0, moveSequence.size())
                .filter(n -> n % 2 != 0)
                .mapToObj(moveSequence::get)
                .collect(Collectors.toList());
    }
}
