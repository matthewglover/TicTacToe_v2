import com.mattheglover.tictactoe.core.AlphaBeta;
import com.mattheglover.tictactoe.core.Game;
import com.mattheglover.tictactoe.core.PlayerSymbol;

public class ComputerPlayer extends Player {

    public ComputerPlayer(PlayerSymbol playerSymbol) {
        super(playerSymbol);
    }

    @Override
    protected void makeMove(Game game) {
        game.move(AlphaBeta.run(game).getMove());
    }
}
