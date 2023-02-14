import java.util.Random;

/**
 * Class that describes Robots, which is a Good
 */
public class Robots extends Good {
    /**
     * Constructs a new instance of Robots
     *
     * @param r the region in which the Robots are sold
     */
    public Robots(Region r) {
        super(3950, "Robots", new Random(), r, 6);
    }

    /**
     * Determines whether the Robots are available in a Region
     *
     * @param r the Region
     * @return availableInRegion
     */
    public static boolean availableInRegion(Region r) {
        return Good.availableInRegion(r, 6);
    }
}
