/**
 * Class that describes a Player in the Space Trader Remake
 *
 * @author jrollins33
 * @version M5
 */
public class Player {

    private int pilot;
    private int fighter;
    private int merchant;
    private int engineer;
    private int credits;
    private String name;
    private Difficulty diff;
    private Region region;
    private Ship ship;

    /**
     * Constructs a new Player
     */
    public Player() {
        pilot = 0;
        fighter = 0;
        merchant = 0;
        engineer = 0;
        name = "";
        diff = Difficulty.MEDIUM;
        credits = diff.getStartingCredits();
        ship = new Ship();

    }

    /**
     * Method that checks if we can incrementing a skill will be a valid
     * operation. Easy difficulty has up to 24 skill points. Medium
     * difficulty has up to 16 skill points. Easy difficulty has up to 8
     * skill points.
     *
     * @param pilot    Number of pilot skill points
     * @param fighter  Number of fighter skill points
     * @param merchant Number of merchant skill points
     * @param engineer Number of engineer skill points
     */
    public void setSkills(int pilot, int fighter, int merchant, int engineer) {
        if (pilot + fighter + merchant + engineer <= diff.getSkillPoints()) {
            this.pilot = pilot;
            this.fighter = fighter;
            this.merchant = merchant;
            this.engineer = engineer;
        }
    }

    /**
     * Returns the number of skill points stored in the Pilot skill
     *
     * @return pilot
     */
    public int getPilot() {
        return pilot;
    }

    /**
     * Returns the number of skill points stored in the Fighter skill
     *
     * @return Fighter
     */
    public int getFighter() {
        return fighter;
    }

    /**
     * Returns the number of skill points stored in the Merchant skill
     *
     * @return Merchant
     */
    public int getMerchant() {
        return merchant;
    }

    /**
     * Returns the number of skill points stored in the Engineer skill
     *
     * @return Engineer
     */
    public int getEngineer() {
        return engineer;
    }

    /**
     * Returns the current number of credits
     *
     * @return credits
     */
    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public Ship getShip() {
        return ship;
    }

    /**
     * Returns the player's name
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name for the player
     *
     * @param name desired name
     * @throws IllegalArgumentException if the name passed in is the empty string or null.
     */
    public void setName(String name) throws IllegalArgumentException {
        if (name == null || name.equals("")) {
            throw new IllegalArgumentException("Name cannot be empty or null!");
        } else {
            this.name = name;
        }
    }

    /**
     * Returns the player's chosen difficulty
     *
     * @return diff
     */
    public Difficulty getDiff() {
        return diff;
    }

    /**
     * Sets the difficulty for the player and disburses the appropriate
     * amount of credits
     *
     * @param diff EASY, MEDIUM, or HARD.
     */
    public void setDiff(Difficulty diff) {
        this.diff = diff;
        credits = diff.getStartingCredits();
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region r) {
        region = r;
    }

    public void travel(Region r, boolean abortTravel) {
        region = ship.travel(region, r, abortTravel);
    }

    public void spend(int credits) {
        this.credits -= credits;
    }

    public void earn(int credits) {
        this.credits += credits;
    }
}