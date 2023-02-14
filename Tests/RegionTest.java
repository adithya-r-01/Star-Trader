import com.sun.jdi.AbsentInformationException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class RegionTest {
    private Region[] regions;
    private Random rand;

    @Before
    public void setUp() throws MaximumRegionsException {
        Region.populateRegions();
        regions = Region.getRegions();
        rand = new Random();
    }

    @After
    public void breakDown() {
        for (int i = 0; i < regions.length; i++) {
            regions[i].setVisited(false);
        }
        Region.clearRegions();
    }
    @Test
    public void moreThan10() {
        boolean thrown = false;
        try {
            for (int i = 0; i <= 10; i++) {
                new Region();
            }
        } catch (MaximumRegionsException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    public void testDifferentNames() throws AbsentInformationException {
        for (int i = 0; i < regions.length; i++) {
            regions[i].setVisited(true);
        }
        for (int i = 0; i < regions.length; i++) {
            for (int j = i + 1; j < regions.length; j++) {
                assertNotSame(regions[i].getName(), regions[j].getName());
            }
        }
    }

    @Test
    public void testDifferentDiscriptions() throws AbsentInformationException {
        for (int i = 0; i < regions.length; i++) {
            regions[i].setVisited(true);
        }
        for (int i = 0; i < regions.length; i++) {
            for (int j = i + 1; j < regions.length; j++) {
                assertNotSame(regions[i].getDescription(), regions[j].getDescription());
            }
        }
    }

    /**
     * Tests to see if the name field is hidden when the region hasn't been
     * visited
     */
    /*@Test
    public void nameHidden() {
        boolean thrown = false;
        try {
            regions[rand.nextInt(10)].getName();
        } catch (AbsentInformationException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }*/

    /**
     * Tests to see if the description field is hidden when the region hasn't
     * been visited
     */
    /*@Test
    public void descriptionHidden() {
        boolean thrown = false;
        try {
            regions[rand.nextInt(10)].getDescription();
        } catch (AbsentInformationException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }*/

    /**
     * Tests to see if the techLevel field is hidden when the region hasn't
     * been visited
     */
    @Test
    public void techLevelHidden() {
        boolean thrown = false;
        try {
            regions[rand.nextInt(10)].getTechLevel();
        } catch (AbsentInformationException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    /**
     * Tests to see if the name field is visible when the region has been
     * visited
     * @throws AbsentInformationException this shouldn't happen
     */
    @Test
    public void nameVisible() throws AbsentInformationException {
        Region r = regions[rand.nextInt(10)];
        r.setVisited(true);
        assertNotNull(r.getName());
    }

    /**
     * Tests to see if the description field is visible when the region has
     * been visited
     * @throws AbsentInformationException this shouldn't happen
     */
    @Test
    public void descriptionVisible() throws AbsentInformationException {
        Region r = regions[rand.nextInt(10)];
        r.setVisited(true);
        assertNotNull(r.getDescription());
    }

    /**
     * Tests to see if the techLevel field is visible when the region has
     * been visited
     */
    @Test
    public void techLevelVisible() {
        boolean thrown = false;
        try {
            Region r = regions[rand.nextInt(10)];
            r.setVisited(true);
            r.getTechLevel();
        } catch (AbsentInformationException e) {
            thrown = true;
        }
        assertFalse(thrown);

    }
}