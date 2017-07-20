import java.util.Observable;
import java.util.Observer;

public abstract class Player implements Observer {
    protected final PlayerSymbol playerSymbol;

    public Player(PlayerSymbol playerSymbol) {
        this.playerSymbol = playerSymbol;
    }

    @Override
    public void update(Observable o, Object arg) {
        Game game = (Game) o;
        PlayerSymbol playerSymbol = (PlayerSymbol) arg;

        if (game.isOver()) {
            return;
        }

        if (playerSymbol == this.playerSymbol) {
            makeMove(game);
        }
    }

    protected abstract void makeMove(Game game);
}
