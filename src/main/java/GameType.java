import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum GameType {
    HUMAN_HUMAN,
    HUMAN_COMPUTER,
    COMPUTER_COMPUTER;

    public String getDescription() {
        return Arrays.stream(this.toString().split("_"))
                .map(String::toLowerCase)
                .map(capitalize())
                .collect(Collectors.joining(" vs "));
    }

    private Function<String, String> capitalize() {
        return (String player) -> Character.toUpperCase(player.charAt(0)) + player.substring(1);
    }
}
