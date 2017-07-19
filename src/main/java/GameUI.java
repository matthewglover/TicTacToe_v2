public interface GameUI {
    int promptForMove(PlayerSymbol playerSymbol, Board board);

    void reportDraw();

    void reportWinner(PlayerSymbol playerSymbol);

    boolean promptPlayAgain();

    int promptForBoardSize(int minBoardSize, int maxBoardSize);
}
