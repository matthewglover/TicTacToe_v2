public interface GameUI {
    int promptForMove(Player player, Board board);

    void reportDraw();

    void reportWinner(Player player);

    boolean promptPlayAgain();

    int promptForBoardSize(int minBoardSize, int maxBoardSize);
}
