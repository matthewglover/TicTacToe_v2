package com.matthewglover.tictactoe.consoleui;

import com.matthewglover.tictactoe.core.*;

import java.util.Observable;
import java.util.Observer;

public class TicTacToeRunner implements Observer {
    private final GameOptionsUI gameOptionsUI;
    private final HumanPlayerUI humanPlayerUI;
    private GameStatusUI gameStatusUI;
    private final int withDelay;
    private GameOptions gameOptions;
    private Game game;
    private Player playerX;
    private Player playerO;
    private GameStatus gameStatus;
    private int gameCount;

    public static void main(String[] args) {
        GameOptionsUI gameOptionsUI = new GameOptionsUI(System.in, System.out);
        HumanPlayerUI humanPlayerUI = new HumanPlayerUI(System.in, System.out);
        GameStatusUI gameStatusUI = new GameStatusUI(System.in, System.out);
        TicTacToeRunner ticTacToeRunner = new TicTacToeRunner(gameOptionsUI, humanPlayerUI, gameStatusUI, 2000);
        ticTacToeRunner.execute();
    }

    public TicTacToeRunner(GameOptionsUI gameOptionsUI, HumanPlayerUI humanPlayerUI, GameStatusUI gameStatusUI, int withDelay) {
        this.gameOptionsUI = gameOptionsUI;
        this.humanPlayerUI = humanPlayerUI;
        this.gameStatusUI = gameStatusUI;
        this.withDelay = withDelay;
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

    public Player getPlayerX() {
        return playerX;
    }

    public Player getPlayerO() {
        return playerO;
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
        playerX = buildPlayer(PlayerSymbol.X, gameOptions.getPlayer1());
        playerO = buildPlayer(PlayerSymbol.O, gameOptions.getPlayer2());
    }

    private Player buildPlayer(PlayerSymbol playerSymbol, PlayerType playerType) {
        return (playerType.isHuman())
                ? new HumanPlayer(humanPlayerUI, playerSymbol)
                : new ComputerPlayer(playerSymbol, withDelay);
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
