import java.util.Observable;
import java.util.Observer;

public class TicTacToeRunner implements Observer {
    private final GameOptionsUI gameOptionsUI;
    private final PlayerUI playerUI;
    private GameStatusUI gameStatusUI;
    private GameOptions gameOptions;
    private Game game;
    private Player playerX;
    private Player playerO;
    private GameStatus gameStatus;
    private int gameCount;

    public static void main(String[] args) {
        GameOptionsUI gameOptionsUI = new GameOptionsUI(System.in, System.out);
        PlayerUI playerUI = new PlayerUI(System.in, System.out);
        GameStatusUI gameStatusUI = new GameStatusUI(System.in, System.out);
        TicTacToeRunner ticTacToeRunner = new TicTacToeRunner(gameOptionsUI, playerUI, gameStatusUI);
        ticTacToeRunner.execute();
    }

    public TicTacToeRunner(GameOptionsUI gameOptionsUI, PlayerUI playerUI, GameStatusUI gameStatusUI) {
        this.gameOptionsUI = gameOptionsUI;
        this.playerUI = playerUI;
        this.gameStatusUI = gameStatusUI;
    }

    @Override
    public void update(Observable o, Object arg) {
        boolean isActive = (Boolean) arg;

        if (isActive) {
            execute();
        }
    }

    public PlayerSymbol getWinner() {
        return game.getWinner();
    }

    public int gameCount() {
        return gameCount;
    }

    public void execute() {
        buildGameOptions();
        buildGameStatus();
        buildPlayers();
        buildGame();
        registerGameObservers();
        observeGameStatus();
        startGame();
    }

    private void buildGameOptions() {
        gameOptions = new GameOptions(gameOptionsUI);
        gameOptions.execute();
    }

    private void buildGameStatus() {
        gameStatus = new GameStatus(gameStatusUI);
    }

    private void buildPlayers() {
        playerX = new Player(playerUI, PlayerSymbol.X);
        playerO = new Player(playerUI, PlayerSymbol.O);
    }

    private void buildGame() {
        game = new Game(gameOptions.getBoardSize());
        gameCount++;
    }

    private void registerGameObservers() {
        game.addObserver(playerX);
        game.addObserver(playerO);
        game.addObserver(gameStatus);
    }

    private void observeGameStatus() {
        gameStatus.addObserver(this);
    }

    private void startGame() {
        game.start();
    }
}
