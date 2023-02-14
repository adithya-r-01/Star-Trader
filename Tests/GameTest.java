import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest {
    //private Game game; // Don't have a game class anymore
    private Region[] arr;

    @Before
    public void setUp() {
        Region.clearRegions();
        //game = new Game();
        arr = Region.getRegions();
    }


    @Test
    public void regionsNotNull() {
        for (Region region : arr) {
            assertNotNull(region);
        }
    }

    @Test
    public void tenRegions() {
        int count = 0;
        for (Region region : arr) {
            if (region != null) {
                count++;
            }
        }
        assertEquals(10, count);
    }

    @Test public void playerNotNull() {
        //assertNotNull(game.getPlayer());
    }
}