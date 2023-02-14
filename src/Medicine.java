import java.util.Random;

/**
 * Class that describes Medicine, which is a Good
 */
public class Medicine extends Good {
    /**
     * Constructs an instance of Medicine
     *
     * @param r the region in which the Medicine is sold
     */
    public Medicine(Region r) {
        super(510, "Medicine", new Random(), r, 4);
    }

    /**
     * Determines whether the Medicine is available in a Region
     *
     * @param r the Region
     * @return availableInRegion
     */
    public static boolean availableInRegion(Region r) {
        return Good.availableInRegion(r, 4);
    }
}
