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

    public PlayerType getPlayerType(PlayerSymbol playerSymbol) {
        if (this == REPLAY) {
            return PlayerType.REPLAY;
        } else {
            int playerIndex = playerSymbol.getPlayerIndex();
            String playerName = getPlayerNames()[playerIndex];
            return PlayerType.valueOf(playerName);
        }
    }

    public static List<GameType> getSelectableValues() {
        return Arrays.stream(GameType.values())
                .filter(GameType::isSelectable)
                .collect(Collectors.toList());
    }

    public boolean isSelectable() {
        return this != REPLAY;
    }

    public String getDescription() {
        return Arrays.stream(getPlayerNames())
                .map(String::toLowerCase)
                .map(capitalize())
                .collect(Collectors.joining(" vs "));
    }

    private String[] getPlayerNames() {
        return this.toString().split("_");
    }

    private Function<String, String> capitalize() {
        return (String player) -> Character.toUpperCase(player.charAt(0)) + player.substring(1);
    }
}
