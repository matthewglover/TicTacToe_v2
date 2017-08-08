package com.matthewglover.tictactoe.gui;

import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class BoardUIHelper {
    public static Text getSquareTextNode(StackPane tile) {
        return (Text) tile.getChildren().stream().filter(node -> node instanceof Text).findFirst().get();
    }
}
