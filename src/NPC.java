import java.util.Random;

/**
 * Abstract class providing common logic for all NPC subclasses
 */
public abstract class NPC {
    private final int successFactor = 50;
    private Random rand = new Random();

    /**
     * Rolls to determine whether an attempt based off of the pilot skill is
     * successful
     *
     * @param player the player in the game
     * @return true or false
     */
    protected boolean rollPilot(Player player) {
        return (roll(player.getPilot(), player.getDiff()) >= successFactor);
    }

    /**
     * Rolls to determine whether an attempt based off the fighter skill is
     * successful
     *
     * @param player the player in the game
     * @return true or false
     */
    protected boolean rollFighter(Player player) {
        return (roll(player.getFighter(), player.getDiff()) >= successFactor);
    }

    /**
     * Rolls to determine whether an attempt based off the merchant skill is
     * successful
     *
     * @param player the player in the game
     * @return true or false
     */
    protected boolean rollMerchant(Player player) {
        return (roll(player.getMerchant(), player.getDiff()) >= successFactor);
    }

    /**
     * Rolls to determine whether an attempt based off the engineer skill is
     * successful
     *
     * @param player the player in the game
     * @return true or false
     */
    protected boolean rollEngineer(Player player) {
        return (roll(player.getEngineer(), player.getDiff()) >= successFactor);
    }

    /**
     * Returns a value from 0 to 100, with the value increasing
     * proportionally to the skill level
     *
     * @param skill      skill level
     * @param difficulty the player's set difficulty
     * @return a double between 0 and 100, exclusive.
     */
    private double roll(int skill, Difficulty difficulty) {
        return (rand.nextInt(101)) * ((double) skill / difficulty.getSkillPoints());
    }
}
