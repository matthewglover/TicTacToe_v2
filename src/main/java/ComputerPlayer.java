import java.util.Observable;
import java.util.Observer;

public class ComputerPlayer implements Observer {
    private final PlayerSymbol playerSymbol;

    public ComputerPlayer(PlayerSymbol playerSymbol) {
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

    private void makeMove(Game game) {
        game.move(AlphaBeta.run(game).getMove());
    }
}
