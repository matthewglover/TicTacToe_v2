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

    public PlayerType getPlayer1() {
        return gameType.getPlayer1();
    }

    public PlayerType getPlayer2() {
        return gameType.getPlayer2();
    }

    public void execute() {
        gameOptionsUI.clearScreen();
        obtainBoardSize();
        gameOptionsUI.clearScreen();
        obtainGameType();
        gameOptionsUI.clearScreen();
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
