public class TicTacToeRunner {
    private final GameOptionsUI gameOptionsUI;
    private final PlayerUI playerUI;
    private GameOptions gameOptions;
    private Game game;
    private Player playerX;
    private Player playerO;

    public static void main(String[] args) {
        GameOptionsUI gameOptionsUI = new GameOptionsUI(System.in, System.out);
        PlayerUI playerUI = new PlayerUI(System.in, System.out);
        TicTacToeRunner ticTacToeRunner = new TicTacToeRunner(gameOptionsUI, playerUI);
        ticTacToeRunner.execute();
    }

    public TicTacToeRunner(GameOptionsUI gameOptionsUI, PlayerUI playerUI) {
        this.gameOptionsUI = gameOptionsUI;
        this.playerUI = playerUI;
    }

    public void execute() {
        buildGameOptions();
        buildGame();
        startGame();
    }

    private void buildGameOptions() {
        gameOptions = new GameOptions(gameOptionsUI);
        gameOptions.execute();
    }

    private void buildGame() {
        game = new Game(gameOptions.getBoardSize());
        playerX = new Player(playerUI, PlayerSymbol.X);
        playerO = new Player(playerUI, PlayerSymbol.O);
        game.addObserver(playerX);
        game.addObserver(playerO);
    }

    private void startGame() {
        game.start();
    }

    public PlayerSymbol getWinner() {
        return game.getWinner();
    }
}
