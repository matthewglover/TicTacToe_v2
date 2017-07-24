import com.matthewglover.tictactoe.consoleui.GameOptionsUI;

public class GameOptionsUIBuilder {
    private final IOTestHelper ioTestHelper = new IOTestHelper();
    private final GameOptionsUI gameOptionsUI;

    public GameOptionsUIBuilder(String input) {
        ioTestHelper.setInputStream(input);
        gameOptionsUI = new GameOptionsUI(ioTestHelper.getInputStream(), ioTestHelper.getOutputStream());
    }

    public IOTestHelper getIoTestHelper() {
        return ioTestHelper;
    }

    public GameOptionsUI getGameOptionsUI() {
        return gameOptionsUI;
    }
}

