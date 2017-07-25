package com.matthewglover.tictactoe.consoleui;

public class PlayerUIBuilder {
    private final IOTestHelper ioTestHelper = new IOTestHelper();
    private final HumanPlayerUI humanPlayerUI;
    private ComputerPlayerUI computerPlayerUI;

    public PlayerUIBuilder(String input) {
        ioTestHelper.setInputStream(input);
        humanPlayerUI = new HumanPlayerUI(ioTestHelper.getInputStream(), ioTestHelper.getOutputStream());
        computerPlayerUI = new ComputerPlayerUI(ioTestHelper.getInputStream(), ioTestHelper.getOutputStream());
    }

    public IOTestHelper getIoTestHelper() {
        return ioTestHelper;
    }

    public HumanPlayerUI getHumanPlayerUI() {
        return humanPlayerUI;
    }

    public ComputerPlayerUI getComputerPlayerUI() {
        return computerPlayerUI;
    }
}
