import java.util.Observable;
import java.util.Observer;

public class TestGameObserver implements Observer {

    private PlayerSymbol nextPlayerSymbol;

    @Override
    public void update(Observable game, Object arg) {
        nextPlayerSymbol = (PlayerSymbol) arg;
    }

    public PlayerSymbol getNextPlayerSymbol() {
        return nextPlayerSymbol;
    }
}
