package com.matthewglover.tictactoe.core;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum GameType {
    HUMAN_HUMAN,
    HUMAN_COMPUTER,
    COMPUTER_HUMAN,
    COMPUTER_COMPUTER,
    REPLAY;

    public String getDescription() {
        return Arrays.stream(getPlayers())
                .map(String::toLowerCase)
                .map(capitalize())
                .collect(Collectors.joining(" vs "));
    }

    public PlayerType getPlayer1() {
        return PlayerType.valueOf(getPlayers()[0]);
    }

    public PlayerType getPlayer2() {
        return PlayerType.valueOf(getPlayers()[1]);
    }

    private String[] getPlayers() {
        return this.toString().split("_");
    }

    private Function<String, String> capitalize() {
        return (String player) -> Character.toUpperCase(player.charAt(0)) + player.substring(1);
    }

    public PlayerType getPlayerType(PlayerSymbol playerSymbol) {
        if (this == REPLAY) {
            return PlayerType.REPLAY;

        } else {
            return PlayerType.valueOf(getPlayers()[playerSymbol.getPlayerIndex()]);
        }
    }

    public boolean isSelectable() {
        return this != REPLAY;
    }

    public static List<GameType> getSelectableValues() {
        return Arrays.stream(GameType.values())
                .filter(GameType::isSelectable)
                .collect(Collectors.toList());
    }
}
