import com.matthewglover.tictactoe.core.Game;

import java.util.Arrays;

public class GameTestHelper {
    public static void runGame(Game game, int[] moves) {
        game.start();
        Arrays.stream(moves).forEach(game::move);
    }
}
