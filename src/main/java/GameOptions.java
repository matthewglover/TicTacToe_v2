public class GameOptions {
    private final GameOptionsUI gameOptionsUI;
    private int boardSize;
    private GameType gameType;

    public GameOptions(GameOptionsUI gameOptionsUI) {
        this.gameOptionsUI = gameOptionsUI;
    }

    public int getBoardSize() {
        return boardSize;
    }

    public GameType getGameType() {
        return gameType;
    }

    public void execute() {
        obtainBoardSize();
        obtainGameType();
    }

    private void obtainGameType() {
        gameOptionsUI.printRequestGameType();
        gameType = gameOptionsUI.promptForGameType();
    }

    private void obtainBoardSize() {
        gameOptionsUI.printRequestBoardSize();
        boardSize = gameOptionsUI.promptForBoardSize();
    }
}
