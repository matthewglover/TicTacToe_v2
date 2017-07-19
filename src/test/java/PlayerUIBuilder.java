public class PlayerUIBuilder {
    private final IOTestHelper ioTestHelper = new IOTestHelper();
    private final PlayerUI playerUI;

    public PlayerUIBuilder(String input) {
        ioTestHelper.setInputStream(input);
        playerUI = new PlayerUI(ioTestHelper.getInputStream(), ioTestHelper.getOutputStream());
    }

    public IOTestHelper getIoTestHelper() {
        return ioTestHelper;
    }

    public PlayerUI getPlayerUI() {
        return playerUI;
    }
}
