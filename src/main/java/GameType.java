import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum GameType {
    HUMAN_HUMAN,
    HUMAN_COMPUTER,
    COMPUTER_COMPUTER;

    private PlayerType player2;

    public String getDescription() {
        return Arrays.stream(getPlayers())
                .map(String::toLowerCase)
                .map(capitalize())
                .collect(Collectors.joining(" vs "));
    }

    public PlayerType getPlayer1() {
        return PlayerType.valueOf(getPlayers()[0]);
    }

    public PlayerType getPlayer2() {
        return PlayerType.valueOf(getPlayers()[1]);
    }

    private Function<String, String> capitalize() {
        return (String player) -> Character.toUpperCase(player.charAt(0)) + player.substring(1);
    }

    private String[] getPlayers() {
        return this.toString().split("_");
    }
}
