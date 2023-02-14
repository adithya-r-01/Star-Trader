/**
 * An enum describing the Difficulty levels that a Player in the game can
 * choose.
 */
public enum Difficulty {

    EASY(16, 2000), MEDIUM(12, 1500), HARD(8, 1000);

    private int startingCredits;
    private int skillPoints;

    /**
     * Constructor for a Difficulty.
     *
     * @param skillPoints     the maximum number of skill points that can be
     *                        allocated
     * @param startingCredits starting number of credits
     */
    Difficulty(int skillPoints, int startingCredits) {
        this.skillPoints = skillPoints;
        this.startingCredits = startingCredits;
    }

    /**
     * Returns the max number of skill points for the Difficulty
     *
     * @return skillPoints
     */
    public int getSkillPoints() {
        return skillPoints;
    }

    /**
     * Returns the starting number of credits for the Difficulty
     *
     * @return startingCredits;
     */
    public int getStartingCredits() {
        return startingCredits;
    }

}