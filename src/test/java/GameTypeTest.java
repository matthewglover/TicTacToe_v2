import org.junit.Test;

import static org.junit.Assert.*;

public class GameTypeTest {

    @Test
    public void HUMAN_HUMANisHuman_vs_Human() {
        assertEquals("Human vs Human", GameType.HUMAN_HUMAN.getDescription());
    }

    @Test
    public void HUMAN_COMPUTERisHuman_vs_Computer() {
        assertEquals("Human vs Computer", GameType.HUMAN_COMPUTER.getDescription());
    }

    @Test
    public void COMPUTER_COMPUTERisComputer_vs_Computer() {
        assertEquals("Computer vs Computer", GameType.COMPUTER_COMPUTER.getDescription());
    }
}