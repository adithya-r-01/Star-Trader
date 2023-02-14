import java.util.Random;

/**
 * Class that describes a Games good
 */
public class Games extends Good {
    /**
     * Constructs a new instance of Games
     *
     * @param r the region in which the Games are being sold
     */
    public Games(Region r) {
        super(180, "Games", new Random(), r, 3);
    }

    /**
     * Determines whether the Games are available in a Region
     *
     * @param r the Region
     * @return availableInRegion
     */
    public static boolean availableInRegion(Region r) {
        return Good.availableInRegion(r, 3);
    }
}
