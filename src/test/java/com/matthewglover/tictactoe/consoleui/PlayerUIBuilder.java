package com.matthewglover.tictactoe.consoleui;

public class PlayerUIBuilder {
    private final IOTestHelper ioTestHelper = new IOTestHelper();
    private final HumanPlayerUI humanPlayerUI;

    public PlayerUIBuilder(String input) {
        ioTestHelper.setInputStream(input);
        humanPlayerUI = new HumanPlayerUI(ioTestHelper.getInputStream(), ioTestHelper.getOutputStream());
    }

    public IOTestHelper getIoTestHelper() {
        return ioTestHelper;
    }

    public HumanPlayerUI getHumanPlayerUI() {
        return humanPlayerUI;
    }
}
