public interface GameUI {
    int promptForMove(Player player, BoardReader board);
    void reportDraw();
    void reportWinner(Player player);
    void reportMoveError();
    boolean promptPlayAgain();
}
