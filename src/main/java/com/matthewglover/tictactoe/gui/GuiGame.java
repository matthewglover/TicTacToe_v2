package com.matthewglover.tictactoe.gui;

import com.matthewglover.tictactoe.core.Game;
import com.matthewglover.tictactoe.core.PlayerSymbol;
import com.matthewglover.tictactoe.core.PlayerType;

import java.util.HashMap;
import java.util.Map;

public class GuiGame extends Game {
    Map<PlayerSymbol, PlayerType> playerTypes = new HashMap<>();

    public GuiGame(int boardSize, PlayerType playerTypeX, PlayerType playerTypeO) {
        super(boardSize);
        playerTypes.put(PlayerSymbol.X, playerTypeX);
        playerTypes.put(PlayerSymbol.O, playerTypeO);
    }

    public PlayerType getNextPlayerType() {
        return playerTypes.get(getNextPlayerSymbol());
    }
}
