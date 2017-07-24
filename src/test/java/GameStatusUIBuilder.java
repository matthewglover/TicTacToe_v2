import com.matthewglover.tictactoe.consoleui.GameStatusUI;

public class GameStatusUIBuilder {
    private final IOTestHelper ioTestHelper = new IOTestHelper();
    private final GameStatusUI gameStatusUI;

    public GameStatusUIBuilder(String input) {
        ioTestHelper.setInputStream(input);
        gameStatusUI = new GameStatusUI(ioTestHelper.getInputStream(), ioTestHelper.getOutputStream());
    }

    public IOTestHelper getIoTestHelper() {
        return ioTestHelper;
    }

    public GameStatusUI getGameStatusUI() {
        return gameStatusUI;
    }
}
