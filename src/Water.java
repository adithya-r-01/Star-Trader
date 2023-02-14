import java.util.Random;

/**
 * Class that describes Water, which is a Good
 */
public class Water extends Good {
    /**
     * Constructs a new instance of Water
     *
     * @param r the region in which the Water is sold
     */
    public Water(Region r) {
        super(30, "Water", new Random(), r, 0);
    }

    /**
     * Determines whether the Water is available in a Region
     *
     * @param r the Region
     * @return availableInRegion
     */
    public static boolean availableInRegion(Region r) {
        return Good.availableInRegion(r, 0);
    }
}
