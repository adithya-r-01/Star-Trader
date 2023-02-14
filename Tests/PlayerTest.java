import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * JUnit test class for the Player class.
 */
public class PlayerTest {
    private Player p;

    /**
     * Initializes a fresh Player before each unit test
     */
    @Before
    public void setUpPlayer() {
        p = new Player();
    }

    @After
    public void breakDown() {
        Region.clearRegions();
    }

    /**
     * Tests setting a null name for the Player. If functioning as expected,
     * the setName method will throw an IllegalArgumentException
     */
    @Test
    public void setNullNameTest() {
        try {
            p.setName(null);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    /**
     * Tests setting an Empty string for the player's name. If functioning as expected,
     * the setName method will throw an IllegalArgumentException
     */
    @Test
    public void setEmptyStringNameTest() {
        try {
            p.setName("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Tests setting skills at a level higher than the maximum allotted
     * number for each difficulty.
     */
    @Test
    public void setInvalidSkills() {
        Difficulty diff = p.getDiff();
        int prevPilot = p.getPilot();
        int prevFighter = p.getFighter();
        int prevMerchant = p.getMerchant();
        int prevEngineer = p.getEngineer();

        if (diff == Difficulty.EASY) {
            p.setSkills(17, 0, 0, 0);
        } else if (diff == Difficulty.MEDIUM) {
            p.setSkills(13, 0, 0, 0);
        } else {
            p.setSkills(9, 0, 0, 0);


            assertEquals(prevPilot, p.getPilot());
            assertEquals(prevFighter, p.getFighter());
            assertEquals(prevMerchant, p.getMerchant());
            assertEquals(prevEngineer, p.getEngineer());
        }
    }

    /**
     * Tests setting valid skills numbers.
     */
    @Test
    public void setValidSkillsEasyTest() {
        p.setDiff(Difficulty.EASY);
        p.setSkills(9, 3, 4, 0);

        assertEquals(9, p.getPilot());
        assertEquals(3, p.getFighter());
        assertEquals(4, p.getMerchant());
        assertEquals(0, p.getEngineer());
    }

    /**
     * Tests setting a valid name for the Player.
     */
    @Test
    public void setValidNameTest() {
        try {
            p.setName("Bob");
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals("Bob", p.getName());
    }

    /**
     * Tests setting the difficulty for the Player
     */
    @Test
    public void setDiffTest() {
        p.setDiff(Difficulty.HARD);
        assertEquals(Difficulty.HARD, p.getDiff());

        p.setDiff(Difficulty.EASY);
        assertEquals(Difficulty.EASY, p.getDiff());

        p.setDiff(Difficulty.MEDIUM);
        assertEquals(Difficulty.MEDIUM, p.getDiff());
    }

    /**
     * Tests that changing the difficulty sets the number of credits to the
     * correct number.
     */
    @Test
    public void testCredits() {
        p.setDiff(Difficulty.EASY);
        assertEquals(2000, Difficulty.EASY.getStartingCredits());
    }


}