import java.util.Random;

/**
 * Class which describes the Furs good
 */
public class Furs extends Good {
    /**
     * Constructs a new instance of Furs
     *
     * @param r the region in which the Furs are being sold
     */
    public Furs(Region r) {
        super(250, "Furs", new Random(), r, 0);
    }

    /**
     * Determines whether the Furs are available in a Region
     *
     * @param r the Region
     * @return availableInRegion
     */
    public static boolean availableInRegion(Region r) {
        return Good.availableInRegion(r, 0);
    }
}
