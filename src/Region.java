import com.sun.jdi.AbsentInformationException;

import java.util.HashSet;
import java.util.Random;

/**
 * Class that describes a Region
 *
 * @version M5
 */
public class Region {

    private static Region[] regions; // static array so that we can access in
    // Both of these HashSets are static so that they can persist across
    // different Region objects
    private static HashSet<Integer> usedNames = new HashSet<>(); // contains the
    // indices of used names
    private static HashSet<Integer> usedDescriptions = new HashSet<>(); //
    // other classes
    private final int xBound = 486; // Maximum value for x, y, or tech is
    private final int yBound = 354;
    // descriptions arrays
    private int x; // x coordinate for the Region's location
    private int y; // y coordinate for the Region's location
    private TechLevel techLevel;
    private TechLevel[] techLevelArray;
    private String name; // name of Region
    private String description; // short Description for region
    private boolean visited; // has the Region been visited by the Player
    private boolean winItem;
    private Random rand; // random number generator. Instantiated by the
    // constructor
    // so that we have a new seed each run.

    private Marketplace marketplace;

    // potential descriptions for a Region


    /**
     * Constructs a new Region. Uses random numbers to ensure that every
     * Region will have a randomly selected name and description. Also
     * includes logic to ensure that multiple instantiated Regions will not
     * reuse names and descriptions.
     *
     * @throws MaximumRegionsException if more than 10 regions have been
     *                                 initialized
     */
    public Region() throws MaximumRegionsException {
        if (usedNames.size() == 10) { // we only have 10 names and
            // descriptions. If we try to instantiate, our logic will leave
            // us with unset variables for the Region.
            throw new MaximumRegionsException(
                    "Maximum number of Regions already "
                            + "instantiated!");
        }

        rand = new Random();
        x = rand.nextInt(xBound) + 75;
        y = rand.nextInt(yBound);
        techLevel = randomEnum();

        // Length of both the names and
        int indexBound = 10;
        int namesIndex = rand.nextInt(indexBound);
        int descriptionsIndex = rand.nextInt(indexBound);

        while (usedNames.contains(namesIndex)) {
            namesIndex = rand.nextInt(indexBound);
        }
        while (usedDescriptions.contains(descriptionsIndex)) {
            descriptionsIndex = rand.nextInt(indexBound);
        }

        // all potential names for a Region
        String[] names = {"Alderaan", "Tatooine", "Sol", "Aegis VII", "Andromeda", "Knowhere",
                          "The CULC", "Home Park", "Tech Square", "Betelgeuse"};
        name = names[namesIndex];
        // all
        String[] descriptions = {"Wretched hive of scum and villainy",
                                    "Not worth visiting", "Worse than Athens", "Like Las Vegas",
                                    "Somehow still a British colony", "Somehow a French colony",
                                    "Overrun with Australians", "Very cheap place to live",
                                    "HAZMAT suit required", "Failed decontamination site"};
        description = descriptions[descriptionsIndex];
        usedNames.add(namesIndex);
        usedDescriptions.add(descriptionsIndex);
    }

    /**
     * Returns the array of Region objects in the game
     *
     * @return regions
     */
    public static Region[] getRegions() {
        return regions;
    }

    public static void populateRegions() {
        try {
            regions = new Region[10];
            for (int i = 0; i < 10; i++) {
                Region.regions[i] = new Region();
            }
            int index = new Random().nextInt(10);
            for (int i = 0; i < 10; i++) {
                Region.regions[i].winItem = i == index;
                Region.regions[i].generateCoordinates();
            }
        } catch (MaximumRegionsException e) {
            e.printStackTrace(); // exception should never happen
        }
    }

    /**
     * Used for testing purposes ONLY. Clears our static variables so that we
     * can continue testing.
     */
    public static void clearRegions() {
        usedNames.clear();
        usedDescriptions.clear();
    }

    /**
     * Method that is used to calculate the distance between two regions.
     * Uses the Pythagorean Theorem.
     *
     * @param a Region #1
     * @param b Region #2
     * @return distance
     */
    public static double distance(Region a, Region b) {
        double xDist = b.getX() - a.getX();
        double yDist = b.getY() - a.getY();

        return Math.sqrt(xDist * xDist + yDist * yDist);
    }

    public static HashSet<Integer> getUsedNames() {
        return usedNames;
    }

    public static HashSet<Integer> getUsedDescriptions() {
        return usedDescriptions;
    }

    /**
     * Returns a random TechLevel from the enums array. If the enums array
     * has not been initialized, this method does so.
     *
     * @return a TechLevel
     */
    private TechLevel randomEnum() {
        if (techLevelArray == null) {
            techLevelArray = new TechLevel[8];
            techLevelArray[0] = TechLevel.PRE_AG;
            techLevelArray[1] = TechLevel.AG;
            techLevelArray[2] = TechLevel.MEDIEVAL;
            techLevelArray[3] = TechLevel.RENAISSANCE;
            techLevelArray[4] = TechLevel.EARLY_INDUSTRIAL;
            techLevelArray[5] = TechLevel.INDUSTRIAL;
            techLevelArray[6] = TechLevel.POST_INDUSTRIAL;
            techLevelArray[7] = TechLevel.HI_TECH;
        }

        return techLevelArray[rand.nextInt(techLevelArray.length)];
    }

    /**
     * Used to generate coordinates for each Region. Ensures that coordinates
     * are valid and provide ample spacing between each Region.
     */
    private void generateCoordinates() {
        for (Region r : getRegions()) {
            if (r != this) {
                while (Region.distance(this, r) < 65) {
                    x = rand.nextInt(xBound) + 75;
                    y = rand.nextInt(yBound);
                }
            }
        }
    }

    /**
     * Gets the x coordinate
     *
     * @return x
     */
    public int getX() {
        return x;

    }

    /**
     * Gets the y coordinate
     *
     * @return y
     */
    public int getY() {
        return y;
    }

    /**
     * Gets the tech level of the Region
     *
     * @return techLevel
     * @throws AbsentInformationException if region is not visited
     */
    public TechLevel getTechLevel() throws AbsentInformationException {
        if (!visited) {
            throw new AbsentInformationException("Information unavailable!");
        }
        return techLevel;
    }

    /**
     * Gets the name of the Region
     *
     * @return name
     */
    public String getName() {
        if (!visited) {
            return "Unknown";
        }
        return name;
    }

    /**
     * Gets the description of the Region
     *
     * @return description
     */
    public String getDescription() {
        if (!visited) {
            return "Unknown";
        }
        return description;
    }

    /**
     * Gets whether the Region is visited or not
     *
     * @return visited
     */
    public boolean isVisited() {
        return visited;
    }

    /**
     * Sets the value of visited
     *
     * @param visited true or false
     */
    public void setVisited(boolean visited) {
        this.visited = visited;
        marketplace = new Marketplace(this);
    }

    public boolean isWinItem() {
        return winItem;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Region)) {
            return false;
        }
        Region r = (Region) obj;
        return name.equals(r.name);
    }

    public Marketplace getMarketplace() {
        return marketplace;
    }
}
