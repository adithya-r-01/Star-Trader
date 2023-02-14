import java.util.Random;

/**
 * Class that describes Ore, which is a Good
 */
public class Ore extends Good {
    /**
     * Constructs a new instance of Ore
     *
     * @param r the region in which the Ore is sold
     */
    public Ore(Region r) {
        super(300, "Ore", new Random(), r, 2);
    }

    /**
     * Determines whether the Ore is available in a Region
     *
     * @param r the Region
     * @return availableInRegion
     */
    public static boolean availableInRegion(Region r) {
        return Good.availableInRegion(r, 2);
    }
}
