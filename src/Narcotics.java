import java.util.Random;

/**
 * Class that describes Narcotics, which is a Good
 */
public class Narcotics extends Good {
    /**
     * Constructs a new instance of Narcotics
     *
     * @param r the region in which the Narcotics are sold
     */
    public Narcotics(Region r) {
        super(2625, "Narcotics", new Random(), r, 5);
    }

    /**
     * Determines whether the Narcotics are available in a Region
     *
     * @param r the Region
     * @return availableInRegion
     */
    public static boolean availableInRegion(Region r) {
        return Good.availableInRegion(r, 5);
    }
}
