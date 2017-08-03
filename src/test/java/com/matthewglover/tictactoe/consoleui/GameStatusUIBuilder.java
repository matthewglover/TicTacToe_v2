package com.matthewglover.tictactoe.consoleui;


public class GameStatusUIBuilder {
    private final IOTestHelper ioTestHelper = new IOTestHelper();
    private final OldGameStatusUI gameStatusUI;

    public GameStatusUIBuilder(String input) {
        ioTestHelper.setInputStream(input);
        gameStatusUI = new OldGameStatusUI(ioTestHelper.getInputStream(), ioTestHelper.getOutputStream());
    }

    public IOTestHelper getIoTestHelper() {
        return ioTestHelper;
    }

    public OldGameStatusUI getGameStatusUI() {
        return gameStatusUI;
    }
}
